/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dto;

import java.util.ArrayList;

/**
 *
 * @author Mario Morra
 */
public class FilmDTO {
    
    private ArrayList<String> titles;
    private ArrayList<String> directors;
    private ArrayList<String> actors;
    private ArrayList<Integer> releaseDates;
    private ArrayList<String> countries;
    private ArrayList<String> originalLanguages;
    private ArrayList<String> genres;
    private ArrayList<YearInterval> dateIntervals;
    
    public FilmDTO(){}

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<Integer> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(ArrayList<Integer> releaseDates) {
        this.releaseDates = releaseDates;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<String> getOriginalLanguages() {
        return originalLanguages;
    }

    public void setOriginalLanguages(ArrayList<String> originalLanguages) {
        this.originalLanguages = originalLanguages;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<YearInterval> getDateIntervals() {
        return dateIntervals;
    }

    public void setDateIntervals(ArrayList<YearInterval> dateIntervals) {
        this.dateIntervals = dateIntervals;
    }
    
    
    
}
