package tn.poste.myship.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Pochette {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String typePochette;
private int quantite;
private Double totalPrice;
@ManyToOne
private Sender sender;
public Double getTotalPrice() {
    return totalPrice;
}
public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
}
public Pochette() {
}
public Pochette(String typePochette, int quantite, Sender sender, Double totalPrice) {
    this.typePochette = typePochette;
    this.quantite = quantite;
    this.sender = sender;
    this.totalPrice = totalPrice;
}
public Long getId() {
    return id;

}
public void setId(Long id) {
    this.id = id;
}
public String getTypePochette() {
    return typePochette;
}
public void setTypePochette(String typePochette) {
    this.typePochette = typePochette;           

}
public int getQuantite() {
    return quantite;
}
public void setQuantite(int quantite) {
    this.quantite = quantite;
}
public Sender getSender() {
    return sender;
}
public void setSender(Sender sender) {
    this.sender = sender;
} 
public Double getPrixTotal() {
    return totalPrice;
}
public void setPrixTotal(Double prixTotal) {
    this.totalPrice = prixTotal;
} 
}

