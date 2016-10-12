package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.controller.ClientCtrl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

@Stateless
@Path("client")
public class ClientWS {
    private ClientCtrl ctrl = new ClientCtrl();
	
    @Context
    private UriInfo context;
        
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String data) {
        String courriel = null;
        String motDePasse = null;
        
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(data);
            courriel = (String) object.get("courriel");
            motDePasse = (String) object.get("motDePasse");
            
            System.out.print(courriel + ":" + motDePasse);
            
            if(ctrl.getPassword(courriel, motDePasse)){
                return Response.status(200).entity("{\"reponse\":\"success\"}").build();
            }
        } 
        catch (ParseException ex) {
            Logger.getLogger(ClientWS.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        return Response.status(401).entity("{\"reponse\":\"fail\"}").build();
    }
}
