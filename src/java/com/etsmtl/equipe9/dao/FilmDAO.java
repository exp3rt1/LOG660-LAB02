package com.etsmtl.equipe9.dao;

import com.etsmtl.equipe9.dto.FilmDTO;
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

public class FilmDAO extends DAOAbstrait<Film, Long> {

    public List<Film> rechercheFilm( FilmDTO dto){

        try {

            connect();
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
            if (!dto.getTitles().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (String titre : dto.getTitles()) {

                    predi.add(cb.or(cb.like(cb.lower(film.get("titre")), "%" + titre.toLowerCase() + "%")));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere langue
            if (!dto.getOriginalLanguages().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (String langue : dto.getOriginalLanguages()) {

                    predi.add(cb.or(cb.equal(cb.lower(film.get("langueoriginale")), langue.toLowerCase())));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere genre
            if (!dto.getGenres().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (String g : dto.getGenres()) {

                    predi.add(cb.or(cb.equal(cb.lower(filmGenre.get("nom")), g.toLowerCase())));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere pays
            if (!dto.getCountries().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (String p : dto.getCountries()) {

                    predi.add(cb.or(cb.equal(cb.lower(filmPays.get("nom")), p.toLowerCase())));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere realisateur
            if (!dto.getDirectors().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (String r : dto.getDirectors()) {

                    predi.add(cb.or(cb.like(cb.lower(filmRealisateur.get("nom")), "%" + r.toLowerCase() + "%")));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere Acteur
            if (!dto.getActors().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (String a : dto.getActors()) {

                    predi.add(cb.or(cb.like(cb.lower(filmPersonnagePersonne.get("nom")), "%" + a.toLowerCase() + "%")));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere Annee Sortie
            if (!dto.getReleaseDates().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (int a : dto.getReleaseDates()) {

                    predi.add(cb.or(cb.equal(film.get("anneesortie"), a)));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere Annee Interval
            if (!dto.getDateIntervals().isEmpty()) {

                List<Predicate> predi = new ArrayList<Predicate>();
                for (YearInterval a : dto.getDateIntervals()) {

                    predi.add(cb.or(cb.between(film.get("anneesortie"), a.getStart(), a.getEnd())));
                }
                Predicate or = cb.or(predi.toArray(new Predicate[]{}));
                predicates.add(or);
            }

            // Critere Vide, on retourne tous les films
            if (dto.getReleaseDates().isEmpty() && dto.getDirectors().isEmpty() && dto.getActors().isEmpty()
                    && dto.getCountries().isEmpty() && dto.getGenres().isEmpty() && dto.getOriginalLanguages().isEmpty()
                    && dto.getTitles().isEmpty() && dto.getDateIntervals().isEmpty()) {

                CriteriaQuery<Film> query = cb.createQuery(Film.class);
                List<Film> result = em.createQuery(query).getResultList();

                return result;

            }

            //Query construction
            cq.select(film)
                    .where(predicates.toArray(new Predicate[]{})).distinct(true);
            Query query = em.createQuery(cq);

            List<Film> result = query.getResultList();

            return result;

        } catch (Exception e) {

            return null;

        } finally {

            disconnect();
        }

    }

    @Override
    public Film findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Film findById(Long id) {
        return this.emFind(Film.class, id);
    }

    @Override
    public boolean insert(Film obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Film obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Film obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Film> findById(List<Long> listeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
