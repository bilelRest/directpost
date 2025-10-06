package tn.poste.myship.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.poste.myship.entity.Receiver;

public interface ReceiverRepo extends JpaRepository<Receiver, Long> {
}
