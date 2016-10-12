/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.FilmCtrl;
import com.etsmtl.equipe9.dto.YearInterval;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
	
    @Context
    private UriInfo context;
        
    @POST
    @Path("recherche")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recherche(String data) {
        
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> directors = new ArrayList<>();
        ArrayList<String> actors = new ArrayList<>();
        ArrayList<String> releaseDates = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<String> originalLanguages = new ArrayList<>();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<YearInterval> dateIntervals = new ArrayList<>();
          
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(data);
            
            System.out.println(object.toString());
            
            // TODO: Parse data
            // TODO: Call controller
            // TODO: Return data
            
            return Response.status(200).entity("{\"reponse\":\"success\"}").build();   
        } 
        catch (ParseException ex) {
            Logger.getLogger(FilmWS.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        return Response.status(401).entity("{\"reponse\":\"fail\"}").build();
    }
}
