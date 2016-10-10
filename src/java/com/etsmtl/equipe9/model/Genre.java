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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "GENRE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g")
    , @NamedQuery(name = "Genre.findByIdgenre", query = "SELECT g FROM Genre g WHERE g.idgenre = :idgenre")
    , @NamedQuery(name = "Genre.findByNom", query = "SELECT g FROM Genre g WHERE g.nom = :nom")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDGENRE")
    private BigDecimal idgenre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOM")
    private String nom;
    @JoinTable(name = "FILMGENRE", joinColumns = {
        @JoinColumn(name = "IDGENRE", referencedColumnName = "IDGENRE")}, inverseJoinColumns = {
        @JoinColumn(name = "IDFILM", referencedColumnName = "IDFILM")})
    @ManyToMany
    private Collection<Film> filmCollection;

    public Genre() {
    }

    public Genre(BigDecimal idgenre) {
        this.idgenre = idgenre;
    }

    public Genre(BigDecimal idgenre, String nom) {
        this.idgenre = idgenre;
        this.nom = nom;
    }

    public BigDecimal getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(BigDecimal idgenre) {
        this.idgenre = idgenre;
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
        hash += (idgenre != null ? idgenre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.idgenre == null && other.idgenre != null) || (this.idgenre != null && !this.idgenre.equals(other.idgenre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Genre[ idgenre=" + idgenre + " ]";
    }
    
}
