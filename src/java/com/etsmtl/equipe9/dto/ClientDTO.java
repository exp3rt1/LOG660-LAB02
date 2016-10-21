package com.etsmtl.equipe9.dto;

public class ClientDTO 
{        
    private String courriel;
    private String motDePasse;
    private String role;
    
    public ClientDTO(){
        role = "client";
    }

    public String getCourriel() {
        return courriel;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public String getRole() {
        return role;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
