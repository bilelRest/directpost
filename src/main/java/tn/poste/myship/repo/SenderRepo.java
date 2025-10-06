package tn.poste.myship.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.poste.myship.entity.Sender;

public interface SenderRepo extends JpaRepository< Sender,Long> {
}
