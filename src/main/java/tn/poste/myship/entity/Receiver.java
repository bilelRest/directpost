package tn.poste.myship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recId;
    private String recName;
    private String recSocialReason;
    private Long recTel;
    private String adress;
    private Long postalCode;

    public Receiver() {
    }

    public Receiver(String recName, String recSocialReason, Long recTel, String adress, Long postalCode) {
        this.recName = recName;
        this.recSocialReason = recSocialReason;
        this.recTel = recTel;
        this.adress = adress;
        this.postalCode = postalCode;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecSocialReason() {
        return recSocialReason;
    }

    public void setRecSocialReason(String recSocialReason) {
        this.recSocialReason = recSocialReason;
    }

    public Long getRecTel() {
        return recTel;
    }

    public void setRecTel(Long recTel) {
        this.recTel = recTel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Long postalCode) {
        this.postalCode = postalCode;
    }
}
