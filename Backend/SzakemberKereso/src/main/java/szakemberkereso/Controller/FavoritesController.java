/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Favorites;
import szakemberkereso.Service.FavoritesService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Favorites")
public class FavoritesController {

    @Context
    private UriInfo context;

    private FavoritesService fs = new FavoritesService();
    
    /**
     * Creates a new instance of FavoritesController
     */
    public FavoritesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.FavoritesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FavoritesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("getAllfavoritesByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllfavoritesByUserId(Favorites favorite){
        List<Favorites> result = fs.getAllfavoritesByUserId(favorite.getUserId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getFavoriteById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFavoriteById(Favorites favorite){
        Favorites result = fs.getFavoriteById(favorite.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteFavorite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFavorite(Favorites favorite){
        Boolean result = fs.deleteFavorite(favorite.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("addFavorite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFavorite(Favorites favorite){
        String result = fs.addFavorite(favorite);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
