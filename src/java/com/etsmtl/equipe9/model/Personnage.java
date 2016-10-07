package com.etsmtl.equipe9.model;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PERSONNAGE",schema="EQUIPE9")
public class Personnage  implements java.io.Serializable {

    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="idfilm", column=@Column(name="IDFILM", nullable=false) ), 
        @AttributeOverride(name="idacteur", column=@Column(name="IDACTEUR", nullable=false) ) } )
    private PersonnageId id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFILM", nullable=false, insertable=false, updatable=false)
    private Film film;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDACTEUR", nullable=false, insertable=false, updatable=false)
    private Personne acteur;
    
    @Column(name="NOMPERSONNAGE", length=500)
    private String nompersonnage;

    public Personnage() {}

    public Personnage(PersonnageId id, Film film, Personne acteur) {
        this.id = id;
        this.film = film;
        this.acteur = acteur;
    }
    public Personnage(PersonnageId id, Film film, Personne acteur, String nompersonnage) {
       this.id = id;
       this.film = film;
       this.acteur = acteur;
       this.nompersonnage = nompersonnage;
    }
   
    
    public PersonnageId getId() {
        return this.id;
    } 
    public void setId(PersonnageId id) {
        this.id = id;
    }
    public Film getFilm() {
        return this.film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public Personne getActeur() {
        return this.acteur;
    }
    public void setActeur(Personne acteur) {
        this.acteur = acteur;
    }
    public String getNompersonnage() {
        return this.nompersonnage;
    }
    public void setNompersonnage(String nompersonnage) {
        this.nompersonnage = nompersonnage;
    }
    
}


