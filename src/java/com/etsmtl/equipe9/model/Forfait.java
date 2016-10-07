package com.etsmtl.equipe9.model;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FORFAIT",schema="EQUIPE9")
public class Forfait  implements java.io.Serializable {

    @Id   
    @Column(name="TYPE", unique=true, nullable=false, length=1)
    private char type;
    
    @Column(name="COUTPARMOIS", nullable=false, precision=10, scale=2)
    private BigDecimal coutparmois;
    
    @Column(name="LOCATIONSMAX", nullable=false)
    private Integer locationsmax;
    
    @Column(name="DUREEMAX", nullable=false)
    private Integer dureemax;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="forfait")
    private Set<Client> clients = new HashSet<>(0);

    public Forfait() {}
	
    public Forfait(char type, BigDecimal coutparmois, Integer locationsmax, Integer dureemax) {
        this.type = type;
        this.coutparmois = coutparmois;
        this.locationsmax = locationsmax;
        this.dureemax = dureemax;
    }
    public Forfait(char type, BigDecimal coutparmois, Integer locationsmax, Integer dureemax, Set<Client> clients) {
       this.type = type;
       this.coutparmois = coutparmois;
       this.locationsmax = locationsmax;
       this.dureemax = dureemax;
       this.clients = clients;
    }
    
    
    public char getType() {
        return this.type;
    }
    public void setType(char type) {
        this.type = type;
    }
    public BigDecimal getCoutparmois() {
        return this.coutparmois;
    }
    public void setCoutparmois(BigDecimal coutparmois) {
        this.coutparmois = coutparmois;
    }
    public Integer getLocationsmax() {
        return this.locationsmax;
    }
    public void setLocationsmax(Integer locationsmax) {
        this.locationsmax = locationsmax;
    }
    public Integer getDureemax() {
        return this.dureemax;
    }
    public void setDureemax(Integer dureemax) {
        this.dureemax = dureemax;
    }
    public Set<Client> getClients() {
        return this.clients;
    }
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
    
}


