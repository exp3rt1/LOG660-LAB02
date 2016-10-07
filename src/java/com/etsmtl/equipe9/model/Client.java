package com.etsmtl.equipe9.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CLIENT",schema="EQUIPE9")
public class Client  implements java.io.Serializable {

    @Id
    @Column(name="COURRIEL", unique=true, nullable=false, length=500)
    private String courriel;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDADRESSE")
    private Adresse adresse;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TYPEFORFAIT")
    private Forfait forfait;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NUMEROCARTECREDIT")
    private Cartecredit cartecredit;
    
    @Column(name="NOM", nullable=false, length=100)
    private String nom;
    
    @Column(name="PRENOM", nullable=false, length=100)
    private String prenom;
    
    @Column(name="MOTPASSE", nullable=false, length=30)
    private String motpasse;
    
    @Column(name="NUMEROTELEPHONE", nullable=false, length=20)
    private String numerotelephone;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATENAISSANCE", nullable=false, length=7)
    private Date datenaissance;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
    private Set<Location> locations = new HashSet<>(0);

    public Client() {}
    
    public Client(String courriel, String nom, String prenom, String motpasse, String numerotelephone, Date datenaissance) {
        this.courriel = courriel;
        this.nom = nom;
        this.prenom = prenom;
        this.motpasse = motpasse;
        this.numerotelephone = numerotelephone;
        this.datenaissance = datenaissance;
    }
    public Client(String courriel, Adresse adresse, Forfait forfait, Cartecredit cartecredit, String nom, String prenom, String motpasse, String numerotelephone, Date datenaissance, Set<Location> locations) {
       this.courriel = courriel;
       this.adresse = adresse;
       this.forfait = forfait;
       this.cartecredit = cartecredit;
       this.nom = nom;
       this.prenom = prenom;
       this.motpasse = motpasse;
       this.numerotelephone = numerotelephone;
       this.datenaissance = datenaissance;
       this.locations = locations;
    }
    
    public String getCourriel() {
        return this.courriel;
    }
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
    public Adresse getAdresse() {
        return this.adresse;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    public Forfait getForfait() {
        return this.forfait;
    }
    public void setForfait(Forfait forfait) {
        this.forfait = forfait;
    }
    public Cartecredit getCartecredit() {
        return this.cartecredit;
    }
    public void setCartecredit(Cartecredit cartecredit) {
        this.cartecredit = cartecredit;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    } 
    public String getMotpasse() {
        return this.motpasse;
    }
    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }
    public String getNumerotelephone() {
        return this.numerotelephone;
    }
    public void setNumerotelephone(String numerotelephone) {
        this.numerotelephone = numerotelephone;
    }
    public Date getDatenaissance() {
        return this.datenaissance;
    }
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    public Set<Location> getLocations() {
        return this.locations;
    }  
    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

}


