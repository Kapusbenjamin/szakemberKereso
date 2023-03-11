/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Model.Ratings;
import szakemberkereso.Service.RatingsService;
import szakemberkereso.Service.ResponseService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Ratings")
public class RatingsController {

    @Context
    private UriInfo context;

    private RatingsService rs = new RatingsService();
    
    /**
     * Creates a new instance of RatingsController
     */
    public RatingsController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.RatingsController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RatingsController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("getRatingById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRatingById(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            Ratings result = rs.getRatingById(rating.getId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte az értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllRatings")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllRatings(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Ratings> result = rs.getAllRatings();
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte az összes értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllNotAcceptedRatings")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllNotAcceptedRatings(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Ratings> result = rs.getAllNotAcceptedRatings();
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte az összes még nem elfogadott értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllRatingsByRatinger")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllRatingsByRatinger(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            List<Ratings> result = rs.getAllRatingsByRatinger(rating.getRatingerUserId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a felhasználóhoz tartozó összes értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllRatingsByRatinged")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllRatingsByRatinged(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            List<Ratings> result = rs.getAllRatingsByRatinged(rating.getRatingedUserId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a felhasználóhoz tartozó összes értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("updateRatingById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRatingById(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = rs.updateRatingById(rating);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen módosította az értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("acceptRating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptRating(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = rs.acceptRating(rating.getId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen elfogadta az értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteRatingById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRatingById(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = rs.deleteRatingById(rating.getId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen törölte az értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("createRating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRating(Ratings rating){        
        JSONObject obj = new JSONObject();
        try{
            String result = rs.createRating(rating);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen létrehozta az értékelést!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
