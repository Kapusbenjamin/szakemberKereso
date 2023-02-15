/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Addresses;
import szakemberkereso.Service.AddressesService;
import szakemberkereso.Service.AuthService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Addresses")
@Stateless
public class AddressesController {

    @Context
    private UriInfo context;
    
    AddressesService as = new AddressesService();
    
    AuthService authService = new AuthService();
    
    /**
     * Creates a new instance of Addresses
     */
    public AddressesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.AddressesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AddressesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getAddressById/{id}")
    public Response getAddressById(@PathParam("id") Integer id){
        Addresses result = as.getAddressById(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAddress(Addresses address){
        String result = as.createAddress(address);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateAddressById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAddressById(Addresses address){
        String result = as.updateAddressById(address);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteAddressById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAddressById(Addresses address){
        if (!authService.isUserAuthorized(address.getUserId(), "ADMIN")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Boolean result = as.deleteAddressById(address.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
