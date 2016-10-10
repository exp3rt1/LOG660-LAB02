/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "PAYS", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")
    , @NamedQuery(name = "Pays.findByIdpays", query = "SELECT p FROM Pays p WHERE p.idpays = :idpays")
    , @NamedQuery(name = "Pays.findByNom", query = "SELECT p FROM Pays p WHERE p.nom = :nom")})
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPAYS")
    private BigDecimal idpays;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOM")
    private String nom;
    @ManyToMany(mappedBy = "paysCollection")
    private Collection<Film> filmCollection;

    public Pays() {
    }

    public Pays(BigDecimal idpays) {
        this.idpays = idpays;
    }

    public Pays(BigDecimal idpays, String nom) {
        this.idpays = idpays;
        this.nom = nom;
    }

    public BigDecimal getIdpays() {
        return idpays;
    }

    public void setIdpays(BigDecimal idpays) {
        this.idpays = idpays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Film> getFilmCollection() {
        return filmCollection;
    }

    public void setFilmCollection(Collection<Film> filmCollection) {
        this.filmCollection = filmCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpays != null ? idpays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.idpays == null && other.idpays != null) || (this.idpays != null && !this.idpays.equals(other.idpays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pays[ idpays=" + idpays + " ]";
    }
    
}
