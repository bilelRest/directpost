package tn.poste.myship.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.poste.myship.entity.Parcel;
import tn.poste.myship.entity.Receiver;
import tn.poste.myship.entity.Sender;
import tn.poste.myship.repo.ParcelRepo;
import tn.poste.myship.repo.ReceiverRepo;
import tn.poste.myship.repo.SenderRepo;

@Controller

@RequestMapping("/client")
public class Client {
    private final SenderRepo senderRepo;
    private final ReceiverRepo receiverRepo;
    private final ParcelRepo parcelRepo;

    public Client(SenderRepo senderRepo,ReceiverRepo receiverRepo, ParcelRepo parcelRepo) {
        this.senderRepo = senderRepo;
        this.receiverRepo = receiverRepo;
        this.parcelRepo = parcelRepo;
    }
    @GetMapping("/check/parcel")
    @ResponseBody
    public Map<String, Object> validateClient(@RequestParam("op") Long op,@RequestParam(value="validate", required=false) Boolean validate) {
        System.out.println("Opération ID reçue: " + op);
        List<Parcel> parcels= parcelRepo.findByOperationIdAndDeliveredFalse(op);
        String opFormatted="";
        if( validate!=null && validate){
            for (Parcel parcel : parcels) {
                parcel.setDelivered(true);
                opFormatted=parcel.getOperationId().getFormattedOpId();
                parcelRepo.save(parcel);
            }
        }

        return Map.of("opFormatted", opFormatted, "parcels", parcels);
    }
@GetMapping("/check/sender")
@ResponseBody
public Sender checkclient(@RequestParam("tel") Long tel) {
    Sender sender = senderRepo.findBySendTel(tel);
    return (sender != null) ? sender : new Sender(); // Retourne un objet vide au lieu de null
}
    @GetMapping("/check/receiver")
@ResponseBody
public Receiver checkReceiver(@RequestParam("tel") Long tel) {
    Receiver receiver = receiverRepo.findByRecTel(tel);
    return (receiver != null) ? receiver : new Receiver(); // Retourne un objet vide au lieu de null
}
@GetMapping("/validate-session")
@ResponseBody
public ResponseEntity<?> validateSession(@RequestParam("tel") Long tel) {
    // Recherche des colis
    List<Parcel> parcels = parcelRepo.findBySendTelAndDeliveredFalse(tel);
    
    if(parcels.isEmpty()) {
        return ResponseEntity.ok("Aucun colis en attente pour ce numéro.");
    }
    
    // Correction ici : .ok() prépare le builder, .build() crée la réponse vide avec code 200
    return ResponseEntity.ok().build();
}
}