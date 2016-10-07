package com.etsmtl.equipe9.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADRESSE" ,schema="EQUIPE9")
public class Adresse  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDADRESSE", unique=true, nullable=false)
    private Long idadresse;
    
    @Column(name="NUMEROCIVIQUE", nullable=false)
    private Integer numerocivique;
    
    @Column(name="RUE", nullable=false, length=500)
    private String rue;
    
    @Column(name="VILLE", nullable=false, length=500)
    private String ville;
    
    @Column(name="PROVINCE", nullable=false, length=500)
    private String province;
    
    @Column(name="CODEPOSTAL", nullable=false, length=7)
    private String codepostal;

    public Adresse() {}
	
    public Adresse(Long idadresse, Integer numerocivique, String rue, String ville, String province, String codepostal) {
        this.idadresse = idadresse;
        this.numerocivique = numerocivique;
        this.rue = rue;
        this.ville = ville;
        this.province = province;
        this.codepostal = codepostal;
    }
    
    public Long getIdadresse() {
        return this.idadresse;
    }
    public void setIdadresse(Long idadresse) {
        this.idadresse = idadresse;
    }   
    public Integer getNumerocivique() {
        return this.numerocivique;
    } 
    public void setNumerocivique(Integer numerocivique) {
        this.numerocivique = numerocivique;
    }
    public String getRue() {
        return this.rue;
    }    
    public void setRue(String rue) {
        this.rue = rue;
    }  
    public String getVille() {
        return this.ville;
    }   
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getProvince() {
        return this.province;
    }   
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCodepostal() {
        return this.codepostal;
    }  
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }
    
}


