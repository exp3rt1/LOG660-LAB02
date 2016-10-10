/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "PERSONNE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p")
    , @NamedQuery(name = "Personne.findByIdpersonne", query = "SELECT p FROM Personne p WHERE p.idpersonne = :idpersonne")
    , @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom")
    , @NamedQuery(name = "Personne.findByDatenaissance", query = "SELECT p FROM Personne p WHERE p.datenaissance = :datenaissance")
    , @NamedQuery(name = "Personne.findByLieunaissance", query = "SELECT p FROM Personne p WHERE p.lieunaissance = :lieunaissance")
    , @NamedQuery(name = "Personne.findByPhotourl", query = "SELECT p FROM Personne p WHERE p.photourl = :photourl")})
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPERSONNE")
    private BigDecimal idpersonne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOM")
    private String nom;
    @Column(name = "DATENAISSANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datenaissance;
    @Size(max = 100)
    @Column(name = "LIEUNAISSANCE")
    private String lieunaissance;
    @Lob
    @Column(name = "BIOGRAPHIE")
    private String biographie;
    @Size(max = 2000)
    @Column(name = "PHOTOURL")
    private String photourl;
    @OneToMany(mappedBy = "idrealisateur")
    private Collection<Film> filmCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne")
    private Collection<Personnage> personnageCollection;

    public Personne() {
    }

    public Personne(BigDecimal idpersonne) {
        this.idpersonne = idpersonne;
    }

    public Personne(BigDecimal idpersonne, String nom) {
        this.idpersonne = idpersonne;
        this.nom = nom;
    }

    public BigDecimal getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(BigDecimal idpersonne) {
        this.idpersonne = idpersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getLieunaissance() {
        return lieunaissance;
    }

    public void setLieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    @XmlTransient
    public Collection<Film> getFilmCollection() {
        return filmCollection;
    }

    public void setFilmCollection(Collection<Film> filmCollection) {
        this.filmCollection = filmCollection;
    }

    @XmlTransient
    public Collection<Personnage> getPersonnageCollection() {
        return personnageCollection;
    }

    public void setPersonnageCollection(Collection<Personnage> personnageCollection) {
        this.personnageCollection = personnageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonne != null ? idpersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.idpersonne == null && other.idpersonne != null) || (this.idpersonne != null && !this.idpersonne.equals(other.idpersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Personne[ idpersonne=" + idpersonne + " ]";
    }
    
}
