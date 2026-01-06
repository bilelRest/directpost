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
    private String country;
    private String city;

    public Receiver() {
    }

    public Receiver(String recName, String recSocialReason, Long recTel, String adress, Long postalCode,String country, String city) {
        this.country=country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
