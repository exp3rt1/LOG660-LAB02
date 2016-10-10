/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "LIENMEDIA", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lienmedia.findAll", query = "SELECT l FROM Lienmedia l")
    , @NamedQuery(name = "Lienmedia.findByIdlienmedia", query = "SELECT l FROM Lienmedia l WHERE l.idlienmedia = :idlienmedia")
    , @NamedQuery(name = "Lienmedia.findByUrl", query = "SELECT l FROM Lienmedia l WHERE l.url = :url")
    , @NamedQuery(name = "Lienmedia.findByType", query = "SELECT l FROM Lienmedia l WHERE l.type = :type")})
public class Lienmedia implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLIENMEDIA")
    private BigDecimal idlienmedia;
    @Size(max = 2000)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TYPE")
    private String type;
    @JoinColumn(name = "IDFILM", referencedColumnName = "IDFILM")
    @ManyToOne
    private Film idfilm;

    public Lienmedia() {
    }

    public Lienmedia(BigDecimal idlienmedia) {
        this.idlienmedia = idlienmedia;
    }

    public Lienmedia(BigDecimal idlienmedia, String type) {
        this.idlienmedia = idlienmedia;
        this.type = type;
    }

    public BigDecimal getIdlienmedia() {
        return idlienmedia;
    }

    public void setIdlienmedia(BigDecimal idlienmedia) {
        this.idlienmedia = idlienmedia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (idlienmedia != null ? idlienmedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lienmedia)) {
            return false;
        }
        Lienmedia other = (Lienmedia) object;
        if ((this.idlienmedia == null && other.idlienmedia != null) || (this.idlienmedia != null && !this.idlienmedia.equals(other.idlienmedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Lienmedia[ idlienmedia=" + idlienmedia + " ]";
    }
    
}
