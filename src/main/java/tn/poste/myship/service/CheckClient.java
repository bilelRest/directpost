package tn.poste.myship.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.poste.myship.entity.Receiver;
import tn.poste.myship.entity.Sender;
import tn.poste.myship.repo.ReceiverRepo;
import tn.poste.myship.repo.SenderRepo;
@Service
@Transactional
public class CheckClient {
    @Autowired
    SenderRepo senderRepo;
    @Autowired
    ReceiverRepo receiverRepo;
    public Sender checkSender(Sender sender) {
        System.err.println("Checking sender with tel: " + sender.getSendTel());
        Sender existingSender = senderRepo.findBySendTel(sender.getSendTel());

        if (existingSender != null) {
            
            if(existingSender.getAdress()==null || !existingSender.getAdress().equals(sender.getAdress())){
                existingSender.setAdress( sender.getAdress());
            }
            if(existingSender.getCity()==null || !existingSender.getCity().equals(sender.getCity())){
                existingSender.setCity( sender.getCity());
            }
            if(existingSender.getPostalCode()==null || !Objects.equals(existingSender.getPostalCode(), sender.getPostalCode())){
                existingSender.setPostalCode( sender.getPostalCode());
            }
            if(existingSender.getSendEmail()==null || !existingSender.getSendEmail().equals(sender.getSendEmail())){
                existingSender.setSendEmail( sender.getSendEmail());
            }
            if(existingSender.getCountry()==null || !existingSender.getCountry().equals(sender.getCountry())){
                existingSender.setCountry( sender.getCountry());
            }
            if(existingSender.getSendName()==null || !existingSender.getSendName().equals(sender.getSendName())){
                existingSender.setSendName( sender.getSendName());
            }
            if(existingSender.getSendSocialReason()==null || !existingSender.getSendSocialReason().equals(sender.getSendSocialReason())){
                existingSender.setSendSocialReason( sender.getSendSocialReason());
            }
            if(existingSender.getSendTel()==null || !Objects.equals(existingSender.getSendTel(), sender.getSendTel())){
                existingSender.setSendTel( sender.getSendTel());
            }
            senderRepo.save(existingSender);
        }else{
            senderRepo.save(sender);
            return sender;
        }
        return existingSender;
    }


        public Receiver checkReceiver(Receiver receiver) {
        System.err.println("Checking receiver with tel: " + receiver.getRecTel());
        Receiver existingReceiver = receiverRepo.findByRecTel(receiver.getRecTel());
        if (existingReceiver != null) {
            if(existingReceiver.getAdress()==null || !existingReceiver.getAdress().equals(receiver.getAdress())){
                existingReceiver.setAdress( receiver.getAdress());
            }
            if(existingReceiver.getCity()==null || !existingReceiver.getCity().equals(receiver.getCity())){
                existingReceiver.setCity( receiver.getCity());
            }
            if(existingReceiver.getPostalCode()==null || !Objects.equals(existingReceiver.getPostalCode(), receiver.getPostalCode())){
                existingReceiver.setPostalCode( receiver.getPostalCode());
            }
            if(existingReceiver.getRecEmail()==null || !existingReceiver.getRecEmail().equals(receiver.getRecEmail())){
                existingReceiver.setRecEmail( receiver.getRecEmail());
            }
            if(existingReceiver.getCountry()==null || !existingReceiver.getCountry().equals(receiver.getCountry())){
                existingReceiver.setCountry( receiver.getCountry());
            }
            if(existingReceiver.getRecName()==null || !existingReceiver.getRecName().equals(receiver.getRecName())){
                existingReceiver.setRecName( receiver.getRecName());
            }
            if(existingReceiver.getRecSocialReason()==null || !existingReceiver.getRecSocialReason().equals(receiver.getRecSocialReason())){
                existingReceiver.setRecSocialReason( receiver.getRecSocialReason());
            }
            if(existingReceiver.getRecTel()==null || !Objects.equals(existingReceiver.getRecTel(), receiver.getRecTel())){
                existingReceiver.setRecTel( receiver.getRecTel());
            }
            receiverRepo.save(existingReceiver);
        } else {
            receiverRepo.save(receiver);
            return receiver;
        }
        return existingReceiver;
    }
}
