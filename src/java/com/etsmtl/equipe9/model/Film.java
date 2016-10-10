/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "FILM", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
    , @NamedQuery(name = "Film.findByIdfilm", query = "SELECT f FROM Film f WHERE f.idfilm = :idfilm")
    , @NamedQuery(name = "Film.findByTitre", query = "SELECT f FROM Film f WHERE f.titre = :titre")
    , @NamedQuery(name = "Film.findByAnneesortie", query = "SELECT f FROM Film f WHERE f.anneesortie = :anneesortie")
    , @NamedQuery(name = "Film.findByLangueoriginale", query = "SELECT f FROM Film f WHERE f.langueoriginale = :langueoriginale")
    , @NamedQuery(name = "Film.findByResumescenario", query = "SELECT f FROM Film f WHERE f.resumescenario = :resumescenario")
    , @NamedQuery(name = "Film.findByDuree", query = "SELECT f FROM Film f WHERE f.duree = :duree")})
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFILM")
    private BigDecimal idfilm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "TITRE")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANNEESORTIE")
    private BigInteger anneesortie;
    @Size(max = 20)
    @Column(name = "LANGUEORIGINALE")
    private String langueoriginale;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "RESUMESCENARIO")
    private String resumescenario;
    @Column(name = "DUREE")
    private BigInteger duree;
    @JoinTable(name = "FILMPAYS", joinColumns = {
        @JoinColumn(name = "IDFILM", referencedColumnName = "IDFILM")}, inverseJoinColumns = {
        @JoinColumn(name = "IDPAYS", referencedColumnName = "IDPAYS")})
    @ManyToMany
    private Collection<Pays> paysCollection;
    @ManyToMany(mappedBy = "filmCollection")
    private Collection<Genre> genreCollection;
    @OneToMany(mappedBy = "idfilm")
    private Collection<Exemplaire> exemplaireCollection;
    @JoinColumn(name = "IDREALISATEUR", referencedColumnName = "IDPERSONNE")
    @ManyToOne
    private Personne idrealisateur;
    @OneToMany(mappedBy = "idfilm")
    private Collection<Scenariste> scenaristeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private Collection<Personnage> personnageCollection;
    @OneToMany(mappedBy = "idfilm")
    private Collection<Lienmedia> lienmediaCollection;

    public Film() {
    }

    public Film(BigDecimal idfilm) {
        this.idfilm = idfilm;
    }

    public Film(BigDecimal idfilm, String titre, BigInteger anneesortie, String resumescenario) {
        this.idfilm = idfilm;
        this.titre = titre;
        this.anneesortie = anneesortie;
        this.resumescenario = resumescenario;
    }

    public BigDecimal getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(BigDecimal idfilm) {
        this.idfilm = idfilm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public BigInteger getAnneesortie() {
        return anneesortie;
    }

    public void setAnneesortie(BigInteger anneesortie) {
        this.anneesortie = anneesortie;
    }

    public String getLangueoriginale() {
        return langueoriginale;
    }

    public void setLangueoriginale(String langueoriginale) {
        this.langueoriginale = langueoriginale;
    }

    public String getResumescenario() {
        return resumescenario;
    }

    public void setResumescenario(String resumescenario) {
        this.resumescenario = resumescenario;
    }

    public BigInteger getDuree() {
        return duree;
    }

    public void setDuree(BigInteger duree) {
        this.duree = duree;
    }

    @XmlTransient
    public Collection<Pays> getPaysCollection() {
        return paysCollection;
    }

    public void setPaysCollection(Collection<Pays> paysCollection) {
        this.paysCollection = paysCollection;
    }

    @XmlTransient
    public Collection<Genre> getGenreCollection() {
        return genreCollection;
    }

    public void setGenreCollection(Collection<Genre> genreCollection) {
        this.genreCollection = genreCollection;
    }

    @XmlTransient
    public Collection<Exemplaire> getExemplaireCollection() {
        return exemplaireCollection;
    }

    public void setExemplaireCollection(Collection<Exemplaire> exemplaireCollection) {
        this.exemplaireCollection = exemplaireCollection;
    }

    public Personne getIdrealisateur() {
        return idrealisateur;
    }

    public void setIdrealisateur(Personne idrealisateur) {
        this.idrealisateur = idrealisateur;
    }

    @XmlTransient
    public Collection<Scenariste> getScenaristeCollection() {
        return scenaristeCollection;
    }

    public void setScenaristeCollection(Collection<Scenariste> scenaristeCollection) {
        this.scenaristeCollection = scenaristeCollection;
    }

    @XmlTransient
    public Collection<Personnage> getPersonnageCollection() {
        return personnageCollection;
    }

    public void setPersonnageCollection(Collection<Personnage> personnageCollection) {
        this.personnageCollection = personnageCollection;
    }

    @XmlTransient
    public Collection<Lienmedia> getLienmediaCollection() {
        return lienmediaCollection;
    }

    public void setLienmediaCollection(Collection<Lienmedia> lienmediaCollection) {
        this.lienmediaCollection = lienmediaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfilm != null ? idfilm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Film)) {
            return false;
        }
        Film other = (Film) object;
        if ((this.idfilm == null && other.idfilm != null) || (this.idfilm != null && !this.idfilm.equals(other.idfilm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Film[ idfilm=" + idfilm + " ]";
    }
    
}
