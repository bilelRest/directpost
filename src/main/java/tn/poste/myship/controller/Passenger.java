package tn.poste.myship.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.poste.myship.entity.Parcel;
import tn.poste.myship.entity.Receiver;
import tn.poste.myship.entity.Sender;
import tn.poste.myship.entity.TrackingNumber;
import tn.poste.myship.repo.ParcelRepo;
import tn.poste.myship.repo.ReceiverRepo;
import tn.poste.myship.repo.SenderRepo;
import tn.poste.myship.repo.TrackingNumberRepo;
import tn.poste.myship.service.CheckClient;


@Controller
@RequestMapping("/passenger")
public class Passenger {
    @Autowired
    CheckClient checkClient;
@Autowired
    ParcelRepo parcelRepo;
@Autowired
    ReceiverRepo receiverRepo;
@Autowired
    SenderRepo senderRepo;
@Autowired
    TrackingNumberRepo trackingNumberRepo;
    @GetMapping("/national")
    public String passenger(Model model) {
        Parcel parcel = new Parcel();
        parcel.setSender(new Sender());
        parcel.setReceiver(new Receiver());
        model.addAttribute("parcel", parcel);
        return "passenger";
    }

    @PostMapping(value = "reserve")
@ResponseBody
public String reserve(@ModelAttribute Parcel parcel){

    // 1. Gérer le numéro de suivi
    TrackingNumber trackingNumber = new TrackingNumber();
    trackingNumberRepo.save(trackingNumber);
    parcel.setTrackingNumber(trackingNumber);

    // 2. Calcul du prix (inchangé)
    //Double weight = parcel.getWeight();
    //Double prix = parcel.getPrice();
    //  // Déporté en méthode pour la clarté
    // parcel.setPrice(prix);

    // 3. --- LE CORRECTIF ICI ---
    // Au lieu de setSendId, on remplace tout l'objet par celui géré par JPA
    Sender managedSender = checkClient.checkSender(parcel.getSender());
    parcel.setSender(managedSender);

    Receiver managedReceiver = checkClient.checkReceiver(parcel.getReceiver());
    parcel.setReceiver(managedReceiver);

    // 4. Sauvegarde
    parcelRepo.save(parcel);

    return trackingNumber.getFormattedParcelId();
}

// Petite fonction helper pour nettoyer le contrôleur
private Double calculPrix(Double weight) {
    if (weight <= 0.5) return 5.0;
    if (weight <= 1) return 7.0;
    if (weight <= 2) return 9.0;
    if (weight <= 5) return 10.0;
    if (weight <= 7) return 12.0;
    if (weight <= 12) return 14.0;
    if (weight <= 17) return 20.0;
    if (weight <= 22) return 25.0;
    if (weight <= 27) return 30.0;
    return 35.0;
}
    @GetMapping(value = "success")
    public String success(Model model, @RequestParam(value = "track")String track){
        if (StringUtils.hasText(track)){
            String numericPart = track.substring(2, track.length() - 2);
           Optional<TrackingNumber> trackingNumber= trackingNumberRepo.findById(Long.parseLong(numericPart));
           if (trackingNumber.isPresent()){
Parcel parcel=parcelRepo.findByTrackingNumber(trackingNumber.get());
    model.addAttribute("parcel",parcel!=null?parcel:new Parcel());

           }
        }
        model.addAttribute("track",track);
        return "success";
    }
}