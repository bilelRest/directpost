package tn.poste.myship.service;

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
        if (existingSender == null) {
            senderRepo.save(sender);
            return sender;
        }
        return existingSender;
    }



        public Receiver checkReceiver(Receiver receiver) {
        System.err.println("Checking receiver with tel: " + receiver.getRecTel());
        Receiver existingReceiver = receiverRepo.findByRecTel(receiver.getRecTel());
        if (existingReceiver == null) {
            receiverRepo.save(receiver);
            return receiver;
        }
        return existingReceiver;
    }
}
