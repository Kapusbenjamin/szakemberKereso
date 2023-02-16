/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

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
import szakemberkereso.Model.Companies;
import szakemberkereso.Service.CompaniesService;

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
        Companies result = cs.getCompanyById(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createCompany")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCompany(Companies company){
        String result = cs.createCompany(company);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateCompanyById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCompanyById(Companies company){
        String result = cs.updateCompanyById(company);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteCompanyById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCompanyById(Companies company){
        Boolean result = cs.deleteCompanyById(company);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
