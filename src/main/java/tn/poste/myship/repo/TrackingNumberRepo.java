package tn.poste.myship.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.poste.myship.entity.TrackingNumber;

public interface TrackingNumberRepo extends JpaRepository<TrackingNumber,Long> {
}
