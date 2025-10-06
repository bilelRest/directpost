package tn.poste.myship.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.poste.myship.entity.Parcel;
import tn.poste.myship.entity.TrackingNumber;

public interface ParcelRepo extends JpaRepository< Parcel,Long> {
    Parcel findByTrackingNumber(TrackingNumber trackingNumber);
}
