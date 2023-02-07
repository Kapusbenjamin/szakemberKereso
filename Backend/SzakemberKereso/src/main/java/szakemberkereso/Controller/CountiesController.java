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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Addresses;
import szakemberkereso.Model.Counties;
import szakemberkereso.Service.CountiesService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Counties")
public class CountiesController {

    @Context
    private UriInfo context;

    private CountiesService cs = new CountiesService();
    
    /**
     * Creates a new instance of CountiesController
     */
    public CountiesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.CountiesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CountiesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getAllCounties")
    public Response getAllCounties(){
        List<Counties> result = cs.getAllCounties();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
            
}