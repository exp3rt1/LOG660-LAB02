package com.etsmtl.equipe9.model;
// Generated 2016-10-03 15:53:41 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LocationId generated by hbm2java
 */
@Embeddable
public class LocationId  implements java.io.Serializable {


     private String courrielclient;
     private BigDecimal idexemplaire;
     private Date datelocation;

    public LocationId() {
    }

    public LocationId(String courrielclient, BigDecimal idexemplaire, Date datelocation) {
       this.courrielclient = courrielclient;
       this.idexemplaire = idexemplaire;
       this.datelocation = datelocation;
    }
   


    @Column(name="COURRIELCLIENT", nullable=false, length=500)
    public String getCourrielclient() {
        return this.courrielclient;
    }
    
    public void setCourrielclient(String courrielclient) {
        this.courrielclient = courrielclient;
    }


    @Column(name="IDEXEMPLAIRE", nullable=false, precision=22, scale=0)
    public BigDecimal getIdexemplaire() {
        return this.idexemplaire;
    }
    
    public void setIdexemplaire(BigDecimal idexemplaire) {
        this.idexemplaire = idexemplaire;
    }


    @Column(name="DATELOCATION", nullable=false, length=7)
    public Date getDatelocation() {
        return this.datelocation;
    }
    
    public void setDatelocation(Date datelocation) {
        this.datelocation = datelocation;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LocationId) ) return false;
		 LocationId castOther = ( LocationId ) other; 
         
		 return ( (this.getCourrielclient()==castOther.getCourrielclient()) || ( this.getCourrielclient()!=null && castOther.getCourrielclient()!=null && this.getCourrielclient().equals(castOther.getCourrielclient()) ) )
 && ( (this.getIdexemplaire()==castOther.getIdexemplaire()) || ( this.getIdexemplaire()!=null && castOther.getIdexemplaire()!=null && this.getIdexemplaire().equals(castOther.getIdexemplaire()) ) )
 && ( (this.getDatelocation()==castOther.getDatelocation()) || ( this.getDatelocation()!=null && castOther.getDatelocation()!=null && this.getDatelocation().equals(castOther.getDatelocation()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCourrielclient() == null ? 0 : this.getCourrielclient().hashCode() );
         result = 37 * result + ( getIdexemplaire() == null ? 0 : this.getIdexemplaire().hashCode() );
         result = 37 * result + ( getDatelocation() == null ? 0 : this.getDatelocation().hashCode() );
         return result;
   }   


}


