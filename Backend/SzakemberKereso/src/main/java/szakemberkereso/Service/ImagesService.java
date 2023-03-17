/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import java.util.Objects;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Images;

/**
 *
 * @author Sharkz
 */
public class ImagesService {
    
    public List<Images> getAllNotAcceptedImages(Integer userId) throws Exception{
        //csak ADMIN kérheti le
        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})){
            List<Images> result = Images.getAllNotAcceptedImages();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<Images> getAllAcceptedImagesByUserId(Integer user_id) throws Exception{
        List<Images> result = Images.getAllAcceptedImagesByUserId(user_id);
        return result;
    }
    
    public List<Images> getImagesByUserId(Images image) throws Exception{
        //szakember kérheti le
        if (AuthService.isUserAuthorized(image.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak a saját képeiket láthatják (ahol van nem elfogadott is)
            if(!Objects.equals(image.getCurrentUserId(), image.getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            List<Images> result = Images.getImagesByUserId(image.getUserId());
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<Images> getAllImages(Integer user_id) throws Exception{
        //ADMIN kérheti le
        if (AuthService.isUserAuthorized(user_id, new Roles[]{Roles.ADMIN})){
            List<Images> result = Images.getAllImages();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
            
    public void acceptImage(Images image) throws Exception{
        //ADMIN fogadhatja el a képeket
        if (AuthService.isUserAuthorized(image.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
            Images.acceptImage(image.getId());
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
            
    public void deleteImage(Images image) throws Exception{
        // USER jogúnak nem lehet képe, nem is törölhet 
        if (AuthService.isUserAuthorized(image.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a WORKER-ek csak a saját képeiket törölhetik
            if(AuthService.isUserAuthorized(image.getCurrentUserId(), new Roles[]{Roles.WORKER})){
                if(!Objects.equals(image.getCurrentUserId(), Images.getImageById(image.getId()).getUserId())){
                    throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
                }
                Images.deleteImage(image.getId());
            }
            Images.deleteImage(image.getId());
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void addImage(Images image) throws Exception{
        // USER jogúnak nem lehet képe, nem is adhat hozzá senkihez 
        if (AuthService.isUserAuthorized(image.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a userek csak saját magukhoz adhatnak hozzá képeket
            if(!Objects.equals(image.getCurrentUserId(), image.getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Images.addImage(image);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
}
