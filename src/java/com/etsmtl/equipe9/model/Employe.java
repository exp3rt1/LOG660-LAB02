package com.etsmtl.equipe9.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EMPLOYE",schema="EQUIPE9")
public class Employe  implements java.io.Serializable {

    @Id
    @Column(name="MATRICULEEMPLOYE", unique=true, nullable=false, length=7)
    private String matriculeemploye;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDADRESSE")
    private Adresse adresse;
    
    @Column(name="COURRIEL", length=500)
    private String courriel;
    
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

    public Employe() {}
	
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
   
    public String getMatriculeemploye() {
        return this.matriculeemploye;
    }  
    public void setMatriculeemploye(String matriculeemploye) {
        this.matriculeemploye = matriculeemploye;
    }
    public Adresse getAdresse() {
        return this.adresse;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }        
    public String getCourriel() {
        return this.courriel;
    } 
    public void setCourriel(String courriel) {
        this.courriel = courriel;
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
    
}


