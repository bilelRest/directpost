package tn.poste.myship.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sendId;
    private String sendName;
    private String sendSocialReason;
    private Long sendTel;
    private String adress;
    private Long postalCode;

    public Sender(String sendName, String sendSocialReason, Long sendTel, String adress, Long postalCode) {
        this.sendName = sendName;
        this.sendSocialReason = sendSocialReason;
        this.sendTel = sendTel;
        this.adress = adress;
        this.postalCode = postalCode;
    }

    public Sender() {
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendSocialReason() {
        return sendSocialReason;
    }

    public void setSendSocialReason(String sendSocialReason) {
        this.sendSocialReason = sendSocialReason;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getSendTel() {
        return sendTel;
    }

    public void setSendTel(Long sendTel) {
        this.sendTel = sendTel;
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
