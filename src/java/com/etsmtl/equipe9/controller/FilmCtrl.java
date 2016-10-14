package com.etsmtl.equipe9.controller;

import com.etsmtl.equipe9.dao.FilmDAO;
import com.etsmtl.equipe9.dto.YearInterval;
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
            listeRealisateur, List<String> listeActeur, List<Integer>
            listeAnneeSortie, List<YearInterval> listeAnneeInterval ) {

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
                
                predi.add( cb.or(cb.like(cb.lower(film.get("titre")), "%"+titre.toLowerCase()+"%")));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere langue
        if (!listeLangue.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String langue : listeLangue) {
                
                predi.add( cb.or(cb.equal(cb.lower(film.get("langueoriginale")), langue.toLowerCase())));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere genre
        if (!listeGenre.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String g : listeGenre) {
                
                predi.add( cb.or(cb.equal(cb.lower(filmGenre.get("nom")), g.toLowerCase())));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere pays
        if (!listepays.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String p : listepays) {
                
                predi.add( cb.or(cb.equal(cb.lower(filmPays.get("nom")), p.toLowerCase())));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere realisateur
        if (!listeRealisateur.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String r : listeRealisateur) {
                
                predi.add( cb.or(cb.like(cb.lower(filmRealisateur.get("nom")), "%"+r.toLowerCase()+"%")));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere Acteur
        if (!listeActeur.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (String a : listeActeur) {
                
                predi.add( cb.or(cb.like(cb.lower(filmPersonnagePersonne.get("nom")), "%"+a.toLowerCase()+"%")));
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
        
<<<<<<< HEAD
        // Critere Annee Interval
        if (!listeAnneeInterval.isEmpty()){
            
            List<Predicate> predi = new ArrayList<Predicate>();
            for (YearInterval a : listeAnneeInterval) {
                
                predi.add( cb.or(cb.between(film.get("anneesortie"), a.getStart(),a.getEnd())));
            }
            Predicate or = cb.or(predi.toArray(new Predicate[]{}));
            predicates.add(or); 
        }
        
        // Critere Vide
=======
        // Critere Vide, on retourne tous les films
>>>>>>> f78dbcc07a9286c6790816070f81e1bc97c91e81
        if (listeAnneeSortie.isEmpty() && listeActeur.isEmpty() && listeRealisateur.isEmpty()
                && listepays.isEmpty() && listeGenre.isEmpty() && listeLangue.isEmpty() 
                && listeTitre.isEmpty() && listeAnneeInterval.isEmpty())  {

            CriteriaQuery<Film> query = cb.createQuery(Film.class);
            List<Film> result = em.createQuery(query).getResultList();

            for (Film film1 : result) {
                System.out.println(film1.getTitre()+" "+film1.getAnneesortie());
            }
            return;

        }
        
        //Query construction
        cq.select(film)
            .where(predicates.toArray(new Predicate[]{})).distinct(true);
        Query query = em.createQuery(cq);
        
        List<Film> result = query.getResultList();
        
        for (Film film1 : result) {
            System.out.println(film1.getTitre()+" "+film1.getAnneesortie());
        }
    }
        
    
    public static void main(String[] args) {
        
        
       
        List<String> listetitre = new ArrayList<>();
<<<<<<< HEAD
        //listetitre.add("the");
        //listetitre.add("at");
=======
        //listetitre.add("ou");
        //listetitre.add("bat");
>>>>>>> f78dbcc07a9286c6790816070f81e1bc97c91e81
        
        List<String> listelangue = new ArrayList<>();
        //listelangue.add("Portuguese");
        //listelangue.add("English");
        
        List<String> listepays = new ArrayList<>();
        //listepays.add("uk");
        //listepays.add("USA");
        
        List<String> listegenre = new ArrayList<>();
        //listegenre.add("Comedy");
        //listegenre.add("action");
        
        List<String> listerealisateur = new ArrayList<>();
        //listerealisateur.add("tim");
        //listerealisateur.add("Tim B");
        
        List<String> listeacteur = new ArrayList<>();
        //listeacteur.add("jack");
        //listeacteur.add("Anna");
        
        List<Integer> listeanneesortie = new ArrayList<>();
        //listeanneesortie.add(1992);
        //listeanneesortie.add(1989);
        
        List<YearInterval> listeanneeinterval = new ArrayList<>();
        listeanneeinterval.add(new YearInterval(2000, 2005));
        //listeanneeinterval.add(1989);
        
        FilmCtrl control = new FilmCtrl();
        control.rechercheFilm(listetitre, listelangue, listegenre, listepays, listerealisateur, listeacteur, listeanneesortie,listeanneeinterval);
        
    }
}
