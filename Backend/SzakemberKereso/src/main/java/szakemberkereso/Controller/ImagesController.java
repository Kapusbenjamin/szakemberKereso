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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import szakemberkereso.Model.Images;
import szakemberkereso.Service.ImagesService;

/**
 * REST Web Service
 *
 * @author Sharkz
 */
@Path("Images")
public class ImagesController {

    @Context
    private UriInfo context;

    private ImagesService is = new ImagesService();
    
    /**
     * Creates a new instance of ImagesController
     */
    public ImagesController() {
    }

    /**
     * Retrieves representation of an instance of szakemberkereso.Controller.ImagesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ImagesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("getAllNotAcceptedImages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllNotAcceptedImages(){
        List<Images> result = is.getAllNotAcceptedImages();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllAcceptedImagesByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAcceptedImagesByUserId(Integer user_id){
        List<Images> result = is.getAllAcceptedImagesByUserId(user_id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getImagesByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImagesByUserId(Integer user_id){
        List<Images> result = is.getImagesByUserId(user_id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("getAllImages")
    public Response getAllImages(){
        List<Images> result = is.getAllImages();
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("acceptImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptImage(Integer id){
        Boolean result = is.acceptImage(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("deleteImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteImage(Integer id){
        Boolean result = is.deleteImage(id);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("addImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addImage(Images image){
        String result = is.addImage(image);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
}
