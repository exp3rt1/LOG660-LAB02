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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="PAYS",schema=Configuration.BD_SCHEMA)
public class Pays  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDPAYS", unique=true, nullable=false)
    private Long idpays;
    
    @Column(name="NOM", nullable=false, length=50)
    private String nom;
    
    @ManyToMany(fetch=FetchType.LAZY, mappedBy="pays")
    private Set<Film> films = new HashSet<>(0);

    public Pays() {
    }
    
    public Pays(Long idpays, String nom) {
        this.idpays = idpays;
        this.nom = nom;
    }
    public Pays(Long idpays, String nom, Set<Film> films) {
       this.idpays = idpays;
       this.nom = nom;
       this.films = films;
    }
   
    
    public Long getIdpays() {
        return this.idpays;
    }
    public void setIdpays(Long idpays) {
        this.idpays = idpays;
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


