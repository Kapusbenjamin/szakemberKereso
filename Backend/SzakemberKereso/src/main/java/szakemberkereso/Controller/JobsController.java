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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Jobs;
import szakemberkereso.Service.JobsService;

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
        Jobs result = js.getJobById(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteJob")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteJob(Jobs job){
        Boolean result = js.deleteJob(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changeJobStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeJobStatus(Jobs job){
        Boolean result = js.changeJobStatus(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllJobs")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobs(Integer userId){
        List<Jobs> result = js.getAllJobs();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllJobsByWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobsByWorker(Jobs job){
        List<Jobs> result = js.getAllJobsByWorker(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllJobsByCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllJobsByCustomer(Jobs job){
        List<Jobs> result = js.getAllJobsByCustomer(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createJob")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJob(Jobs job){
        String result = js.createJob(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateJobByWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJobByWorker(Jobs job){
        String result = js.updateJobByWorker(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateJobByCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJobByCustomer(Jobs job){
        String result = js.updateJobByCustomer(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("acceptByWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptByWorker(Jobs job){
        Boolean result = js.acceptByWorker(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("acceptByCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptByCustomer(Jobs job){
        Boolean result = js.acceptByCustomer(job);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    
}
