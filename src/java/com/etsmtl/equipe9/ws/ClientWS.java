package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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

@Stateless
@Path("client")
public class ClientWS {
    private ClientCtrl ctrl = new ClientCtrl();
	
    @Context
    private UriInfo context;
        
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    public Response login(
            @FormParam("courriel") String courriel,
            @FormParam("motPasse") String motDePasse
    ) {  
        URI uri = null;
        try {
            //JSONObject object = (JSONObject) new JSONParser().parse(data);
            //courriel = (String) object.get("courriel");
            //motDePasse = (String) object.get("motDePasse");

            if(ctrl.getPassword(courriel, motDePasse)){
                uri = new URI("/LOG660-LAB02/rechercheFilm.html");
            }
            else {
                uri = new URI("/LOG660-LAB02/#error");
            }
        }
        catch (URISyntaxException ex) {       
            Logger.getLogger(ClientWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.temporaryRedirect(uri).build();
    }
}
