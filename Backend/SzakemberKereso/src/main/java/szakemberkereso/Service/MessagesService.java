/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Messages;

/**
 *
 * @author Sharkz
 */
public class MessagesService {
    
    public List<Messages> getAllMessagesBetweenUsers(Messages message) throws Exception{
        if (AuthService.isUserAuthorized(message.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak a saját beszélgetéseiket kérhetik le
            if(Objects.equals(message.getCurrentUserId(), message.getSenderId()) || Objects.equals(message.getCurrentUserId(), message.getReceiverId())){
                List<Messages> result = Messages.getAllMessagesBetweenUsers(message);
                return result;
            }
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public List<Messages> getAllMessages(Integer user_id) throws Exception{
        //csak ADMIN kérheti le
        if (AuthService.isUserAuthorized(user_id, new Roles[]{Roles.ADMIN})){
            List<Messages> result = Messages.getAllMessages();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
            
    public void checkMessage(Messages message) throws Exception{
        if (AuthService.isUserAuthorized(message.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak a saját beszélgetéseiket fogadhatják el (currentUserId)
            Messages.checkMessage(message);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public void createMessage(Messages message) throws Exception{
        if (AuthService.isUserAuthorized(message.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak saját üzenetet hozhatnak létre
            if(!Objects.equals(message.getCurrentUserId(), message.getSenderId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Messages.createMessage(message);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
            
}
