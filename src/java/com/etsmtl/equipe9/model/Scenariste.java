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
@Table(name = "SCENARISTE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scenariste.findAll", query = "SELECT s FROM Scenariste s")
    , @NamedQuery(name = "Scenariste.findByIdscenariste", query = "SELECT s FROM Scenariste s WHERE s.idscenariste = :idscenariste")
    , @NamedQuery(name = "Scenariste.findByNom", query = "SELECT s FROM Scenariste s WHERE s.nom = :nom")})
public class Scenariste implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSCENARISTE")
    private BigDecimal idscenariste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOM")
    private String nom;
    @JoinColumn(name = "IDFILM", referencedColumnName = "IDFILM")
    @ManyToOne
    private Film idfilm;

    public Scenariste() {
    }

    public Scenariste(BigDecimal idscenariste) {
        this.idscenariste = idscenariste;
    }

    public Scenariste(BigDecimal idscenariste, String nom) {
        this.idscenariste = idscenariste;
        this.nom = nom;
    }

    public BigDecimal getIdscenariste() {
        return idscenariste;
    }

    public void setIdscenariste(BigDecimal idscenariste) {
        this.idscenariste = idscenariste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        hash += (idscenariste != null ? idscenariste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scenariste)) {
            return false;
        }
        Scenariste other = (Scenariste) object;
        if ((this.idscenariste == null && other.idscenariste != null) || (this.idscenariste != null && !this.idscenariste.equals(other.idscenariste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Scenariste[ idscenariste=" + idscenariste + " ]";
    }
    
}
