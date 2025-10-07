package tn.poste.myship.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.poste.myship.entity.Parcel;
import tn.poste.myship.entity.TrackingNumber;

import java.time.LocalDate;

public interface ParcelRepo extends JpaRepository< Parcel,Long> {
    Parcel findByTrackingNumber(TrackingNumber trackingNumber);


        // Recherche par date de création du tracking number
        @Query("SELECT p FROM Parcel p WHERE p.trackingNumber.createdAt BETWEEN :dateDebut AND :dateFin")
        Page<Parcel> findByTrackingNumberCreatedAtBetween(@Param("dateDebut") LocalDate dateDebut,
                                                          @Param("dateFin") LocalDate dateFin,
                                                          Pageable pageable);

        // Compter les colis d'aujourd'hui
        @Query("SELECT COUNT(p) FROM Parcel p WHERE p.trackingNumber.createdAt = :date")
        Long countByTrackingNumberCreatedAt(@Param("date") LocalDate date);

        // Somme des prix
        @Query("SELECT COALESCE(SUM(p.price), 0) FROM Parcel p WHERE p.trackingNumber.createdAt BETWEEN :dateDebut AND :dateFin")
        Double sumPriceByDateRange(@Param("dateDebut") LocalDate dateDebut,
                                   @Param("dateFin") LocalDate dateFin);

        // Somme des poids
        @Query("SELECT COALESCE(SUM(p.weight), 0) FROM Parcel p WHERE p.trackingNumber.createdAt BETWEEN :dateDebut AND :dateFin")
        Double sumWeightByDateRange(@Param("dateDebut") LocalDate dateDebut,
                                    @Param("dateFin") LocalDate dateFin);
    }

