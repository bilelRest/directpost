package tn.poste.myship.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Operation {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long opId;
@OneToMany
private List<Parcel> parcel;
@OneToMany
private List< Pochette> pochette;

public Operation() {}
public Operation(List<Parcel> parcel, List<Pochette> pochette) {
    this.parcel = parcel;
    this.pochette = pochette;
}

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public List<Parcel> getParcel() {
        return parcel;
    }

    public void setParcel(List<Parcel> parcel) {
        this.parcel = parcel;
    }

    public List<Pochette> getPochette() {
        return pochette;
    }

    public void setPochette(List<Pochette> pochette) {
        this.pochette = pochette;
    }
    public String getFormattedOpId() {
    // Récupère l'année sur 2 chiffres (ex: 2026 -> 26)
    int yearTwoDigits = LocalDate.now().getYear() % 100;
    
    // Récupère le mois avec un zéro initial si nécessaire (ex: 01, 05, 12)
    String month = String.format("%02d", LocalDate.now().getMonthValue());

    // Format final : OP- (ID sur 10 positions avec zéros) (Mois) (Année)
    return String.format("OP-%010d%s%02d", this.opId, month, yearTwoDigits);
}

}
