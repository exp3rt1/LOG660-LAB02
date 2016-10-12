package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Genre;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.model.Personnage;
import com.etsmtl.equipe9.model.Personne;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



public class FilmCtrl {
    
   public Film getFilm (Long idFilm){
        
        FilmDAO dao = new FilmDAO();
        return dao.findById(idFilm);
   }
   
   public List<Film> getFilms (List<Long> listIdFilm){
        
        FilmDAO dao = new FilmDAO();
        return dao.findById(listIdFilm);
   }
   
    public void rechercheFilm(List<String> listeTitre, List<String> listeLangue, 
            List<String> listeGenre, List<String> listepays, List<String> 
            listeRealisateur, List<String> listeActeur, List<Integer> listeAnneeSortie ) {

        // Entity Manager
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("WebApplication1PU");
        EntityManager em = emf.createEntityManager();
        
        // JPA CRITERIA API SETUP
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root film = cq.from(Film.class);
        Root pays = cq.from(Pays.class);
        Root genre = cq.from(Genre.class);
        Root personne = cq.from(Personne.class);
        Root personnage = cq.from(Personnage.class);
        Join<Film, Genre> filmGenre = film.join("genres");
        Join<Film, Pays> filmPays = film.join("pays");
        Join<Film, Personne> filmRealisateur = film.join("realisateur");
        Join<Film, Personnage> filmPersonnage = film.join("personnages");
        Join<Personnage, Personne> filmPersonnagePersonne = filmPersonnage.join("acteur");
        
        // Liste finale des criteres
        List<Predicate> predicates = new ArrayList<Predicate>();
        
        // Critere titre
        if (!listeTitre.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String titre : listeTitre) {
                
                predi.add( cb.or(cb.like(film.get("titre"), "%"+titre+"%")));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere langue
        if (!listeLangue.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String langue : listeLangue) {
                
                predi.add( cb.or(cb.equal(film.get("langueoriginale"), langue)));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere genre
        if (!listeGenre.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String g : listeGenre) {
                
                predi.add( cb.or(cb.equal(filmGenre.get("nom"), g)));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere pays
        if (!listepays.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String p : listepays) {
                
                predi.add( cb.or(cb.equal(filmPays.get("nom"), p)));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere realisateur
        if (!listeRealisateur.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String r : listeRealisateur) {
                
                predi.add( cb.or(cb.like(filmRealisateur.get("nom"), "%"+r+"%")));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere Acteur
        if (!listeActeur.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String a : listeActeur) {
                
                predi.add( cb.or(cb.like(filmPersonnagePersonne.get("nom"), "%"+a+"%")));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere Annee Sortie
        if (!listeAnneeSortie.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (int a : listeAnneeSortie) {
                
                predi.add( cb.or(cb.equal(film.get("anneesortie"), a)));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere Vide
        if (listeAnneeSortie.isEmpty() && listeActeur.isEmpty() && listeRealisateur.isEmpty()
                && listepays.isEmpty() && listeGenre.isEmpty() && listeLangue.isEmpty() && listeTitre.isEmpty()) {

            CriteriaQuery<Film> query = cb.createQuery(Film.class);
            List<Film> result = em.createQuery(query).getResultList();

            for (Film film1 : result) {
                System.out.println(film1.getTitre());
            }
            return;

        }
        
        //Query construction
        cq.select(film)
            .where(predicates.toArray(new Predicate[]{})).distinct(true);
        Query query = em.createQuery(cq);
        
        List<Film> result = query.getResultList();
        
        for (Film film1 : result) {
            System.out.println(film1.getTitre());
        }
    }
        
    
    public static void main(String[] args) {
        
        
       
        List<String> listetitre = new ArrayList<>();
        //listetitre.add("ou");
        //listetitre.add("an");
        
        List<String> listelangue = new ArrayList<>();
        //listelangue.add("Portuguese");
        //listelangue.add("English");
        
        List<String> listepays = new ArrayList<>();
        //listepays.add("UK");
        //listepays.add("USA");
        
        List<String> listegenre = new ArrayList<>();
        //listegenre.add("Comedy");
        //listegenre.add("Action");
        
        List<String> listerealisateur = new ArrayList<>();
        //listerealisateur.add("Tim");
        //listerealisateur.add("Tim B");
        
        List<String> listeacteur = new ArrayList<>();
        //listeacteur.add("Jack");
        //listeacteur.add("Anna");
        
        List<Integer> listeanneesortie = new ArrayList<>();
        //listeanneesortie.add(1992);
        //listeanneesortie.add(1989);
        
        FilmCtrl control = new FilmCtrl();
        control.rechercheFilm(listetitre, listelangue, listegenre, listepays, listerealisateur, listeacteur, listeanneesortie);
        
    }
}
