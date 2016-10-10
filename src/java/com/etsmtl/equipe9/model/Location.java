/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "LOCATION", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
    , @NamedQuery(name = "Location.findByCourrielclient", query = "SELECT l FROM Location l WHERE l.locationPK.courrielclient = :courrielclient")
    , @NamedQuery(name = "Location.findByIdexemplaire", query = "SELECT l FROM Location l WHERE l.locationPK.idexemplaire = :idexemplaire")
    , @NamedQuery(name = "Location.findByDatelocation", query = "SELECT l FROM Location l WHERE l.locationPK.datelocation = :datelocation")
    , @NamedQuery(name = "Location.findByDateretour", query = "SELECT l FROM Location l WHERE l.dateretour = :dateretour")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocationPK locationPK;
    @Column(name = "DATERETOUR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateretour;
    @JoinColumn(name = "COURRIELCLIENT", referencedColumnName = "COURRIEL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Client client;
    @JoinColumn(name = "IDEXEMPLAIRE", referencedColumnName = "IDEXEMPLAIRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Exemplaire exemplaire;

    public Location() {
    }

    public Location(LocationPK locationPK) {
        this.locationPK = locationPK;
    }

    public Location(String courrielclient, BigInteger idexemplaire, Date datelocation) {
        this.locationPK = new LocationPK(courrielclient, idexemplaire, datelocation);
    }

    public LocationPK getLocationPK() {
        return locationPK;
    }

    public void setLocationPK(LocationPK locationPK) {
        this.locationPK = locationPK;
    }

    public Date getDateretour() {
        return dateretour;
    }

    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationPK != null ? locationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationPK == null && other.locationPK != null) || (this.locationPK != null && !this.locationPK.equals(other.locationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Location[ locationPK=" + locationPK + " ]";
    }
    
}
