package tn.poste.myship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class TrackingNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parcelId;
    private LocalDate createdAt=LocalDate.now();

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public String getFormattedParcelId() {
        return "EN"+String.format("%09d", parcelId)+"TN";
    }
}
