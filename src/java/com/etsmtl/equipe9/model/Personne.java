package com.etsmtl.equipe9.model;


import com.etsmtl.equipe9.service.Configuration;
import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PERSONNE",schema=Configuration.BD_SCHEMA)
public class Personne  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDPERSONNE", unique=true, nullable=false)
    private Long idpersonne;
    
    @Column(name="NOM", nullable=false, length=500)
    private String nom;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATENAISSANCE", length=7)
    private Date datenaissance;
    
    @Column(name="LIEUNAISSANCE", length=100)
    private String lieunaissance;
    
    @Column(name="BIOGRAPHIE")
    private String biographie;
    
    @Column(name="PHOTOURL", length=2000)
    private String photourl;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="acteur")
    private Set<Personnage> personnages = new HashSet<>(0);
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="realisateur")
    private Set<Film> films = new HashSet<>(0);

    public Personne() {}
	
    public Personne(Long idpersonne, String nom) {
        this.idpersonne = idpersonne;
        this.nom = nom;
    }
    public Personne(Long idpersonne, String nom, Date datenaissance, String lieunaissance, String biographie, String photourl, Set<Personnage> personnages, Set<Film> films) {
       this.idpersonne = idpersonne;
       this.nom = nom;
       this.datenaissance = datenaissance;
       this.lieunaissance = lieunaissance;
       this.biographie = biographie;
       this.photourl = photourl;
       this.personnages = personnages;
       this.films = films;
    }
    
    
    public Long getIdpersonne() {
        return this.idpersonne;
    }
    public void setIdpersonne(Long idpersonne) {
        this.idpersonne = idpersonne;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Date getDatenaissance() {
        return this.datenaissance;
    }
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }
    public String getLieunaissance() {
        return this.lieunaissance;
    }
    public void setLieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }
    public String getBiographie() {
        return this.biographie;
    }
    public void setBiographie(String biographie) {
        this.biographie = biographie;
    } 
    public String getPhotourl() {
        return this.photourl;
    }
    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
    public Set<Personnage> getPersonnages() {
        return this.personnages;
    }
    public void setPersonnages(Set<Personnage> personnages) {
        this.personnages = personnages;
    }
    public Set<Film> getFilms() {
        return this.films;
    }
    public void setFilms(Set<Film> films) {
        this.films = films;
    }
    
}


