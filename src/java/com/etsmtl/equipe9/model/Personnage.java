/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "PERSONNAGE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnage.findAll", query = "SELECT p FROM Personnage p")
    , @NamedQuery(name = "Personnage.findByIdfilm", query = "SELECT p FROM Personnage p WHERE p.personnagePK.idfilm = :idfilm")
    , @NamedQuery(name = "Personnage.findByIdacteur", query = "SELECT p FROM Personnage p WHERE p.personnagePK.idacteur = :idacteur")
    , @NamedQuery(name = "Personnage.findByNompersonnage", query = "SELECT p FROM Personnage p WHERE p.nompersonnage = :nompersonnage")})
public class Personnage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonnagePK personnagePK;
    @Size(max = 500)
    @Column(name = "NOMPERSONNAGE")
    private String nompersonnage;
    @JoinColumn(name = "IDFILM", referencedColumnName = "IDFILM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Film film;
    @JoinColumn(name = "IDACTEUR", referencedColumnName = "IDPERSONNE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personne personne;

    public Personnage() {
    }

    public Personnage(PersonnagePK personnagePK) {
        this.personnagePK = personnagePK;
    }

    public Personnage(BigInteger idfilm, BigInteger idacteur) {
        this.personnagePK = new PersonnagePK(idfilm, idacteur);
    }

    public PersonnagePK getPersonnagePK() {
        return personnagePK;
    }

    public void setPersonnagePK(PersonnagePK personnagePK) {
        this.personnagePK = personnagePK;
    }

    public String getNompersonnage() {
        return nompersonnage;
    }

    public void setNompersonnage(String nompersonnage) {
        this.nompersonnage = nompersonnage;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personnagePK != null ? personnagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnage)) {
            return false;
        }
        Personnage other = (Personnage) object;
        if ((this.personnagePK == null && other.personnagePK != null) || (this.personnagePK != null && !this.personnagePK.equals(other.personnagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Personnage[ personnagePK=" + personnagePK + " ]";
    }
    
}
