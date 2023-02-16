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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Ads;
import szakemberkereso.Service.AdsService;

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
        Integer result = as.createAd(ad);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAd(Ads ad){
        String result = as.updateAd(ad);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllAcceptedAds")
    public Response getAllAcceptedAds(){
        List<Ads> result = as.getAllAcceptedAds();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllNonAcceptedAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllNonAcceptedAds(Integer userId){
        List<Ads> result = as.getAllNonAcceptedAds();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("acceptAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptAd(Ads ad){
        Boolean result = as.acceptAd(ad.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAds(Integer userId){
        List<Ads> result = as.getAllAds();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllAdsByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAdsByUserId(Ads ad){
        List<Ads> result = as.getAllAdsByUserId(ad.getUserId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("filteringAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response filteringAds(Ads ad){
        List<Ads> result = as.filteringAds(ad);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAdsById/{id}")
    public Response getAdsById(@PathParam("id") Integer id){
        Ads result = as.getAdsById(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAd(Ads ad){
        Boolean result = as.deleteAd(ad.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
