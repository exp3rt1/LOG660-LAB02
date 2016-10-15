/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.FilmCtrl;
import com.etsmtl.equipe9.controller.GenreCtrl;
import com.etsmtl.equipe9.controller.PaysCtrl;
import com.etsmtl.equipe9.dto.FilmDTO;
import com.etsmtl.equipe9.dto.YearInterval;
import com.etsmtl.equipe9.model.Film;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
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
    
    private FilmCtrl filmCtrl = new FilmCtrl();
    private PaysCtrl paysCtrl = new PaysCtrl();
    private GenreCtrl genreCtrl = new GenreCtrl();
	
    @Context private UriInfo context;
    @Context private HttpServletRequest servletRequest;
        
    @POST
    @Path("recherche")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String recherche(String data, @Context HttpServletRequest request) {
        
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> directors = new ArrayList<>();
        ArrayList<String> actors = new ArrayList<>();
        ArrayList<Integer> releaseDates = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> originalLanguages = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<YearInterval> dateIntervals = new ArrayList<>();
        
        HttpSession session = request.getSession(false);
        System.out.print(session.getId());
        
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
    
    @POST
    @Path("getAllFilmGenres")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllFilmGenres(String data, @Context HttpServletRequest request) {
        JSONArray jsonGenres = new JSONArray();
        List<String> genres = genreCtrl.getGenres();
        genres.stream().filter((genre) -> (genre != null && !genre.isEmpty())).forEach((genre) -> {
            jsonGenres.add(genre);
        });
        return jsonGenres.toJSONString();
    }
    
    @POST
    @Path("getAllFilmCountries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllFilmCountries(String data, @Context HttpServletRequest request) {
        JSONArray jsonCountries = new JSONArray();
        List<String> countries = paysCtrl.getPays();
        countries.stream().filter((country) -> (country != null && !country.isEmpty())).forEach((country) -> {
            jsonCountries.add(country);
        });
        return jsonCountries.toJSONString();
    }
    
    @POST
    @Path("getAllFilmLanguages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAllFilmLanguages(String data, @Context HttpServletRequest request) {
        JSONArray jsonLanguages = new JSONArray();
        List<String> langues = filmCtrl.getLangues();
        langues.stream().filter((langue) -> (langue != null && !langue.isEmpty())).forEach((langue) -> {
            jsonLanguages.add(langue);
        });
        return jsonLanguages.toJSONString();
    }
    
    @POST
    @Path("info/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getFilmInfo(@PathParam("id") String filmId, @Context HttpServletRequest request) {
        Long id = Long.parseLong(filmId);
        Film film = filmCtrl.getFilm(id);
        String testStr = "NOT FOUND! GG.";
        if(film != null){
            testStr = "Nom : " + film.getTitre() + " - Durée : " + film.getDuree().toString() + " minutes";
        }
        JSONArray json = new JSONArray();
        json.add(testStr);
        return json.toJSONString();
    }
    

}