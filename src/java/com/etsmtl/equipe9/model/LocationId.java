package com.etsmtl.equipe9.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocationId  implements java.io.Serializable {
    
    @Column(name="COURRIELCLIENT", nullable=false, length=500)
    private String courrielclient;
    
    @Column(name="IDEXEMPLAIRE", nullable=false)
    private Long idexemplaire;
    
    @Column(name="DATELOCATION", nullable=false, length=7)
    private Date datelocation;

    public LocationId() {}

    public LocationId(String courrielclient, Long idexemplaire, Date datelocation) {
       this.courrielclient = courrielclient;
       this.idexemplaire = idexemplaire;
       this.datelocation = datelocation;
    }
    
    
    public String getCourrielclient() {
        return this.courrielclient;
    }
    public void setCourrielclient(String courrielclient) {
        this.courrielclient = courrielclient;
    }
    public Long getIdexemplaire() {
        return this.idexemplaire;
    }
    public void setIdexemplaire(Long idexemplaire) {
        this.idexemplaire = idexemplaire;
    }
    public Date getDatelocation() {
        return this.datelocation;
    }
    public void setDatelocation(Date datelocation) {
        this.datelocation = datelocation;
    }
    
    
    @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LocationId) ) return false;
		 LocationId castOther = ( LocationId ) other; 
         
		 return ( (this.getCourrielclient()==castOther.getCourrielclient()) || ( this.getCourrielclient()!=null && castOther.getCourrielclient()!=null && this.getCourrielclient().equals(castOther.getCourrielclient()) ) )
 && ( (this.getIdexemplaire()==castOther.getIdexemplaire()) || ( this.getIdexemplaire()!=null && castOther.getIdexemplaire()!=null && this.getIdexemplaire().equals(castOther.getIdexemplaire()) ) )
 && ( (this.getDatelocation()==castOther.getDatelocation()) || ( this.getDatelocation()!=null && castOther.getDatelocation()!=null && this.getDatelocation().equals(castOther.getDatelocation()) ) );
   }
   
    @Override
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCourrielclient() == null ? 0 : this.getCourrielclient().hashCode() );
         result = 37 * result + ( getIdexemplaire() == null ? 0 : this.getIdexemplaire().hashCode() );
         result = 37 * result + ( getDatelocation() == null ? 0 : this.getDatelocation().hashCode() );
         return result;
   }   


}


