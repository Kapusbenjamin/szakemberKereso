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
import szakemberkereso.Model.AdsCounties;
import szakemberkereso.Model.Counties;
import szakemberkereso.Service.AdsCountiesService;

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
        String result = acs.addNewCountyToAd(ad_county);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteCountyFromAd")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCountyFromAd(AdsCounties ad_county){
        String result = acs.deleteCountyFromAd(ad_county);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllCountiesByAd/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllCountiesByAd(@PathParam("id") Integer ad_id_in){
        List<Counties> result = acs.getAllCountiesByAd(ad_id_in);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    
}
