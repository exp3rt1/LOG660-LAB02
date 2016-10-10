/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
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
@Table(name = "CARTECREDIT", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cartecredit.findAll", query = "SELECT c FROM Cartecredit c")
    , @NamedQuery(name = "Cartecredit.findByNumero", query = "SELECT c FROM Cartecredit c WHERE c.numero = :numero")
    , @NamedQuery(name = "Cartecredit.findByMoisexpiration", query = "SELECT c FROM Cartecredit c WHERE c.moisexpiration = :moisexpiration")
    , @NamedQuery(name = "Cartecredit.findByAnneeexpiration", query = "SELECT c FROM Cartecredit c WHERE c.anneeexpiration = :anneeexpiration")
    , @NamedQuery(name = "Cartecredit.findByCvv", query = "SELECT c FROM Cartecredit c WHERE c.cvv = :cvv")
    , @NamedQuery(name = "Cartecredit.findByType", query = "SELECT c FROM Cartecredit c WHERE c.type = :type")})
public class Cartecredit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOISEXPIRATION")
    private BigInteger moisexpiration;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANNEEEXPIRATION")
    private BigInteger anneeexpiration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CVV")
    private String cvv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TYPE")
    private String type;
    @OneToMany(mappedBy = "numerocartecredit")
    private Collection<Client> clientCollection;

    public Cartecredit() {
    }

    public Cartecredit(String numero) {
        this.numero = numero;
    }

    public Cartecredit(String numero, BigInteger moisexpiration, BigInteger anneeexpiration, String cvv, String type) {
        this.numero = numero;
        this.moisexpiration = moisexpiration;
        this.anneeexpiration = anneeexpiration;
        this.cvv = cvv;
        this.type = type;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigInteger getMoisexpiration() {
        return moisexpiration;
    }

    public void setMoisexpiration(BigInteger moisexpiration) {
        this.moisexpiration = moisexpiration;
    }

    public BigInteger getAnneeexpiration() {
        return anneeexpiration;
    }

    public void setAnneeexpiration(BigInteger anneeexpiration) {
        this.anneeexpiration = anneeexpiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartecredit)) {
            return false;
        }
        Cartecredit other = (Cartecredit) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cartecredit[ numero=" + numero + " ]";
    }
    
}
