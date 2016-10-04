
package com.etsmtl.equipe9.ws;

import com.etsmtl.equipe9.model.Location;
import com.etsmtl.equipe9.model.LocationId;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author NicolasSevigny
 */
@Stateless
@Path("com.etsmtl.equipe9.model.location")
public class LocationFacadeREST extends AbstractFacade<Location> {

    @PersistenceContext(unitName = "LOG660-LAB02PU")
    private EntityManager em;

    private LocationId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;courrielclient=courrielclientValue;idexemplaire=idexemplaireValue;datelocation=datelocationValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.etsmtl.equipe9.model.LocationId key = new com.etsmtl.equipe9.model.LocationId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> courrielclient = map.get("courrielclient");
        if (courrielclient != null && !courrielclient.isEmpty()) {
            key.setCourrielclient(courrielclient.get(0));
        }
        java.util.List<String> idexemplaire = map.get("idexemplaire");
        if (idexemplaire != null && !idexemplaire.isEmpty()) {
            key.setIdexemplaire(new java.math.BigDecimal(idexemplaire.get(0)));
        }
        java.util.List<String> datelocation = map.get("datelocation");
        if (datelocation != null && !datelocation.isEmpty()) {
            key.setDatelocation(new java.util.Date(datelocation.get(0)));
        }
        return key;
    }

    public LocationFacadeREST() {
        super(Location.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Location entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Location entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.etsmtl.equipe9.model.LocationId key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Location find(@PathParam("id") PathSegment id) {
        com.etsmtl.equipe9.model.LocationId key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Location> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Location> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
