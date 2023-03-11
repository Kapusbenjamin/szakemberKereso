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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Model.Counties;
import szakemberkereso.Service.CountiesService;
import szakemberkereso.Service.ResponseService;

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
        JSONObject obj = new JSONObject();
        try{
            List<Counties> result = cs.getAllCounties();
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte az összes megyét!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
