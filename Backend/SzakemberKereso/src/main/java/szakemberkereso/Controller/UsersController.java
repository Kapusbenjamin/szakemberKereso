/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package szakemberkereso.Controller;

import java.util.List;
import java.util.Objects;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Users;
import szakemberkereso.Service.AuthService;
import szakemberkereso.Service.ResponseService;
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
        JSONObject obj = new JSONObject();
        try{
            Users result = us.getUserById(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte a felhaszn??l??t!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsers(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Users> result = us.getAllUsers(userId);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lek??rte az ??sszes felhaszn??l??t!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("loginUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(Users user){        
        JSONObject obj = new JSONObject();
        try{
            Users result = us.loginUser(user.getEmail(), user.getPhone(), user.getPassword());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen bejelentkezett!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("logoutUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logoutUser(Integer currentUserId){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = us.logoutUser(currentUserId);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen kijelentkezett!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Users user){        
        JSONObject obj = new JSONObject();
        try{
            Integer result = us.createUser(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen regisztr??lt!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("createUserWorker")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUserWorker(Users user){        
        JSONObject obj = new JSONObject();
        try{
            Integer result = us.createUserWorker(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen regisztr??lt szakemberk??nt!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Users user){        
        JSONObject obj = new JSONObject();
        try{
            String result = us.updateUser(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen m??dos??totta a felhaszn??l?? adatait!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(Users user){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = us.deleteUser(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen t??r??lte a felhaszn??l??t!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("changeAccess")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeAccess(Users user){        
        JSONObject obj = new JSONObject();
        try{
            Boolean result = us.changeAccess(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen m??dos??totta a jogosults??g??t szakemberr??!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changePassword(Users user){        
        JSONObject obj = new JSONObject();
        try{
            String result = us.changePassword(user);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen m??dos??totta a jelszav??t!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("validateEmailByToken")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateEmailByToken(@QueryParam("t") String token){        
        JSONObject obj = new JSONObject();
        try{
            String result = us.validateEmailByToken(token);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen meger??s??tette a fi??kj??t!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("forgotPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response forgotPassword(String email){ 
        JSONObject obj = new JSONObject();
        try{
            Boolean result = us.forgotPassword(email);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen elk??ldt??k E-mailben az ??j jelsz??hoz sz??ks??ges linket!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("resetPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPassword(Users u){        
        JSONObject obj = new JSONObject();
        try{
            String email = u.getEmail();
            String password = u.getPassword();
            String pwtoken = u.getToken();

            String result = us.resetPassword(email, password, pwtoken);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen be??ll??totta az ??j jelsz??t!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
