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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "FORFAIT", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Forfait.findAll", query = "SELECT f FROM Forfait f")
    , @NamedQuery(name = "Forfait.findByType", query = "SELECT f FROM Forfait f WHERE f.type = :type")
    , @NamedQuery(name = "Forfait.findByCoutparmois", query = "SELECT f FROM Forfait f WHERE f.coutparmois = :coutparmois")
    , @NamedQuery(name = "Forfait.findByLocationsmax", query = "SELECT f FROM Forfait f WHERE f.locationsmax = :locationsmax")
    , @NamedQuery(name = "Forfait.findByDureemax", query = "SELECT f FROM Forfait f WHERE f.dureemax = :dureemax")})
public class Forfait implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TYPE")
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COUTPARMOIS")
    private BigDecimal coutparmois;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATIONSMAX")
    private BigInteger locationsmax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUREEMAX")
    private BigInteger dureemax;
    @OneToMany(mappedBy = "typeforfait")
    private Collection<Client> clientCollection;

    public Forfait() {
    }

    public Forfait(String type) {
        this.type = type;
    }

    public Forfait(String type, BigDecimal coutparmois, BigInteger locationsmax, BigInteger dureemax) {
        this.type = type;
        this.coutparmois = coutparmois;
        this.locationsmax = locationsmax;
        this.dureemax = dureemax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCoutparmois() {
        return coutparmois;
    }

    public void setCoutparmois(BigDecimal coutparmois) {
        this.coutparmois = coutparmois;
    }

    public BigInteger getLocationsmax() {
        return locationsmax;
    }

    public void setLocationsmax(BigInteger locationsmax) {
        this.locationsmax = locationsmax;
    }

    public BigInteger getDureemax() {
        return dureemax;
    }

    public void setDureemax(BigInteger dureemax) {
        this.dureemax = dureemax;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (type != null ? type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forfait)) {
            return false;
        }
        Forfait other = (Forfait) object;
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Forfait[ type=" + type + " ]";
    }
    
}
