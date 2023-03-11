/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

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
import szakemberkereso.Model.Companies;
import szakemberkereso.Service.CompaniesService;
import szakemberkereso.Service.ResponseService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Companies")
public class CompaniesController {

    @Context
    private UriInfo context;

    CompaniesService cs = new CompaniesService();
    
    /**
     * Creates a new instance of CompaniesController
     */
    public CompaniesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.CompaniesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CompaniesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getCompanyById/{id}")
    public Response getCompanyById(@PathParam("id") Integer id){        
        JSONObject obj = new JSONObject();
        try{
            Companies result = cs.getCompanyById(id);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a céget!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("createCompany")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCompany(Companies company){        
        JSONObject obj = new JSONObject();
        try{
            String result = cs.createCompany(company);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen létrehozta a céget!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("updateCompanyById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCompanyById(Companies company){        
        JSONObject obj = new JSONObject();
        try{
            String result = cs.updateCompanyById(company);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen módosította a céget!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteCompanyById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCompanyById(Companies company){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = cs.deleteCompanyById(company);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen törölte a céget!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
