/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Addresses;
import szakemberkereso.Model.Users;
import szakemberkereso.Service.UsersService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Users")
public class UsersController {

    @Context
    private UriInfo context;

    private UsersService us = new UsersService();
    
    /**
     * Creates a new instance of UsersController
     */
    public UsersController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Configuration.UsersController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsersController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    @GET
    @Path("getUserById/{id}")
    public Response getUserById(@PathParam("id") Integer id){
        Users result = us.getUserById(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("loginUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Users user){
        Users result = us.loginUser(user.getEmail(), user.getPhone(), user.getPassword());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("logoutUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logoutUser(Users user){
        Boolean result = us.logoutUser(user.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Integer id){
        Boolean result = us.deleteUser(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changeAccess/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeAccess(@PathParam("id") Integer id){
        Boolean result = us.changeAccess(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllUsers")
    public Response getAllUsers(){
        List<Users> result = us.getAllUsers();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
//    @POST
//    @Path("createAddress")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createAddress(Addresses address){
//        String result = as.createAddress(address);
//        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//    }
//    
//    @POST
//    @Path("updateAddressById")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateAddressById(Addresses address){
//        String result = as.updateAddressById(address);
//        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//    }
//    
//    @DELETE
//    @Path("deleteAddressById/{id}")
//    public Response deleteAddressById(@PathParam("id") Integer id){
//        Boolean result = as.deleteAddressById(id);
//        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
//    }
    
}
