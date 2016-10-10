/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nicolas Desktop
 */
@Embeddable
public class PersonnagePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFILM")
    private BigInteger idfilm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDACTEUR")
    private BigInteger idacteur;

    public PersonnagePK() {
    }

    public PersonnagePK(BigInteger idfilm, BigInteger idacteur) {
        this.idfilm = idfilm;
        this.idacteur = idacteur;
    }

    public BigInteger getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(BigInteger idfilm) {
        this.idfilm = idfilm;
    }

    public BigInteger getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(BigInteger idacteur) {
        this.idacteur = idacteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfilm != null ? idfilm.hashCode() : 0);
        hash += (idacteur != null ? idacteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonnagePK)) {
            return false;
        }
        PersonnagePK other = (PersonnagePK) object;
        if ((this.idfilm == null && other.idfilm != null) || (this.idfilm != null && !this.idfilm.equals(other.idfilm))) {
            return false;
        }
        if ((this.idacteur == null && other.idacteur != null) || (this.idacteur != null && !this.idacteur.equals(other.idacteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PersonnagePK[ idfilm=" + idfilm + ", idacteur=" + idacteur + " ]";
    }
    
}
