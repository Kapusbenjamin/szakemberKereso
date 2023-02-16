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
import szakemberkereso.Model.Messages;
import szakemberkereso.Service.MessagesService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Messages")
public class MessagesController {

    @Context
    private UriInfo context;

    private MessagesService ms = new MessagesService();
    
    /**
     * Creates a new instance of MessagesController
     */
    public MessagesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.MessagesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MessagesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    @POST
    @Path("getAllMessagesBetweenUsers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllMessagesBetweenUsers(Messages message){
        List<Messages> result = ms.getAllMessagesBetweenUsers(message);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getAllMessages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllMessages(Integer userId){
        List<Messages> result = ms.getAllMessages();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("checkMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkMessage(Messages message){
        Boolean result = ms.checkMessage(message);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(Messages message){
        String result = ms.createMessage(message);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
