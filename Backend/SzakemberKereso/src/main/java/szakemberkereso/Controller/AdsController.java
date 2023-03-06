/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Ads;
import szakemberkereso.Model.Users;
import szakemberkereso.Service.AdsService;
import szakemberkereso.Service.AuthService;

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
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //a USER jogosultságú user-ek nem hozhatnak létre hirdetéseket
            Integer result = as.createAd(ad);
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        else{
            return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
        }
    }
    
    @POST
    @Path("updateAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAd(Ads ad){
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            //a user-ek csak a saját hirdetésüket módosíthatják
            if(!Objects.equals(ad.getCurrentUserId(), Ads.getAdsById(ad.getId()).getUserId())){
                return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
            }
            String result = as.updateAd(ad);
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        else{
            return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
        }
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
        //a nem ADMIN jogosultságú user-ek nem kérhetik le
        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})) {
            List<Ads> result = as.getAllNonAcceptedAds();
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        else{
            return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
        }
    }
    
    @POST
    @Path("acceptAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptAd(Ads ad){
        //a nem ADMIN jogosultságú user-ek nem fogadhatják el
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN})) {
            Boolean result = as.acceptAd(ad.getId());
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        else{
            return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
        }
    }
    
    @POST
    @Path("getAllAds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAds(Integer userId){
        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})){
            List<Ads> result = as.getAllAds();
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        else{
            return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
        }
    }
    
    @POST
    @Path("getAllAdsByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAdsByUserId(Ads ad){
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})) {
            List<Ads> result = as.getAllAdsByUserId(ad.getUserId());
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        else if(AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.USER})){
            //a USER jogosultságú user-ek csak az elfogadott hirdetéseket láthatják
            List<Ads> ads = as.getAllAdsByUserId(ad.getUserId());
            List<Ads> result = new ArrayList<>();
            for(Ads a : ads){
                if(a.getStatus() == 1){
                    result.add(a);
                }
            }
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
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
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            if(!AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
                //a nem ADMIN jogosultságú user-ek csak a saját hirdetésüket törölhetik
                if(!Objects.equals(ad.getCurrentUserId(), Ads.getAdsById(ad.getId()).getUserId())){
                    return Response.status(Response.Status.FORBIDDEN).entity("Nincs jogosultsága ehhez a kéréshez.").build();
                }
            }
            Boolean result = as.deleteAd(ad.getId());
            return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Nem sikerült azonosítani.").build();
    }
    
}
