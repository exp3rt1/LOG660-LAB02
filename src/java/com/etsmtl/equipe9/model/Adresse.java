/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ADRESSE", catalog = "", schema = "EQUIPE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a")
    , @NamedQuery(name = "Adresse.findByIdadresse", query = "SELECT a FROM Adresse a WHERE a.idadresse = :idadresse")
    , @NamedQuery(name = "Adresse.findByNumerocivique", query = "SELECT a FROM Adresse a WHERE a.numerocivique = :numerocivique")
    , @NamedQuery(name = "Adresse.findByRue", query = "SELECT a FROM Adresse a WHERE a.rue = :rue")
    , @NamedQuery(name = "Adresse.findByVille", query = "SELECT a FROM Adresse a WHERE a.ville = :ville")
    , @NamedQuery(name = "Adresse.findByProvince", query = "SELECT a FROM Adresse a WHERE a.province = :province")
    , @NamedQuery(name = "Adresse.findByCodepostal", query = "SELECT a FROM Adresse a WHERE a.codepostal = :codepostal")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDADRESSE")
    private BigDecimal idadresse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMEROCIVIQUE")
    private BigInteger numerocivique;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "RUE")
    private String rue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "VILLE")
    private String ville;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PROVINCE")
    private String province;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "CODEPOSTAL")
    private String codepostal;
    @OneToMany(mappedBy = "idadresse")
    private Collection<Employe> employeCollection;
    @OneToMany(mappedBy = "idadresse")
    private Collection<Client> clientCollection;

    public Adresse() {
    }

    public Adresse(BigDecimal idadresse) {
        this.idadresse = idadresse;
    }

    public Adresse(BigDecimal idadresse, BigInteger numerocivique, String rue, String ville, String province, String codepostal) {
        this.idadresse = idadresse;
        this.numerocivique = numerocivique;
        this.rue = rue;
        this.ville = ville;
        this.province = province;
        this.codepostal = codepostal;
    }

    public BigDecimal getIdadresse() {
        return idadresse;
    }

    public void setIdadresse(BigDecimal idadresse) {
        this.idadresse = idadresse;
    }

    public BigInteger getNumerocivique() {
        return numerocivique;
    }

    public void setNumerocivique(BigInteger numerocivique) {
        this.numerocivique = numerocivique;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    @XmlTransient
    public Collection<Employe> getEmployeCollection() {
        return employeCollection;
    }

    public void setEmployeCollection(Collection<Employe> employeCollection) {
        this.employeCollection = employeCollection;
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
        hash += (idadresse != null ? idadresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idadresse == null && other.idadresse != null) || (this.idadresse != null && !this.idadresse.equals(other.idadresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Adresse[ idadresse=" + idadresse + " ]";
    }
    
}
