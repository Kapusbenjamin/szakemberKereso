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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Model.Images;
import szakemberkereso.Service.ImagesService;
import szakemberkereso.Service.ResponseService;

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
    
    @POST
    @Path("getAllNotAcceptedImages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllNotAcceptedImages(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Images> result = is.getAllNotAcceptedImages();
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a még nem elfogadott képeket!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @GET
    @Path("getAllAcceptedImagesByUserId/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllAcceptedImagesByUserId(@PathParam("userId") Integer user_id){        
        JSONObject obj = new JSONObject();
        try{
            List<Images> result = is.getAllAcceptedImagesByUserId(user_id);
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a felhasználóhoz tartozó összes elfogadott képet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getImagesByUserId")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImagesByUserId(Images image){        
        JSONObject obj = new JSONObject();
        try{
            List<Images> result = is.getImagesByUserId(image.getUserId());
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte a felhasználóhoz tartozó összes képet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("getAllImages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllImages(Integer userId){        
        JSONObject obj = new JSONObject();
        try{
            List<Images> result = is.getAllImages();
            obj.put("result", JSONObject.wrap(result));
            obj.put("message", "Sikeresen lekérte az összes képet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("acceptImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response acceptImage(Images image){        
        JSONObject obj = new JSONObject();
        try{
            is.acceptImage(image.getId());
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen elfogadta a képet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("deleteImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteImage(Images image){        
        JSONObject obj = new JSONObject();
        try{
            is.deleteImage(image.getId());
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen törölte a képet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
    @POST
    @Path("addImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addImage(Images image){        
        JSONObject obj = new JSONObject();
        try{
            is.addImage(image);
            obj.put("result", JSONObject.wrap(true));
            obj.put("message", "Sikeresen hozzáadta a képet!");
            return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception e){
            return ResponseService.handleExceptions(e);
        }
    }
    
}
