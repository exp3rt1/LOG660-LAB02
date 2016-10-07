package com.etsmtl.equipe9.model;


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="LOCATION",schema="EQUIPE9")
public class Location  implements java.io.Serializable {
    
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="courrielclient", column=@Column(name="COURRIELCLIENT", nullable=false, length=500) ), 
        @AttributeOverride(name="idexemplaire", column=@Column(name="IDEXEMPLAIRE", nullable=false) ), 
        @AttributeOverride(name="datelocation", column=@Column(name="DATELOCATION", nullable=false, length=7) ) } )
    private LocationId id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COURRIELCLIENT", nullable=false, insertable=false, updatable=false)
    private Client client;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDEXEMPLAIRE", nullable=false, insertable=false, updatable=false)
    private Exemplaire exemplaire;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATERETOUR", length=7)
    private Date dateretour;

    public Location() {}
	
    public Location(LocationId id, Client client, Exemplaire exemplaire) {
        this.id = id;
        this.client = client;
        this.exemplaire = exemplaire;
    }
    public Location(LocationId id, Client client, Exemplaire exemplaire, Date dateretour) {
       this.id = id;
       this.client = client;
       this.exemplaire = exemplaire;
       this.dateretour = dateretour;
    }
    
    
    public LocationId getId() {
        return this.id;
    }
    public void setId(LocationId id) {
        this.id = id;
    }
    public Client getClient() {
        return this.client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Exemplaire getExemplaire() {
        return this.exemplaire;
    }
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
    public Date getDateretour() {
        return this.dateretour;
    }
    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
    }
    
}


