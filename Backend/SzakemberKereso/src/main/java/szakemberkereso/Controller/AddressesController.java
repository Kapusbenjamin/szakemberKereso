/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import javax.ejb.Stateless;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Model.Addresses;
import szakemberkereso.Service.AddressesService;
import szakemberkereso.Service.ResponseService;

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
        JSONObject obj = new JSONObject();
        try{
            Addresses result = as.getAddressById(id);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a címet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
//    @POST
//    @Path("createAddress")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createAddress(Addresses address){
//        String result = as.createAddress(address);
//        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//    }
    
    @POST
    @Path("updateAddressById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAddressById(Addresses address){        
        JSONObject obj = new JSONObject();
        try{
            as.updateAddressById(address);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen módosította a címet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteAddressById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAddressById(Addresses address){        
        JSONObject obj = new JSONObject();
        try{
            as.deleteAddressById(address);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen törölte a címet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
