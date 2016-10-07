package com.etsmtl.equipe9.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SCENARISTE",schema="EQUIPE9")
public class Scenariste  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDSCENARISTE", unique=true, nullable=false)
    private Long idscenariste;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFILM")
    private Film film;
    
    @Column(name="NOM", nullable=false, length=50)
    private String nom;

    public Scenariste() {}
	
    public Scenariste(Long idscenariste, String nom) {
        this.idscenariste = idscenariste;
        this.nom = nom;
    }
    public Scenariste(Long idscenariste, Film film, String nom) {
       this.idscenariste = idscenariste;
       this.film = film;
       this.nom = nom;
    }
   
    
    public Long getIdscenariste() {
        return this.idscenariste;
    }
    public void setIdscenariste(Long idscenariste) {
        this.idscenariste = idscenariste;
    }
    public Film getFilm() {
        return this.film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }    
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}


