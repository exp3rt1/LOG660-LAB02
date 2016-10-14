package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//@Path("client")
@WebServlet("/login")
public class ClientWS extends HttpServlet {
    private ClientCtrl ctrl = new ClientCtrl();
	
    @Context
    private UriInfo context;
        
    //@POST
    //@WebServlet("login")
    //@Produces(MediaType.TEXT_HTML)
    /*public Response login(
            @FormParam("courriel") String courriel,
            @FormParam("motPasse") String motDePasse
    ) {  
        URI uri = null;
        String courriel = request.getParameter("courriel");
        String motDePasse = request.getParameter("motPasse");
        try {            
            if (!courriel.isEmpty() && !motDePasse.isEmpty()) {
                if(ctrl.getPassword(courriel, motDePasse)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("courriel", courriel);
                    uri = new URI("/LOG660-LAB02/rechercheFilm.html");
                }
                else {
                    uri = new URI("/LOG660-LAB02/#error");
                }
            }
            else {
                uri = new URI("/LOG660-LAB02/#error");
            }
        }
        catch (URISyntaxException ex) {       
            Logger.getLogger(ClientWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.temporaryRedirect(uri).build();
    }*/
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String courriel = request.getParameter("courriel");
        String motDePasse = request.getParameter("motPasse");
        
        if (!courriel.isEmpty() && !motDePasse.isEmpty() && ctrl.getPassword(courriel, motDePasse)) {
                Cookie courrielCookie = new Cookie("courriel", courriel);
                courrielCookie.setMaxAge(60 * 60);
                response.addCookie(courrielCookie);
                response.sendRedirect("/LOG660-LAB02/rechercheFilm.html");
        }
        else {
            response.sendRedirect("/LOG660-LAB02/#error");
        }
    }
}
