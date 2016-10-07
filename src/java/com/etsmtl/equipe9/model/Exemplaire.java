package com.etsmtl.equipe9.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EXEMPLAIRE",schema="EQUIPE9")
public class Exemplaire  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDEXEMPLAIRE", unique=true, nullable=false)
    private Long idexemplaire;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFILM")
    private Film film;
    
    @Column(name="ESTLOUE", nullable=false)
    private boolean estloue;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="exemplaire")
    private Set<Location> locations = new HashSet<Location>(0);

    public Exemplaire() {}
	
    public Exemplaire(Long idexemplaire, boolean estloue) {
        this.idexemplaire = idexemplaire;
        this.estloue = estloue;
    }
    public Exemplaire(Long idexemplaire, Film film, boolean estloue, Set<Location> locations) {
       this.idexemplaire = idexemplaire;
       this.film = film;
       this.estloue = estloue;
       this.locations = locations;
    }
    
    
    public Long getIdexemplaire() {
        return this.idexemplaire;
    }
    public void setIdexemplaire(Long idexemplaire) {
        this.idexemplaire = idexemplaire;
    }
    public Film getFilm() {
        return this.film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public boolean isEstloue() {
        return this.estloue;
    }    
    public void setEstloue(boolean estloue) {
        this.estloue = estloue;
    }
    public Set<Location> getLocations() {
        return this.locations;
    }  
    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
    
}


