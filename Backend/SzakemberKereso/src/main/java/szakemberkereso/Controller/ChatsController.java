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
import szakemberkereso.Model.Chats;
import szakemberkereso.Service.ChatsService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Chats")
public class ChatsController {

    @Context
    private UriInfo context;
    
    private ChatsService cs = new ChatsService();

    /**
     * Creates a new instance of ChatsController
     */
    public ChatsController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.ChatsController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ChatsController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("getAllChatsByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllChatsByUserId(Chats chat){
        List<Chats> result = cs.getAllChatsByUserId(chat.getUserId());
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("createChat")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createChat(Chats chat){
        String result = cs.createChat(chat);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
