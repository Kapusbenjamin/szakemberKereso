/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.function.Supplier;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Model.Favorites;
import szakemberkereso.Service.FavoritesService;
import szakemberkereso.Service.ResponseService;

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
        JSONObject obj = new JSONObject();
        try{
            List<Favorites> result = fs.getAllfavoritesByUserId(favorite.getUserId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a felhasználóhoz tartozó összes kedvencet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getFavoriteById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFavoriteById(Favorites favorite){        
        JSONObject obj = new JSONObject();
        try{
            Favorites result = fs.getFavoriteById(favorite.getId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a kedvencet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteFavorite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFavorite(Favorites favorite){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = fs.deleteFavorite(favorite.getId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen törölte a kedvencet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("addFavorite")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFavorite(Favorites favorite){        
        JSONObject obj = new JSONObject();
        try{
            String result = fs.addFavorite(favorite);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen hozzáadta a kedvencet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
