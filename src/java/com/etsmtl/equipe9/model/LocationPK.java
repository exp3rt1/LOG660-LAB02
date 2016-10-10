/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicolas Desktop
 */
@Embeddable
public class LocationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COURRIELCLIENT")
    private String courrielclient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDEXEMPLAIRE")
    private BigInteger idexemplaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATELOCATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelocation;

    public LocationPK() {
    }

    public LocationPK(String courrielclient, BigInteger idexemplaire, Date datelocation) {
        this.courrielclient = courrielclient;
        this.idexemplaire = idexemplaire;
        this.datelocation = datelocation;
    }

    public String getCourrielclient() {
        return courrielclient;
    }

    public void setCourrielclient(String courrielclient) {
        this.courrielclient = courrielclient;
    }

    public BigInteger getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(BigInteger idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

    public Date getDatelocation() {
        return datelocation;
    }

    public void setDatelocation(Date datelocation) {
        this.datelocation = datelocation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courrielclient != null ? courrielclient.hashCode() : 0);
        hash += (idexemplaire != null ? idexemplaire.hashCode() : 0);
        hash += (datelocation != null ? datelocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationPK)) {
            return false;
        }
        LocationPK other = (LocationPK) object;
        if ((this.courrielclient == null && other.courrielclient != null) || (this.courrielclient != null && !this.courrielclient.equals(other.courrielclient))) {
            return false;
        }
        if ((this.idexemplaire == null && other.idexemplaire != null) || (this.idexemplaire != null && !this.idexemplaire.equals(other.idexemplaire))) {
            return false;
        }
        if ((this.datelocation == null && other.datelocation != null) || (this.datelocation != null && !this.datelocation.equals(other.datelocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LocationPK[ courrielclient=" + courrielclient + ", idexemplaire=" + idexemplaire + ", datelocation=" + datelocation + " ]";
    }
    
}
