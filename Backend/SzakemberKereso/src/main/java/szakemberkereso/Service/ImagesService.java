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
    
    public List<Images> getAllNotAcceptedImages(){
        List<Images> result = Images.getAllNotAcceptedImages();
        return result;
    }
    
    public List<Images> getAllAcceptedImagesByUserId(Integer user_id){
        List<Images> result = Images.getAllAcceptedImagesByUserId(user_id);
        return result;
    }
    
    public List<Images> getImagesByUserId(Integer user_id){
        List<Images> result = Images.getImagesByUserId(user_id);
        return result;
    }
    
    public List<Images> getAllImages(){
        List<Images> result = Images.getAllImages();
        return result;
    }
            
    public Boolean acceptImage(Integer id){
        Boolean result = Images.acceptImage(id);
        return result;
    }
            
    public Boolean deleteImage(Integer id){
        Boolean result = Images.deleteImage(id);
        return result;
    }
    
    public String addImage(Images image){
        String result = Images.addImage(image);
        return result;
    }
    
}
