/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "MA_VUE_MOYENNE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaVueMoyenne.findAll", query = "SELECT m FROM MaVueMoyenne m")
    , @NamedQuery(name = "MaVueMoyenne.findByIdfilm", query = "SELECT m FROM MaVueMoyenne m WHERE m.idfilm = :idfilm")
    , @NamedQuery(name = "MaVueMoyenne.findByCote", query = "SELECT m FROM MaVueMoyenne m WHERE m.cote = :cote")})
public class MaVueMoyenne implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFILM")
    private Long idfilm;
    @Column(name = "COTE")
    private BigDecimal cote;

    public MaVueMoyenne() {
    }

    public MaVueMoyenne(Long idfilm) {
        this.idfilm = idfilm;
    }

    public Long getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(Long idfilm) {
        this.idfilm = idfilm;
    }

    public BigDecimal getCote() {
        return cote;
    }

    public void setCote(BigDecimal cote) {
        this.cote = cote;
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
        if (!(object instanceof MaVueMoyenne)) {
            return false;
        }
        MaVueMoyenne other = (MaVueMoyenne) object;
        if ((this.idfilm == null && other.idfilm != null) || (this.idfilm != null && !this.idfilm.equals(other.idfilm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.etsmtl.equipe9.model.MaVueMoyenne[ idfilm=" + idfilm + " ]";
    }
    
}
