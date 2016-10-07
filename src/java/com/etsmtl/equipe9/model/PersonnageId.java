package com.etsmtl.equipe9.model;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonnageId implements java.io.Serializable {

    @Column(name = "IDFILM", nullable = false)
    private Long idfilm;
    
    @Column(name = "IDACTEUR", nullable = false)
    private Long idacteur;

    public PersonnageId() {}

    public PersonnageId(Long idfilm, Long idacteur) {
        this.idfilm = idfilm;
        this.idacteur = idacteur;
    }

    
    public Long getIdfilm() {
        return this.idfilm;
    }
    public void setIdfilm(Long idfilm) {
        this.idfilm = idfilm;
    }
    public Long getIdacteur() {
        return this.idacteur;
    }
    public void setIdacteur(Long idacteur) {
        this.idacteur = idacteur;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other)) {return true;}
        if ((other == null)) {return false;}
        if (!(other instanceof PersonnageId)) {return false;}
        PersonnageId castOther = (PersonnageId) other;

        return ((Objects.equals(this.getIdfilm(), castOther.getIdfilm())) || (this.getIdfilm() != null && castOther.getIdfilm() != null && this.getIdfilm().equals(castOther.getIdfilm())))
                && ((Objects.equals(this.getIdacteur(), castOther.getIdacteur())) || (this.getIdacteur() != null && castOther.getIdacteur() != null && this.getIdacteur().equals(castOther.getIdacteur())));
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (getIdfilm() == null ? 0 : this.getIdfilm().hashCode());
        result = 37 * result + (getIdacteur() == null ? 0 : this.getIdacteur().hashCode());
        return result;
    }
    
}
