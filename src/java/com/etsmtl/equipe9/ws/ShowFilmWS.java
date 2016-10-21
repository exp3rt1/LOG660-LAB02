/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Oli
 */
@WebServlet("/film")
public class ShowFilmWS extends HttpServlet {
    private ClientCtrl ctrl = new ClientCtrl();
	
    @Context private UriInfo context;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Mets le id en parametre et en session
        // Va chercher les info du film directement
        // Mets les infos en session
        response.sendRedirect("/LOG660-LAB02/film/show");
    }
}
