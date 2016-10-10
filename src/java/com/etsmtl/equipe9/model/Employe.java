/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "EMPLOYE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e")
    , @NamedQuery(name = "Employe.findByMatriculeemploye", query = "SELECT e FROM Employe e WHERE e.matriculeemploye = :matriculeemploye")
    , @NamedQuery(name = "Employe.findByCourriel", query = "SELECT e FROM Employe e WHERE e.courriel = :courriel")
    , @NamedQuery(name = "Employe.findByNom", query = "SELECT e FROM Employe e WHERE e.nom = :nom")
    , @NamedQuery(name = "Employe.findByPrenom", query = "SELECT e FROM Employe e WHERE e.prenom = :prenom")
    , @NamedQuery(name = "Employe.findByMotpasse", query = "SELECT e FROM Employe e WHERE e.motpasse = :motpasse")
    , @NamedQuery(name = "Employe.findByNumerotelephone", query = "SELECT e FROM Employe e WHERE e.numerotelephone = :numerotelephone")
    , @NamedQuery(name = "Employe.findByDatenaissance", query = "SELECT e FROM Employe e WHERE e.datenaissance = :datenaissance")})
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "MATRICULEEMPLOYE")
    private String matriculeemploye;
    @Size(max = 500)
    @Column(name = "COURRIEL")
    private String courriel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PRENOM")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MOTPASSE")
    private String motpasse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NUMEROTELEPHONE")
    private String numerotelephone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATENAISSANCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datenaissance;
    @JoinColumn(name = "IDADRESSE", referencedColumnName = "IDADRESSE")
    @ManyToOne
    private Adresse idadresse;

    public Employe() {
    }

    public Employe(String matriculeemploye) {
        this.matriculeemploye = matriculeemploye;
    }

    public Employe(String matriculeemploye, String nom, String prenom, String motpasse, String numerotelephone, Date datenaissance) {
        this.matriculeemploye = matriculeemploye;
        this.nom = nom;
        this.prenom = prenom;
        this.motpasse = motpasse;
        this.numerotelephone = numerotelephone;
        this.datenaissance = datenaissance;
    }

    public String getMatriculeemploye() {
        return matriculeemploye;
    }

    public void setMatriculeemploye(String matriculeemploye) {
        this.matriculeemploye = matriculeemploye;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getNumerotelephone() {
        return numerotelephone;
    }

    public void setNumerotelephone(String numerotelephone) {
        this.numerotelephone = numerotelephone;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public Adresse getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(Adresse idadresse) {
        this.idadresse = idadresse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculeemploye != null ? matriculeemploye.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.matriculeemploye == null && other.matriculeemploye != null) || (this.matriculeemploye != null && !this.matriculeemploye.equals(other.matriculeemploye))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Employe[ matriculeemploye=" + matriculeemploye + " ]";
    }
    
}
