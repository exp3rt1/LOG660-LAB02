package com.etsmtl.equipe9.model;
// Generated 2016-10-03 15:53:41 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Employe generated by hbm2java
 */
@Entity
@Table(name="EMPLOYE"
    ,schema="EQUIPE9"
)@XmlRootElement

public class Employe  implements java.io.Serializable {


     private String matriculeemploye;
     private Adresse adresse;
     private String courriel;
     private String nom;
     private String prenom;
     private String motpasse;
     private String numerotelephone;
     private Date datenaissance;

    public Employe() {
    }

	
    public Employe(String matriculeemploye, String nom, String prenom, String motpasse, String numerotelephone, Date datenaissance) {
        this.matriculeemploye = matriculeemploye;
        this.nom = nom;
        this.prenom = prenom;
        this.motpasse = motpasse;
        this.numerotelephone = numerotelephone;
        this.datenaissance = datenaissance;
    }
    public Employe(String matriculeemploye, Adresse adresse, String courriel, String nom, String prenom, String motpasse, String numerotelephone, Date datenaissance) {
       this.matriculeemploye = matriculeemploye;
       this.adresse = adresse;
       this.courriel = courriel;
       this.nom = nom;
       this.prenom = prenom;
       this.motpasse = motpasse;
       this.numerotelephone = numerotelephone;
       this.datenaissance = datenaissance;
    }
   
     @Id 

    
    @Column(name="MATRICULEEMPLOYE", unique=true, nullable=false, length=7)
    public String getMatriculeemploye() {
        return this.matriculeemploye;
    }
    
    public void setMatriculeemploye(String matriculeemploye) {
        this.matriculeemploye = matriculeemploye;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDADRESSE")
    public Adresse getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    
    @Column(name="COURRIEL", length=500)
    public String getCourriel() {
        return this.courriel;
    }
    
    public void setCourriel(String courriel) {
        this.courriel = courriel;
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




}


