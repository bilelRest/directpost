package tn.poste.myship.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import tn.poste.myship.entity.Pochette;
import tn.poste.myship.entity.Receiver;
import tn.poste.myship.entity.Sender;
import tn.poste.myship.entity.TrackingNumber;
import tn.poste.myship.repo.ParcelRepo;
import tn.poste.myship.repo.PochetteRepo;
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
@Autowired
    PochetteRepo pochetteRepo;

    @GetMapping("/national")
    public String passenger(Model model) {
        Parcel parcel = new Parcel();
        parcel.setSender(new Sender());
        parcel.setReceiver(new Receiver());
        model.addAttribute("parcel", parcel);
        return "passenger";
    }
@PostMapping("/ajouterpochette")
@ResponseBody
public ResponseEntity<Map<String, Object>> ajouterPochette(
        @RequestParam("type") String typePochette,
        @RequestParam("quantite") int quantite,
        @RequestParam("tel") String tel, // Changé de Long à String pour correspondre au JS
        Model model) {
    
    Map<String, Object> response = new HashMap<>();
    
    try {
        // Validation
        if (typePochette == null || typePochette.isEmpty() || quantite <= 0 || tel == null || tel.isEmpty()) {
            response.put("success", false);
            response.put("message", "Données invalides");
            return ResponseEntity.badRequest().body(response);
        }
        
        // Vérifier si l'expéditeur existe
        Sender sender = senderRepo.findBySendTel(Long.parseLong(tel));
        if (sender == null) {
            response.put("success", false);
            response.put("message", "Expéditeur non trouvé");
            return ResponseEntity.badRequest().body(response);
        }
        
        // Calcul du prix
        Double prix = 0.0;
        switch (typePochette) {
            case "pn" -> prix = 1.0;
            case "pnpm" -> prix = 1.2;
            case "pngm" -> prix = 1.5;
            case "mat" -> prix = 2.0;
            default -> {
                response.put("success", false);
                response.put("message", "Type de pochette invalide");
                return ResponseEntity.badRequest().body(response);
            }
        }
        
        Double totalPrice = prix * quantite;
        
        // Créer et sauvegarder la pochette
        Pochette pochette = new Pochette();
        pochette.setTypePochette(typePochette);
        pochette.setQuantite(quantite);
        pochette.setSender(sender);
        pochette.setPrixTotal(totalPrice);
        
        Pochette savedPochette = pochetteRepo.save(pochette);
        
        // Réponse de succès
        response.put("success", true);
        response.put("message", "Pochette ajoutée avec succès");
        response.put("data", Map.of(
            "id", savedPochette.getId(),
            "type", savedPochette.getTypePochette(),
            "quantite", savedPochette.getQuantite(),
            "prixTotal", savedPochette.getPrixTotal(),
            "sender", savedPochette.getSender().getSendName()
        ));
        
        return ResponseEntity.ok(response);
        
    } catch (Exception e) {
        response.put("success", false);
        response.put("message", "Erreur serveur: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
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