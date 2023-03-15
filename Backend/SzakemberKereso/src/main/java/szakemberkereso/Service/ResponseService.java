/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import szakemberkereso.Exception.EmailException;
import szakemberkereso.Exception.PasswordException;

/**
 *
 * @author Sharkz
 */
public class ResponseService {
    
    private static Response createErrorResponse(Response.Status status, String message) {
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        return Response.status(status).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    public static Response handleExceptions(Exception e) {
        if(e instanceof NotFoundException){
            return createErrorResponse(Response.Status.NOT_FOUND, e.getMessage());
        }
        else if(e instanceof ForbiddenException){
            return createErrorResponse(Response.Status.FORBIDDEN, e.getMessage());
        } 
        else if(e instanceof AuthenticationFailedException){
            return createErrorResponse(Response.Status.UNAUTHORIZED, e.getMessage());
        } 
        else if(e instanceof EmailException || e instanceof PasswordException){
            return createErrorResponse(Response.Status.BAD_REQUEST, e.getMessage());
        } 
        else{
            return createErrorResponse(Response.Status.SERVICE_UNAVAILABLE, e.getMessage());
        }
    }
    
}
