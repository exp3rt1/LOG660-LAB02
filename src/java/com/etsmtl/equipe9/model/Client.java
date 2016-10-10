/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nicolas Desktop
 */
@Entity
@Table(name = "CLIENT", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findByCourriel", query = "SELECT c FROM Client c WHERE c.courriel = :courriel")
    , @NamedQuery(name = "Client.findByNom", query = "SELECT c FROM Client c WHERE c.nom = :nom")
    , @NamedQuery(name = "Client.findByPrenom", query = "SELECT c FROM Client c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Client.findByMotpasse", query = "SELECT c FROM Client c WHERE c.motpasse = :motpasse")
    , @NamedQuery(name = "Client.findByNumerotelephone", query = "SELECT c FROM Client c WHERE c.numerotelephone = :numerotelephone")
    , @NamedQuery(name = "Client.findByDatenaissance", query = "SELECT c FROM Client c WHERE c.datenaissance = :datenaissance")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Collection<Location> locationCollection;
    @JoinColumn(name = "IDADRESSE", referencedColumnName = "IDADRESSE")
    @ManyToOne
    private Adresse idadresse;
    @JoinColumn(name = "NUMEROCARTECREDIT", referencedColumnName = "NUMERO")
    @ManyToOne
    private Cartecredit numerocartecredit;
    @JoinColumn(name = "TYPEFORFAIT", referencedColumnName = "TYPE")
    @ManyToOne
    private Forfait typeforfait;

    public Client() {
    }

    public Client(String courriel) {
        this.courriel = courriel;
    }

    public Client(String courriel, String nom, String prenom, String motpasse, String numerotelephone, Date datenaissance) {
        this.courriel = courriel;
        this.nom = nom;
        this.prenom = prenom;
        this.motpasse = motpasse;
        this.numerotelephone = numerotelephone;
        this.datenaissance = datenaissance;
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

    @XmlTransient
    public Collection<Location> getLocationCollection() {
        return locationCollection;
    }

    public void setLocationCollection(Collection<Location> locationCollection) {
        this.locationCollection = locationCollection;
    }

    public Adresse getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(Adresse idadresse) {
        this.idadresse = idadresse;
    }

    public Cartecredit getNumerocartecredit() {
        return numerocartecredit;
    }

    public void setNumerocartecredit(Cartecredit numerocartecredit) {
        this.numerocartecredit = numerocartecredit;
    }

    public Forfait getTypeforfait() {
        return typeforfait;
    }

    public void setTypeforfait(Forfait typeforfait) {
        this.typeforfait = typeforfait;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courriel != null ? courriel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.courriel == null && other.courriel != null) || (this.courriel != null && !this.courriel.equals(other.courriel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Client[ courriel=" + courriel + " ]";
    }
    
}
