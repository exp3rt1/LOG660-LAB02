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
public class MaVueCorrelationPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFILM1")
    private Long idfilm1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFILM2")
    private Long idfilm2;

    public MaVueCorrelationPK() {
    }

    public MaVueCorrelationPK(Long idfilm1, Long idfilm2) {
        this.idfilm1 = idfilm1;
        this.idfilm2 = idfilm2;
    }

    public Long getIdfilm1() {
        return idfilm1;
    }

    public void setIdfilm1(Long idfilm1) {
        this.idfilm1 = idfilm1;
    }

    public Long getIdfilm2() {
        return idfilm2;
    }

    public void setIdfilm2(Long idfilm2) {
        this.idfilm2 = idfilm2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfilm1 != null ? idfilm1.hashCode() : 0);
        hash += (idfilm2 != null ? idfilm2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaVueCorrelationPK)) {
            return false;
        }
        MaVueCorrelationPK other = (MaVueCorrelationPK) object;
        if ((this.idfilm1 == null && other.idfilm1 != null) || (this.idfilm1 != null && !this.idfilm1.equals(other.idfilm1))) {
            return false;
        }
        if ((this.idfilm2 == null && other.idfilm2 != null) || (this.idfilm2 != null && !this.idfilm2.equals(other.idfilm2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.etsmtl.equipe9.model.MaVueCorrelationPK[ idfilm1=" + idfilm1 + ", idfilm2=" + idfilm2 + " ]";
    }
    
}
