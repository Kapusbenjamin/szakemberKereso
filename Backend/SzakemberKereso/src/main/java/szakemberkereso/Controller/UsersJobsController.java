/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

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
import org.json.JSONObject;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.JobTags;
import szakemberkereso.Model.UsersJobs;
import szakemberkereso.Service.AuthService;
import szakemberkereso.Service.ResponseService;
import szakemberkereso.Service.UsersJobsService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("UsersJobs")
public class UsersJobsController {

    @Context
    private UriInfo context;

    private UsersJobsService ujs = new UsersJobsService();
    
    /**
     * Creates a new instance of UsersJobsController
     */
    public UsersJobsController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Configuration.UsersJobsController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsersJobsController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getAllJobsByUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobsByUser(@PathParam("id") Integer id){        
        JSONObject obj = new JSONObject();
        try{
            List<JobTags> result = ujs.getAllJobsByUser(id);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a felhasználóhoz tartozó összes szakmát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("addNewJobToUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewJobToUser(UsersJobs user_job){
        JSONObject obj = new JSONObject();
        try{
            ujs.addNewJobToUser(user_job);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen hozzáadta a szakmát a felhasználóhoz!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteUserJob")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUserJob(UsersJobs user_job){        
        JSONObject obj = new JSONObject();
        try{
            ujs.deleteUserJob(user_job);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen törölte a szakmát a felhasználótól!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
