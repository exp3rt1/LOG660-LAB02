package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MA_VUE_CORRELATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaVueCorrelation.findAll", query = "SELECT m FROM MaVueCorrelation m")
    , @NamedQuery(name = "MaVueCorrelation.findByIdfilm1", query = "SELECT m FROM MaVueCorrelation m WHERE m.maVueCorrelationPK.idfilm1 = :idfilm1")
    , @NamedQuery(name = "MaVueCorrelation.findByIdfilm2", query = "SELECT m FROM MaVueCorrelation m WHERE m.maVueCorrelationPK.idfilm2 = :idfilm2")
    , @NamedQuery(name = "MaVueCorrelation.findByNbCorr", query = "SELECT m FROM MaVueCorrelation m WHERE m.nbCorr = :nbCorr")
    , @NamedQuery(name = "MaVueCorrelation.findByCorrelation", query = "SELECT m FROM MaVueCorrelation m WHERE m.correlation = :correlation")})
public class MaVueCorrelation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaVueCorrelationPK maVueCorrelationPK;
    @Column(name = "NB_CORR")
    private BigInteger nbCorr;
    @Column(name = "CORRELATION")
    private BigInteger correlation;

    public MaVueCorrelation() {
    }

    public MaVueCorrelation(MaVueCorrelationPK maVueCorrelationPK) {
        this.maVueCorrelationPK = maVueCorrelationPK;
    }

    public MaVueCorrelation(Long idfilm1, Long idfilm2) {
        this.maVueCorrelationPK = new MaVueCorrelationPK(idfilm1, idfilm2);
    }

    public MaVueCorrelationPK getMaVueCorrelationPK() {
        return maVueCorrelationPK;
    }

    public void setMaVueCorrelationPK(MaVueCorrelationPK maVueCorrelationPK) {
        this.maVueCorrelationPK = maVueCorrelationPK;
    }

    public BigInteger getNbCorr() {
        return nbCorr;
    }

    public void setNbCorr(BigInteger nbCorr) {
        this.nbCorr = nbCorr;
    }

    public BigInteger getCorrelation() {
        return correlation;
    }

    public void setCorrelation(BigInteger correlation) {
        this.correlation = correlation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maVueCorrelationPK != null ? maVueCorrelationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaVueCorrelation)) {
            return false;
        }
        MaVueCorrelation other = (MaVueCorrelation) object;
        if ((this.maVueCorrelationPK == null && other.maVueCorrelationPK != null) || (this.maVueCorrelationPK != null && !this.maVueCorrelationPK.equals(other.maVueCorrelationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.etsmtl.equipe9.model.MaVueCorrelation[ maVueCorrelationPK=" + maVueCorrelationPK + " ]";
    }
    
}
