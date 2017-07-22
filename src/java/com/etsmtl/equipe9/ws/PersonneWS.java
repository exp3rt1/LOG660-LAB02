package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@WebServlet("/personne/{id}")
public class PersonneWS extends HttpServlet {
    private ClientCtrl ctrl = new ClientCtrl();
	
    @Context
    private UriInfo context;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Cookie idCookie;
        HttpSession session;
        String courriel = request.getParameter("courriel");
        String motDePasse = request.getParameter("motPasse");
        
        if (!courriel.isEmpty() && !motDePasse.isEmpty() && ctrl.getPassword(courriel, motDePasse)) 
        {
            session = request.getSession(true);            
            idCookie = new Cookie("id", session.getId());
            idCookie.setMaxAge(60 * 3600);
            response.addCookie(idCookie);
            
            response.sendRedirect("./rechercheFilm.html");
        }
        else {
            response.sendRedirect("./#error");
        }
    }
}
