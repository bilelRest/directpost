package tn.poste.myship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.poste.myship.entity.Parcel;
import tn.poste.myship.entity.Receiver;
import tn.poste.myship.entity.Sender;
import tn.poste.myship.entity.TrackingNumber;
import tn.poste.myship.repo.ParcelRepo;
import tn.poste.myship.repo.ReceiverRepo;
import tn.poste.myship.repo.SenderRepo;
import tn.poste.myship.repo.TrackingNumberRepo;

import java.util.Optional;


@Controller
public class Passenger {
@Autowired
    ParcelRepo parcelRepo;
@Autowired
    ReceiverRepo receiverRepo;
@Autowired
    SenderRepo senderRepo;
@Autowired
    TrackingNumberRepo trackingNumberRepo;
    @GetMapping("/passenger")
    public String passenger(Model model) {
        Parcel parcel = new Parcel();
        parcel.setSender(new Sender());
        parcel.setReceiver(new Receiver());
        model.addAttribute("parcel", parcel);
        return "passenger";
    }

    @PostMapping(value = "reserve")
    public String reserve(@ModelAttribute Parcel parcel,@RequestParam(value = "price",required = true)Double price){
        TrackingNumber trackingNumber=new TrackingNumber();
        trackingNumberRepo.save(trackingNumber);
parcel.setTrackingNumber(trackingNumber);
parcel.setPrice(price);
parcelRepo.save(parcel);

        return "redirect:/success?track="+trackingNumber.getFormattedParcelId();
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
