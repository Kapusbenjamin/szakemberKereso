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
            
    public void checkMessage(Messages message) throws Exception{
        Messages.checkMessage(message);
    }
    
    public void createMessage(Messages message) throws Exception{
        Messages.createMessage(message);
    }
            
}
