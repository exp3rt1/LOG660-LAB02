package com.etsmtl.equipe9.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CARTECREDIT",schema="EQUIPE9")
public class Cartecredit  implements java.io.Serializable {

    @Id
    @Column(name="NUMERO", unique=true, nullable=false, length=20)
    private String numero;
    
    @Column(name="MOISEXPIRATION", nullable=false)
    private Integer moisexpiration;
    
    @Column(name="ANNEEEXPIRATION", nullable=false)
    private Integer anneeexpiration;
    
    @Column(name="CVV", nullable=false, length=4)
    private String cvv;
    
    @Column(name="TYPE", nullable=false, length=15)
    private String type;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="cartecredit")
    private Set<Client> clients = new HashSet<Client>(0);
    
    public Cartecredit() {}
    
    public Cartecredit(String numero, Integer moisexpiration, Integer anneeexpiration, String cvv, String type) {
        this.numero = numero;
        this.moisexpiration = moisexpiration;
        this.anneeexpiration = anneeexpiration;
        this.cvv = cvv;
        this.type = type;
    }
    public Cartecredit(String numero, Integer moisexpiration, Integer anneeexpiration, String cvv, String type, Set<Client> clients) {
       this.numero = numero;
       this.moisexpiration = moisexpiration;
       this.anneeexpiration = anneeexpiration;
       this.cvv = cvv;
       this.type = type;
       this.clients = clients;
    }
    
    public String getNumero() {
        return this.numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Integer getMoisexpiration() {
        return this.moisexpiration;
    }
    public void setMoisexpiration(Integer moisexpiration) {
        this.moisexpiration = moisexpiration;
    }
    public Integer getAnneeexpiration() {
        return this.anneeexpiration;
    } 
    public void setAnneeexpiration(Integer anneeexpiration) {
        this.anneeexpiration = anneeexpiration;
    }
    public String getCvv() {
        return this.cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public String getType() {
        return this.type;
    } 
    public void setType(String type) {
        this.type = type;
    }
    public Set<Client> getClients() {
        return this.clients;
    }
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
    
}


