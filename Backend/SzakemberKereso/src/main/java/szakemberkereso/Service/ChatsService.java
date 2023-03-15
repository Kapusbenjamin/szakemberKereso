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
import szakemberkereso.Model.Chats;

/**
 *
 * @author Sharkz
 */
public class ChatsService {
    
    public List<Chats> getAllChatsByUserId(Chats chat) throws Exception{
        if (AuthService.isUserAuthorized(chat.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak a saját beszélgetéseiket kérhetik le
            if(!Objects.equals(chat.getCurrentUserId(), chat.getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            List<Chats> result = Chats.getAllChatsByUserId(chat.getUserId());
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public void createChat(Chats chat) throws Exception{
        if (AuthService.isUserAuthorized(chat.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak saját beszélgetéseket hozhatnak létre
            if(Objects.equals(chat.getCurrentUserId(), chat.getSenderId()) || Objects.equals(chat.getCurrentUserId(), chat.getReceiverId())){
                Chats.createChat(chat);
            }
            else{
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }        
    }
    
}
