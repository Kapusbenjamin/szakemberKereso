/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Ratings;

/**
 *
 * @author Sharkz
 */
public class RatingsService {
    
    public Ratings getRatingById(Integer id) throws Exception{
        Ratings result = Ratings.getRatingById(id);
        return result;
    }
    
    public List<Ratings> getAllRatings(Integer user_id) throws Exception{
        //csak ADMIN kérheti le
        if (AuthService.isUserAuthorized(user_id, new Roles[]{Roles.ADMIN})){
            List<Ratings> result = Ratings.getAllRatings();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    } 
    
    public List<Ratings> getAllNotAcceptedRatings(Integer user_id) throws Exception{
        //csak ADMIN kérheti le
        if (AuthService.isUserAuthorized(user_id, new Roles[]{Roles.ADMIN})){
            List<Ratings> result = Ratings.getAllNotAcceptedRatings();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
            
    public List<Ratings> getAllRatingsByRatinger(Ratings rating) throws Exception{
        if (AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            if(!AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
                //a user-ek csak a saját értékeléseiket kérhetik le
                if(!Objects.equals(rating.getCurrentUserId(), rating.getRatingerUserId())){
                    throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
                }
                List<Ratings> result = Ratings.getAllRatingsByRatinger(rating.getRatingerUserId());
                return result;
            }
            List<Ratings> result = Ratings.getAllRatingsByRatinger(rating.getRatingerUserId());
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
            
    public List<Ratings> getAllRatingsByRatinged(Ratings rating) throws Exception{
        List<Ratings> result = Ratings.getAllRatingsByRatinged(rating.getRatingedUserId());
        return result;
    }
            
    public void updateRatingById(Ratings rating) throws Exception{
        if (AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak a saját értékelésüket módosíthatják
            if(!Objects.equals(rating.getCurrentUserId(), Ratings.getRatingById(rating.getId()).getRatingerUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Ratings.updateRatingById(rating);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
            
    public void acceptRating(Ratings rating) throws Exception{
        if (AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
            Ratings.acceptRating(rating.getId());
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
            
    public void deleteRatingById(Ratings rating) throws Exception{
        if (AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            if(!AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
                //a nem ADMIN user-ek csak a saját értékeléseiket törölhetik
                if(!Objects.equals(rating.getCurrentUserId(), Ratings.getRatingById(rating.getId()).getRatingerUserId())){
                    throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
                }
                Ratings.deleteRatingById(rating.getId());
            }
            Ratings.deleteRatingById(rating.getId());
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public void createRating(Ratings rating) throws Exception{
        if (AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak értékelőként hozhatnak létre értékelést
            if(!Objects.equals(rating.getCurrentUserId(), rating.getRatingerUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            //a user-ek csak WORKER vagy ADMIN jogú felhasználót értékelhetnek
            else if(AuthService.isUserAuthorized(rating.getRatingedUserId(), new Roles[]{Roles.USER})){
                throw new BadRequestException("Az értékelendő felhasználó nem szakember, ezért nem értékelheti!");
            }
            Ratings.createRating(rating);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
          
    public Boolean canWriteRating(Ratings rating) throws Exception{
        if (AuthService.isUserAuthorized(rating.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            return Ratings.canWriteRating(rating);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
          
}
