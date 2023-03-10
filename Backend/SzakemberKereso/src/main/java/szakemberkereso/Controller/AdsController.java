/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
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
import szakemberkereso.Model.Ads;
import szakemberkereso.Service.AdsService;
import szakemberkereso.Service.ResponseService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Ads")
public class AdsController {

    @Context
    private UriInfo context;

    private AdsService as = new AdsService();
    
    /**
     * Creates a new instance of AdsController
     */
    public AdsController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.AdsController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AdsController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("createAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAd(Ads ad){        
        JSONObject obj = new JSONObject();
        try{
            Integer result = as.createAd(ad);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen l??trehozta a hirdet??st!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("updateAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAd(Ads ad){        
        JSONObject obj = new JSONObject();
        try{
            as.updateAd(ad);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen m??dos??totta a hirdet??st!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @GET
    @Path("getAllAcceptedAds")
    public Response getAllAcceptedAds(){        
        JSONObject obj = new JSONObject();
        try{
            List<Ads> result = as.getAllAcceptedAds();
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte az elfogadott hirdet??seket!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllNonAcceptedAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllNonAcceptedAds(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Ads> result = as.getAllNonAcceptedAds(userId);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte azokat a hirdet??seket amik m??g nem lettek elfogadva!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("acceptAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptAd(Ads ad){        
        JSONObject obj = new JSONObject();
        try{
            as.acceptAd(ad);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen elfogadta a hirdet??st!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAds(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Ads> result = as.getAllAds(userId);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte az ??sszes hirdet??st!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllAdsByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAdsByUserId(Ads ad){        
        JSONObject obj = new JSONObject();
        try{
            List<Ads> result = as.getAllAdsByUserId(ad);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte a felhaszn??l??hoz tartoz?? hirdet??seket!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("filteringAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filteringAds(Ads ad){        
        JSONObject obj = new JSONObject();
        try{
            List<Ads> result = as.filteringAds(ad);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte sz??rve a hirdet??seket!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @GET
    @Path("getAdsById/{id}")
    public Response getAdsById(@PathParam("id") Integer id){        
        JSONObject obj = new JSONObject();
        try{
            Ads result = as.getAdsById(id);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte a hirdet??st!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAd(Ads ad){        
        JSONObject obj = new JSONObject();
        try{
            as.deleteAd(ad);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen t??r??lte a hirdet??st!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
