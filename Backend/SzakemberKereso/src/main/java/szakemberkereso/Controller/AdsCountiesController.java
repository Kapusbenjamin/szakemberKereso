/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
import java.util.Objects;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Ads;
import szakemberkereso.Model.AdsCounties;
import szakemberkereso.Model.Counties;
import szakemberkereso.Service.AdsCountiesService;
import szakemberkereso.Service.AuthService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("AdsCounties")
public class AdsCountiesController {

    @Context
    private UriInfo context;

    private AdsCountiesService acs = new AdsCountiesService();
    
    /**
     * Creates a new instance of AdsCountiesController
     */
    public AdsCountiesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.AdsCountiesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AdsCountiesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("addNewCountyToAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCountyToAd(AdsCounties ad_county){
        JSONObject obj = new JSONObject();
        try{
            acs.addNewCountyToAd(ad_county);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen hozzáadta a megyét a hirdetéshez!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(NotFoundException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(ForbiddenException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(AuthenticationFailedException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e) {
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
    }
    
    @POST
    @Path("deleteCountyFromAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCountyFromAd(AdsCounties ad_county){
        JSONObject obj = new JSONObject();
        try{
            acs.deleteCountyFromAd(ad_county);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen törölte a megyét a hirdetésből!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(NotFoundException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(ForbiddenException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(AuthenticationFailedException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e) {
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
    }
    
    @GET
    @Path("getAllCountiesByAd/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllCountiesByAd(@PathParam("id") Integer ad_id_in){
        JSONObject obj = new JSONObject();
        try{
            List<Counties> result = acs.getAllCountiesByAd(ad_id_in);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a hirdetéshez tartozó megyéket!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(NotFoundException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(ForbiddenException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(AuthenticationFailedException e){
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e) {
            obj.put("message", e.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
    }
    
    
}
