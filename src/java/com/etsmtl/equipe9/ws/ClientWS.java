package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import com.etsmtl.equipe9.dto.ClientDTO;
import java.io.IOException;
import java.net.URI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


@WebServlet("/login")
public class ClientWS extends HttpServlet {
    private ClientCtrl ctrl = new ClientCtrl();
	
    @Context private UriInfo context;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session;
        String courriel = request.getParameter("courriel");
        String motDePasse = request.getParameter("motPasse");
        
        try {
            if (!courriel.isEmpty() && !motDePasse.isEmpty() && ctrl.getPassword(courriel, motDePasse)) 
            {
                // Crée une session
                session = request.getSession(true);
                session.setMaxInactiveInterval(1000); 
                
                // Crée un client
                ClientDTO client = new ClientDTO();
                client.setCourriel(courriel);
                client.setMotDePasse(motDePasse); 
                
                // Ajoute l'objet client à la page
                session.setAttribute("client", client);
                session.setAttribute("recherche", "");
                
                response.sendRedirect("./recherche");
            }
            else {
                response.sendRedirect("./#error");
            }
        }
        catch(Exception e) {
            response.sendRedirect("./#error");
        }
    }
}
