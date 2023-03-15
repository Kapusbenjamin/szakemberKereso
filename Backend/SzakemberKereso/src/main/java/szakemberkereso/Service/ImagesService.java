/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Images;

/**
 *
 * @author Sharkz
 */
public class ImagesService {
    
    public List<Images> getAllNotAcceptedImages() throws Exception{
        List<Images> result = Images.getAllNotAcceptedImages();
        return result;
    }
    
    public List<Images> getAllAcceptedImagesByUserId(Integer user_id) throws Exception{
        List<Images> result = Images.getAllAcceptedImagesByUserId(user_id);
        return result;
    }
    
    public List<Images> getImagesByUserId(Integer user_id) throws Exception{
        List<Images> result = Images.getImagesByUserId(user_id);
        return result;
    }
    
    public List<Images> getAllImages() throws Exception{
        List<Images> result = Images.getAllImages();
        return result;
    }
            
    public void acceptImage(Integer id) throws Exception{
        Images.acceptImage(id);
    }
            
    public void deleteImage(Integer id) throws Exception{
        Images.deleteImage(id);
    }
    
    public void addImage(Images image) throws Exception{
        Images.addImage(image);
    }
    
}
