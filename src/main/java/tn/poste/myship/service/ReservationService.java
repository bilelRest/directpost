package tn.poste.myship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tn.poste.myship.entity.Parcel;
import tn.poste.myship.entity.Receiver;
import tn.poste.myship.entity.Sender;
import tn.poste.myship.entity.TrackingNumber;
import tn.poste.myship.repo.ParcelRepo;
import tn.poste.myship.repo.ReceiverRepo;
import tn.poste.myship.repo.SenderRepo;
import tn.poste.myship.repo.TrackingNumberRepo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ReservationService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private SenderRepo senderRepo;
    @Autowired
    private ReceiverRepo receiverRepo;
    @Autowired
    private ParcelRepo parcelRepo;
    @Autowired
    private TrackingNumberRepo trackingNumberRepo;

    public SseEmitter createEmitter() {
        SseEmitter emitter = new SseEmitter(30 * 60 * 1000L); // 30 minutes
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    public SseEmitter getLastEmitter() {
        if (emitters.isEmpty()) return null;
        return emitters.get(emitters.size() - 1);
    }

    @Async
    public void processReservation(Sender sender, Receiver receiver, SseEmitter emitter) {
        try {
            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Démarrage du traitement"));

            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Enregistrement de l'expéditeur..."));
            Sender savedSender = senderRepo.save(sender);
            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Expéditeur enregistré (id=" + savedSender.getSendId() + ")"));

            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Enregistrement du destinataire..."));
            Receiver savedReceiver = receiverRepo.save(receiver);
            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Destinataire enregistré (id=" + savedReceiver.getRecId() + ")"));

            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Création du dépôt (parcel)..."));
            Parcel parcel = new Parcel();
            parcel.setSender(savedSender);
            parcel.setReceiver(savedReceiver);
            parcel.setWeight(0.0);
            parcel.setPrice(0.0);
            Parcel savedParcel = parcelRepo.save(parcel);

            if (emitter != null) emitter.send(SseEmitter.event().name("progress").data("Génération du numéro de suivi..."));
            TrackingNumber tn = new TrackingNumber();
            // Save tracking number after parcel so we can set parcelId on it
            tn.setParcelId(savedParcel.getParcelId());
            tn.setCreatedAt(LocalDate.now());
            trackingNumberRepo.save(tn);

            savedParcel.setTrackingNumber(tn);
            parcelRepo.save(savedParcel);

            if (emitter != null) emitter.send(SseEmitter.event().name("done").data(tn.getFormattedParcelId()));

            if (emitter != null) emitter.complete();
        } catch (IOException e) {
            if (emitter != null) emitter.completeWithError(e);
        }
    }
}
