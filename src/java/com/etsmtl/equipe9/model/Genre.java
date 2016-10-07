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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="GENRE",schema="EQUIPE9")
public class Genre  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDGENRE", unique=true, nullable=false, precision=22, scale=0)
    private Long idgenre;
    
    @Column(name="NOM", nullable=false, length=30)
    private String nom;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="FILMGENRE", schema="EQUIPE9", joinColumns = { 
    @JoinColumn(name="IDGENRE", nullable=false, updatable=false) }, 
    inverseJoinColumns = { 
    @JoinColumn(name="IDFILM", nullable=false, updatable=false) })
    private Set<Film> films = new HashSet<>(0);

    public Genre() {}
	
    public Genre(Long idgenre, String nom) {
        this.idgenre = idgenre;
        this.nom = nom;
    }
    public Genre(Long idgenre, String nom, Set<Film> films) {
       this.idgenre = idgenre;
       this.nom = nom;
       this.films = films;
    }
    
    
    public Long getIdgenre() {
        return this.idgenre;
    }
    public void setIdgenre(Long idgenre) {
        this.idgenre = idgenre;
    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Set<Film> getFilms() {
        return this.films;
    }
    public void setFilms(Set<Film> films) {
        this.films = films;
    }
    
}


