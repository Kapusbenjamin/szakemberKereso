/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Chats;

/**
 *
 * @author Sharkz
 */
public class ChatsService {
    
    public List<Chats> getAllChatsByUserId(Integer id) throws Exception{
        List<Chats> result = Chats.getAllChatsByUserId(id);
        return result;
    }
    
    public String createChat(Chats chat) throws Exception{        
        String result = Chats.createChat(chat);
        return result;
    }
    
}
