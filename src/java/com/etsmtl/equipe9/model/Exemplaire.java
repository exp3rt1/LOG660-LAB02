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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "EXEMPLAIRE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exemplaire.findAll", query = "SELECT e FROM Exemplaire e")
    , @NamedQuery(name = "Exemplaire.findByIdexemplaire", query = "SELECT e FROM Exemplaire e WHERE e.idexemplaire = :idexemplaire")
    , @NamedQuery(name = "Exemplaire.findByEstloue", query = "SELECT e FROM Exemplaire e WHERE e.estloue = :estloue")})
public class Exemplaire implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEXEMPLAIRE")
    private BigDecimal idexemplaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTLOUE")
    private short estloue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exemplaire")
    private Collection<Location> locationCollection;
    @JoinColumn(name = "IDFILM", referencedColumnName = "IDFILM")
    @ManyToOne
    private Film idfilm;

    public Exemplaire() {
    }

    public Exemplaire(BigDecimal idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

    public Exemplaire(BigDecimal idexemplaire, short estloue) {
        this.idexemplaire = idexemplaire;
        this.estloue = estloue;
    }

    public BigDecimal getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(BigDecimal idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

    public short getEstloue() {
        return estloue;
    }

    public void setEstloue(short estloue) {
        this.estloue = estloue;
    }

    @XmlTransient
    public Collection<Location> getLocationCollection() {
        return locationCollection;
    }

    public void setLocationCollection(Collection<Location> locationCollection) {
        this.locationCollection = locationCollection;
    }

    public Film getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(Film idfilm) {
        this.idfilm = idfilm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexemplaire != null ? idexemplaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exemplaire)) {
            return false;
        }
        Exemplaire other = (Exemplaire) object;
        if ((this.idexemplaire == null && other.idexemplaire != null) || (this.idexemplaire != null && !this.idexemplaire.equals(other.idexemplaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Exemplaire[ idexemplaire=" + idexemplaire + " ]";
    }
    
}
