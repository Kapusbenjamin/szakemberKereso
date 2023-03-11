/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Messages;

/**
 *
 * @author Sharkz
 */
public class MessagesService {
    
    public List<Messages> getAllMessagesBetweenUsers(Messages message) throws Exception{
        List<Messages> result = Messages.getAllMessagesBetweenUsers(message);
        return result;
    }
    
    public List<Messages> getAllMessages() throws Exception{
        List<Messages> result = Messages.getAllMessages();
        return result;
    }
            
    public Boolean checkMessage(Messages message) throws Exception{
        Boolean result = Messages.checkMessage(message);
        return result;
    }
    
    public String createMessage(Messages message) throws Exception{
        String result = Messages.createMessage(message);
        return result;
    }
            
}
