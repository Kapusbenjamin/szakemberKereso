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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Ratings;
import szakemberkereso.Service.RatingsService;

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
    
    @GET
    @Path("getRatingById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRatingById(@PathParam("id") Integer id){
        Ratings result = rs.getRatingById(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllRatings")
    public Response getAllRatings(){
        List<Ratings> result = rs.getAllRatings();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllNotAcceptedRatings")
    public Response getAllNotAcceptedRatings(){
        List<Ratings> result = rs.getAllNotAcceptedRatings();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllRatingsByRatinger/{user_id}")
    public Response getAllRatingsByRatinger(@PathParam("user_id") Integer user_id){
        List<Ratings> result = rs.getAllRatingsByRatinger(user_id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllRatingsByRatinged/{user_id}")
    public Response getAllRatingsByRatinged(@PathParam("user_id") Integer user_id){
        List<Ratings> result = rs.getAllRatingsByRatinged(user_id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateRatingById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRatingById(Ratings rating){
        Boolean result = rs.updateRatingById(rating);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("acceptRating/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptRating(@PathParam("id") Integer id){
        Boolean result = rs.acceptRating(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteRatingById/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRatingById(@PathParam("id") Integer id){
        Boolean result = rs.deleteRatingById(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createRating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRating(Ratings rating){
        String result = rs.createRating(rating);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
