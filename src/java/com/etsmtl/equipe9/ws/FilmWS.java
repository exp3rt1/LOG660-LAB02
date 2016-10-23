/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.FilmCtrl;
import com.etsmtl.equipe9.controller.GenreCtrl;
import com.etsmtl.equipe9.controller.LocationCtrl;
import com.etsmtl.equipe9.controller.PaysCtrl;
import com.etsmtl.equipe9.dto.ClientDTO;
import com.etsmtl.equipe9.controller.PersonneCtrl;
import com.etsmtl.equipe9.dto.FilmDTO;
import com.etsmtl.equipe9.dto.YearInterval;
import com.etsmtl.equipe9.model.Film;
import com.etsmtl.equipe9.model.Genre;
import com.etsmtl.equipe9.model.Pays;
import com.etsmtl.equipe9.model.Personnage;
import com.etsmtl.equipe9.model.Personne;
import com.etsmtl.equipe9.model.Scenariste;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Mario Morra
 */

@Stateless
@Path("film")
public class FilmWS {
    
    private final FilmCtrl filmCtrl = new FilmCtrl();
    private final PaysCtrl paysCtrl = new PaysCtrl();
    private final GenreCtrl genreCtrl = new GenreCtrl();
    private final LocationCtrl locationCtrl = new LocationCtrl();
    private final PersonneCtrl personneCtrl = new PersonneCtrl();
	
    @Context private UriInfo context;
    @Context private HttpServletRequest servletRequest;
    @Context private HttpServletResponse response;
        
    @POST
    @Path("recherche")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String recherche(String data, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> directors = new ArrayList<>();
        ArrayList<String> actors = new ArrayList<>();
        ArrayList<Integer> releaseDates = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> originalLanguages = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<YearInterval> dateIntervals = new ArrayList<>();
        
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        ClientDTO client = (ClientDTO)session.getAttribute("client");
        
        if(!client.getRole().equals("client")) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        try {
            
            JSONObject searchObject = (JSONObject) new JSONParser().parse(data);
            
            JSONArray titres = (JSONArray) searchObject.get("titres");
            if(titres != null){
                titres.stream().forEach((titre) -> {titles.add((String)titre);});
            }
            
            JSONArray realisateurs = (JSONArray) searchObject.get("realisateurs");
            if(realisateurs != null){
                realisateurs.stream().forEach((realisateur) -> {directors.add((String)realisateur);});
            }
            
            JSONArray acteurs = (JSONArray) searchObject.get("acteurs");
            if(acteurs != null){
                acteurs.stream().forEach((acteur) -> {actors.add((String)acteur);});
            }
            
            JSONArray pays = (JSONArray) searchObject.get("pays");
            if(pays != null){
                pays.stream().forEach((unPays) -> {countries.add((String)unPays);});
            }
            
            JSONArray languesOriginales = (JSONArray) searchObject.get("languesOriginales");
            if(languesOriginales != null){
                languesOriginales.stream().forEach((langueOriginale) -> {originalLanguages.add((String)langueOriginale);});
            }
            
            JSONArray genrerinos = (JSONArray) searchObject.get("genres");
            if(genrerinos != null){
                genrerinos.stream().forEach((genrerino) -> {genres.add((String)genrerino);});
            }
            
            JSONArray anneesSortie = (JSONArray) searchObject.get("anneesSortie");
            if(anneesSortie != null){
                for(Object anneeSortie : anneesSortie){
                    Integer releaseDate = Integer.parseInt((String) anneeSortie);
                    releaseDates.add(releaseDate);
                }
            }
            
            JSONArray intervallesAnnees = (JSONArray) searchObject.get("intervallesAnnees");
            if(intervallesAnnees != null){
                for(Object intervalleAnnees : intervallesAnnees){
                    String interval = (String) intervalleAnnees;
                    String start = interval.split(",")[0];
                    String fin = interval.split(",")[1];
                    dateIntervals.add(new YearInterval(Integer.parseInt(start),Integer.parseInt(fin)));
                }
            }
            
            FilmDTO filmDTO = new FilmDTO();
            filmDTO.setActors(actors);
            filmDTO.setCountries(countries);
            filmDTO.setDateIntervals(dateIntervals);
            filmDTO.setDirectors(directors);
            filmDTO.setGenres(genres);
            filmDTO.setOriginalLanguages(originalLanguages);
            filmDTO.setReleaseDates(releaseDates);
            filmDTO.setTitles(titles);
            
            List<Film> searchResults = filmCtrl.getFilms(filmDTO);        
            
            JSONArray json = new JSONArray();  
            searchResults.stream().forEach((film) -> {
                JSONArray jsonFilm = new JSONArray();
                jsonFilm.add(film.getIdfilm().toString());
                jsonFilm.add(film.getTitre());
                jsonFilm.add(film.getAnneesortie());
                json.add(jsonFilm);
            });
            
            return json.toJSONString();
         
        } 
        catch (ParseException ex) {
            Logger.getLogger(FilmWS.class.getName()).log(Level.SEVERE, null, ex);
            return "{\"GG\":\"ERROR\"}";
        }       
        
    }
    
    @GET
    @Path("getAllFilmGenres")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllFilmGenres(String data, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        JSONArray jsonGenres = new JSONArray();
        List<String> genres = genreCtrl.getGenres();
        
        // Securite
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        ClientDTO client = (ClientDTO)session.getAttribute("client");
        if(!client.getRole().equals("client")) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        // Securite
        
        genres.stream().filter((genre) -> (genre != null && !genre.isEmpty())).forEach((genre) -> {
            jsonGenres.add(genre);
        });
        return jsonGenres.toJSONString();
    }
    
    @GET
    @Path("getAllFilmCountries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllFilmCountries(String data, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        JSONArray jsonCountries = new JSONArray();
        List<String> countries = paysCtrl.getPays();
        
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        ClientDTO client = (ClientDTO)session.getAttribute("client");
        if(!client.getRole().equals("client")) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        countries.stream().filter((country) -> (country != null && !country.isEmpty())).forEach((country) -> {
            jsonCountries.add(country);
        });
        return jsonCountries.toJSONString();
    }
    
    @GET
    @Path("getAllFilmLanguages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllFilmLanguages(String data, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        JSONArray jsonLanguages = new JSONArray();
        List<String> langues = filmCtrl.getLangues();
        langues.stream().filter((langue) -> (langue != null && !langue.isEmpty())).forEach((langue) -> {
            jsonLanguages.add(langue);
        });
        return jsonLanguages.toJSONString();
    }
    
    @GET
    @Path("getFilmInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getFilmInfo(@PathParam("id") String filmId, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {

        
        
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        ClientDTO client = (ClientDTO)session.getAttribute("client");
        if(!client.getRole().equals("client")) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        JSONObject filmJSON = new JSONObject();
        
        Long id = Long.parseLong(filmId);
        Film film = filmCtrl.getFilm(id);
        
        // Titre
        String movieTitle = film.getTitre();
        filmJSON.put("title", movieTitle);
        
        // Durée (en minutes)
        String duration = film.getDuree().toString();
        filmJSON.put("duration", duration);
        
        // Année de sortie
        String year = film.getAnneesortie().toString();
        filmJSON.put("year", year);
        
        // Langue originale
        String language = film.getLangueoriginale();
        filmJSON.put("language", language);
        
        // Résumé du scénario
        String synopsis = film.getResumescenario();
        filmJSON.put("synopsis", synopsis);
        
        // Réalisateur
        JSONObject director = new JSONObject();
        String directorId = film.getRealisateur().getIdpersonne().toString();
        String directorName = film.getRealisateur().getNom();
        director.put("directorId", directorId);
        director.put("directorName", directorName);
        filmJSON.put("director",director);
     
        // Liste des acteurs avec leur personnage
        JSONArray actorList = new JSONArray();
        Set<Personnage> personnages = film.getPersonnages();
        for(Personnage personnage : personnages){
            String characterName = personnage.getNompersonnage();
            String actorName = personnage.getActeur().getNom();
            String actorId = personnage.getActeur().getIdpersonne().toString();
            JSONObject actor = new JSONObject();
            actor.put("actorId", actorId);
            actor.put("actorName", actorName);
            actor.put("characterName", characterName);
            actorList.add(actor);
        }
        filmJSON.put("actors", actorList);
        
        // Scénaristes
        JSONArray screenwriterList = new JSONArray();
        Set<Scenariste> scenaristes = film.getScenaristes();
        for(Scenariste scenariste : scenaristes){
            String screenwriter = scenariste.getNom();
            screenwriterList.add(screenwriter);
        }
        filmJSON.put("screenwriters", screenwriterList);
        
        // Pays
        JSONArray countryList = new JSONArray();
        Set<Pays> pays = film.getPays();
        for(Pays lePays : pays){
            String country = lePays.getNom();
            countryList.add(country);
        }
        filmJSON.put("countries", countryList);
        
        // Genres
        JSONArray genreList = new JSONArray();
        Set<Genre> genres = film.getGenres();
        for(Genre genre : genres){
            String myGenre = genre.getNom();
            genreList.add(myGenre);
        }
        filmJSON.put("genres", genreList);
        
        
        String email = client.getCourriel();
        boolean filmCopiesLeft = locationCtrl.verifierLocationExemplaire(email, film.getIdfilm()); 
        filmJSON.put("filmCopiesLeft", filmCopiesLeft);
        
        return filmJSON.toJSONString();
    }
    
    
    @GET
    @Path("afficher/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String showFilm (@PathParam("id") String filmId, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        ClientDTO client = (ClientDTO)session.getAttribute("client");
        if(!client.getRole().equals("client")) {
            response.sendRedirect("/LOG660-LAB02/");
            return "";
        }
        
        return "/LOG660-LAB02/film.html?id="+filmId;
    }
    
    
    @GET
    @Path("louerFilm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String louerFilm(@PathParam("id") String filmID, @Context HttpServletRequest request) { 
        
        try {
            
            HttpSession session = request.getSession(false);
            if(session == null) {
                response.sendRedirect("/LOG660-LAB02/");
                return "";
            }

            ClientDTO client = (ClientDTO)session.getAttribute("client");
            if(!client.getRole().equals("client")) {
                response.sendRedirect("/LOG660-LAB02/");
                return "";
            }

            Long filmId = Long.parseLong(filmID);
            String email = client.getCourriel();
            
            JSONObject success = new JSONObject();
            
            boolean filmCopiesLeft = locationCtrl.verifierLocationExemplaire(email, filmId); 
            
            if(!filmCopiesLeft){
                success.put("success", false);
                success.put("message", "Désolé. Il n'y a plus d'exemplaires dispnibles.");
                success.put("noMoreCopies", true);
                return success.toJSONString();
            }
            
            boolean userCanRent = locationCtrl.verifierLocationForfait(email);
            
            if(!userCanRent){
                success.put("success", false);
                success.put("message", "Désolé. Votre forfait ne comprend plus de locations.");
                if(filmCopiesLeft){
                    success.put("noMoreCopies", false);
                }
                else {
                    success.put("noMoreCopies", true);
                }
                return success.toJSONString();
            }
            
            boolean result = locationCtrl.louerFilm(email, filmId);
            filmCopiesLeft = locationCtrl.verifierLocationExemplaire(email, filmId);
            if(result){
                success.put("success", true);
                success.put("message", "La location s'est effectuée avec succès!");
                if(filmCopiesLeft){
                    success.put("noMoreCopies", false);
                }
                else {
                    success.put("noMoreCopies", true);
                }
                return success.toJSONString();
            }
            else{
                success.put("success", false);
                success.put("message", "Désolé. Votre reqête a échouée.");
                return success.toJSONString();
            }
        } catch (Exception ex) {
            Logger.getLogger(FilmWS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        //return null;
    }
    
    
    @GET
    @Path("getPersonInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getPersonInfo(@PathParam("id") String personID, @Context HttpServletRequest request) {
        try {
            //JSONObject location = (JSONObject) new JSONParser().parse(data);
            //String personID = (String) location.get("personID");
            
            System.out.println("personID : " + personID);
            
            if(personID.isEmpty()){             
                return null;
            }
            
            Long personId = Long.parseLong(personID);
            
            System.out.println("Parsed personId : " + personId);
            
            Personne person = personneCtrl.getPersonne(personId);
            
            System.out.println("PersonID from object : " + person.getIdpersonne());
            
            JSONObject jsonPerson = new JSONObject();
            
            jsonPerson.put("id", person.getIdpersonne());
            jsonPerson.put("name", person.getNom());
            
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfBirth = formatter.format(person.getDatenaissance());
            jsonPerson.put("dateOfBirth", dateOfBirth);
            
            jsonPerson.put("placeOfBirth", person.getLieunaissance());
            jsonPerson.put("biography", person.getBiographie());
            
            return jsonPerson.toJSONString();
            
        } catch (Exception ex) {
            Logger.getLogger(FilmWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}