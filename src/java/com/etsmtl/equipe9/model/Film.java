package com.etsmtl.equipe9.model;


import com.etsmtl.equipe9.service.Configuration;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FILM",schema=Configuration.BD_SCHEMA)
public class Film  implements java.io.Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDFILM", unique=true, nullable=false)
    private Long idfilm;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDREALISATEUR")
    private Personne realisateur;
    
    @Column(name="TITRE", nullable=false, length=200)
    private String titre;
    
    @Column(name="ANNEESORTIE", nullable=false)
    private Integer anneesortie;
    
    @Column(name="LANGUEORIGINALE", length=20)
    private String langueoriginale;
    
    @Column(name="RESUMESCENARIO", nullable=false, length=2000)
    private String resumescenario;
    
    @Column(name="DUREE")
    private Integer duree;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="FILMPAYS", schema=Configuration.BD_SCHEMA, 
        joinColumns = { 
            @JoinColumn(name="IDFILM", nullable=false, updatable=false) }, 
        inverseJoinColumns = { 
            @JoinColumn(name="IDPAYS", nullable=false, updatable=false) })
    private Set<Pays> pays = new HashSet<>(0);
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    private Set<Lienmedia> liensmedia = new HashSet<>(0);
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    private Set<Personnage> personnages = new HashSet<>(0);
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="FILMGENRE", schema=Configuration.BD_SCHEMA, joinColumns = { 
        @JoinColumn(name="IDFILM", nullable=false, updatable=false) }, 
        inverseJoinColumns = { 
        @JoinColumn(name="IDGENRE", nullable=false, updatable=false) })
    private Set<Genre> genres = new HashSet<>(0);
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    private Set<Exemplaire> exemplaires = new HashSet<>(0);
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="film")
    private Set<Scenariste> scenaristes = new HashSet<>(0);

    public Film() {}
	
    public Film(Long idfilm, String titre, Integer anneesortie, String resumescenario) {
        this.idfilm = idfilm;
        this.titre = titre;
        this.anneesortie = anneesortie;
        this.resumescenario = resumescenario;
    }
    public Film(Long idfilm, Personne realisateur, String titre, Integer anneesortie, String langueoriginale, String resumescenario, Integer duree, Set<Pays> pays, Set<Lienmedia> lienmedias, Set<Personnage> personnages, Set<Genre> genres, Set<Exemplaire> exemplaires, Set<Scenariste> scenaristes) {
       this.idfilm = idfilm;
       this.realisateur = realisateur;
       this.titre = titre;
       this.anneesortie = anneesortie;
       this.langueoriginale = langueoriginale;
       this.resumescenario = resumescenario;
       this.duree = duree;
       this.pays = pays;
       this.liensmedia = lienmedias;
       this.personnages = personnages;
       this.genres = genres;
       this.exemplaires = exemplaires;
       this.scenaristes = scenaristes;
    }
    
    
    public Long getIdfilm() {
        return this.idfilm;
    }  
    public void setIdfilm(Long idfilm) {
        this.idfilm = idfilm;
    }
    public Personne getRealisateur() {
        return this.realisateur;
    }
    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
    }
    public String getTitre() {
        return this.titre;
    } 
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public Integer getAnneesortie() {
        return this.anneesortie;
    }
    public void setAnneesortie(Integer anneesortie) {
        this.anneesortie = anneesortie;
    }
    public String getLangueoriginale() {
        return this.langueoriginale;
    }
    public void setLangueoriginale(String langueoriginale) {
        this.langueoriginale = langueoriginale;
    }
    public String getResumescenario() {
        return this.resumescenario;
    }
    public void setResumescenario(String resumescenario) {
        this.resumescenario = resumescenario;
    }
    public Integer getDuree() {
        return this.duree;
    }
    public void setDuree(Integer duree) {
        this.duree = duree;
    }
    public Set<Pays> getPays() {
        return this.pays;
    }
    public void setPays(Set<Pays> pays) {
        this.pays = pays;
    }
    public Set<Lienmedia> getLiensmedia() {
        return this.liensmedia;
    }
    public void setLiensmedia(Set<Lienmedia> liensmedia) {
        this.liensmedia = liensmedia;
    }
    public Set<Personnage> getPersonnages() {
        return this.personnages;
    }
    public void setPersonnages(Set<Personnage> personnages) {
        this.personnages = personnages;
    }
    public Set<Genre> getGenres() {
        return this.genres;
    }
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
    public Set<Exemplaire> getExemplaires() {
        return this.exemplaires;
    }
    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
    public Set<Scenariste> getScenaristes() {
        return this.scenaristes;
    }
    public void setScenaristes(Set<Scenariste> scenaristes) {
        this.scenaristes = scenaristes;
    }
    
}


