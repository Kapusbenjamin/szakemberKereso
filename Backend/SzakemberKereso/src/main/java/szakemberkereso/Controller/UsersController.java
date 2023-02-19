/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    
    
    @POST
    @Path("getUserById")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUserById(Users user){
        Users result = us.getUserById(user.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers(Integer userId){
        List<Users> result = us.getAllUsers();
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
    @Path("createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Users user){
        String result = us.createUser(user);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createUserWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserWorker(Users user){
        String result = us.createUserWorker(user);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Users user){
        String result = us.updateUser(user);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(Users user){
        Boolean result = us.deleteUser(user.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changeAccess")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeAccess(Users user){
        Boolean result = us.changeAccess(user.getId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword(Users user){
        String result = us.changePassword(user);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("validateEmailByToken")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateEmailByToken(@QueryParam("t") String token){
        String result = us.validateEmailByToken(token);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("forgotPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response forgotPassword(Users user){
        Boolean result = us.forgotPassword(user.getEmail());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("resetPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPassword(Users u){
        String email = u.getEmail();
        String password = u.getPassword();
        String pwtoken = u.getToken();
        
        String result = us.resetPassword(email, password, pwtoken);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
