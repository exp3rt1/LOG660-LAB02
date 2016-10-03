package com.etsmtl.equipe9.model;
// Generated 2016-10-03 15:53:41 by Hibernate Tools 4.3.1


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

/**
 * Client generated by hbm2java
 */
@Entity
@Table(name="CLIENT"
    ,schema="EQUIPE9"
)
public class Client  implements java.io.Serializable {


     private String courriel;
     private Adresse adresse;
     private Forfait forfait;
     private Cartecredit cartecredit;
     private String nom;
     private String prenom;
     private String motpasse;
     private String numerotelephone;
     private Date datenaissance;
     private Set<Location> locations = new HashSet<Location>(0);

    public Client() {
    }

	
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
   
     @Id 

    
    @Column(name="COURRIEL", unique=true, nullable=false, length=500)
    public String getCourriel() {
        return this.courriel;
    }
    
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDADRESSE")
    public Adresse getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TYPEFORFAIT")
    public Forfait getForfait() {
        return this.forfait;
    }
    
    public void setForfait(Forfait forfait) {
        this.forfait = forfait;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NUMEROCARTECREDIT")
    public Cartecredit getCartecredit() {
        return this.cartecredit;
    }
    
    public void setCartecredit(Cartecredit cartecredit) {
        this.cartecredit = cartecredit;
    }

    
    @Column(name="NOM", nullable=false, length=100)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="PRENOM", nullable=false, length=100)
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    @Column(name="MOTPASSE", nullable=false, length=30)
    public String getMotpasse() {
        return this.motpasse;
    }
    
    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    
    @Column(name="NUMEROTELEPHONE", nullable=false, length=20)
    public String getNumerotelephone() {
        return this.numerotelephone;
    }
    
    public void setNumerotelephone(String numerotelephone) {
        this.numerotelephone = numerotelephone;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="DATENAISSANCE", nullable=false, length=7)
    public Date getDatenaissance() {
        return this.datenaissance;
    }
    
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="client")
    public Set<Location> getLocations() {
        return this.locations;
    }
    
    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }




}


