package com.etsmtl.equipe9.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LIENMEDIA",schema="EQUIPE9")
public class Lienmedia  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDLIENMEDIA", unique=true, nullable=false, precision=22, scale=0)
    private Long idlienmedia;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFILM")
    private Film film;
    
    @Column(name="URL", length=2000)
    private String url;
    
    @Column(name="TYPE", nullable=false, length=15)
    private String type;

    public Lienmedia() {}
    
    public Lienmedia(Long idlienmedia, String type) {
        this.idlienmedia = idlienmedia;
        this.type = type;
    }
    public Lienmedia(Long idlienmedia, Film film, String url, String type) {
       this.idlienmedia = idlienmedia;
       this.film = film;
       this.url = url;
       this.type = type;
    }
    
    
    public Long getIdlienmedia() {
        return this.idlienmedia;
    }
    public void setIdlienmedia(Long idlienmedia) {
        this.idlienmedia = idlienmedia;
    }
    public Film getFilm() {
        return this.film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}


