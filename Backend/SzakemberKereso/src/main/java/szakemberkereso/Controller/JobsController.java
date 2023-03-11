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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Jobs;
import szakemberkereso.Service.AuthService;
import szakemberkereso.Service.JobsService;
import szakemberkereso.Service.ResponseService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Jobs")
public class JobsController {

    @Context
    private UriInfo context;
    
    private JobsService js = new JobsService();

    /**
     * Creates a new instance of JobsController
     */
    public JobsController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.JobsController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of JobsController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("getJobById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getJobById(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            Jobs result = js.getJobById(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a munkát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteJob")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteJob(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = js.deleteJob(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen törölte a munkát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("changeJobStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeJobStatus(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = js.changeJobStatus(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen módosította a munka státuszát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllJobs")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobs(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Jobs> result = js.getAllJobs(userId);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte az összes munkát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllJobsByWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobsByWorker(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            List<Jobs> result = js.getAllJobsByWorker(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a szakemberhez tartozó összes munkát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }    
    }
    
    @POST
    @Path("getAllJobsByCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobsByCustomer(Jobs job){
        JSONObject obj = new JSONObject();
        try{
            List<Jobs> result = js.getAllJobsByCustomer(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a megrendelőhöz tartozó összes munkát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }    
    }
    
    @POST
    @Path("createJob")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJob(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            String result = js.createJob(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen létrehozta a munkát!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("updateJobByWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJobByWorker(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            String result = js.updateJobByWorker(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen módosította a munkát mint szakember!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("updateJobByCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJobByCustomer(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            String result = js.updateJobByCustomer(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen módosította a munkát mint megrendelő!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("acceptByWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptByWorker(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = js.acceptByWorker(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen elfogadta a munka részleteit mint szakember!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("acceptByCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptByCustomer(Jobs job){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = js.acceptByCustomer(job);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen elfogadta a munka részleteit mint megrendelő!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
