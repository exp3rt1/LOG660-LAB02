package com.etsmtl.equipe9.model;
// Generated 2016-10-03 15:53:41 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Film generated by hbm2java
 */
@Entity
@Table(name="FILM"
    ,schema="EQUIPE9"
)@XmlRootElement

public class Film  implements java.io.Serializable {


     private BigDecimal idfilm;
     private Personne personne;
     private String titre;
     private BigDecimal anneesortie;
     private String langueoriginale;
     private String resumescenario;
     private BigDecimal duree;
     private Set<Pays> payses = new HashSet<Pays>(0);
     private Set<Lienmedia> lienmedias = new HashSet<Lienmedia>(0);
     private Set<Personnage> personnages = new HashSet<Personnage>(0);
     private Set<Genre> genres = new HashSet<Genre>(0);
     private Set<Exemplaire> exemplaires = new HashSet<Exemplaire>(0);
     private Set<Scenariste> scenaristes = new HashSet<Scenariste>(0);

    public Film() {
    }

	
    public Film(BigDecimal idfilm, String titre, BigDecimal anneesortie, String resumescenario) {
        this.idfilm = idfilm;
        this.titre = titre;
        this.anneesortie = anneesortie;
        this.resumescenario = resumescenario;
    }
    public Film(BigDecimal idfilm, Personne personne, String titre, BigDecimal anneesortie, String langueoriginale, String resumescenario, BigDecimal duree, Set<Pays> payses, Set<Lienmedia> lienmedias, Set<Personnage> personnages, Set<Genre> genres, Set<Exemplaire> exemplaires, Set<Scenariste> scenaristes) {
       this.idfilm = idfilm;
       this.personne = personne;
       this.titre = titre;
       this.anneesortie = anneesortie;
       this.langueoriginale = langueoriginale;
       this.resumescenario = resumescenario;
       this.duree = duree;
       this.payses = payses;
       this.lienmedias = lienmedias;
       this.personnages = personnages;
       this.genres = genres;
       this.exemplaires = exemplaires;
       this.scenaristes = scenaristes;
    }
   
     @Id 

    
    @Column(name="IDFILM", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getIdfilm() {
        return this.idfilm;
    }
    
    public void setIdfilm(BigDecimal idfilm) {
        this.idfilm = idfilm;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDREALISATEUR")
    public Personne getPersonne() {
        return this.personne;
    }
    
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    
    @Column(name="TITRE", nullable=false, length=200)
    public String getTitre() {
        return this.titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    @Column(name="ANNEESORTIE", nullable=false, precision=22, scale=0)
    public BigDecimal getAnneesortie() {
        return this.anneesortie;
    }
    
    public void setAnneesortie(BigDecimal anneesortie) {
        this.anneesortie = anneesortie;
    }

    
    @Column(name="LANGUEORIGINALE", length=20)
    public String getLangueoriginale() {
        return this.langueoriginale;
    }
    
    public void setLangueoriginale(String langueoriginale) {
        this.langueoriginale = langueoriginale;
    }

    
    @Column(name="RESUMESCENARIO", nullable=false, length=2000)
    public String getResumescenario() {
        return this.resumescenario;
    }
    
    public void setResumescenario(String resumescenario) {
        this.resumescenario = resumescenario;
    }

    
    @Column(name="DUREE", precision=22, scale=0)
    public BigDecimal getDuree() {
        return this.duree;
    }
    
    public void setDuree(BigDecimal duree) {
        this.duree = duree;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="FILMPAYS", schema="EQUIPE9", joinColumns = { 
        @JoinColumn(name="IDFILM", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="IDPAYS", nullable=false, updatable=false) })
    public Set<Pays> getPayses() {
        return this.payses;
    }
    
    public void setPayses(Set<Pays> payses) {
        this.payses = payses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    public Set<Lienmedia> getLienmedias() {
        return this.lienmedias;
    }
    
    public void setLienmedias(Set<Lienmedia> lienmedias) {
        this.lienmedias = lienmedias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    public Set<Personnage> getPersonnages() {
        return this.personnages;
    }
    
    public void setPersonnages(Set<Personnage> personnages) {
        this.personnages = personnages;
    }

@ManyToMany(fetch=FetchType.LAZY, mappedBy="films")
    public Set<Genre> getGenres() {
        return this.genres;
    }
    
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    public Set<Exemplaire> getExemplaires() {
        return this.exemplaires;
    }
    
    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    public Set<Scenariste> getScenaristes() {
        return this.scenaristes;
    }
    
    public void setScenaristes(Set<Scenariste> scenaristes) {
        this.scenaristes = scenaristes;
    }




}

