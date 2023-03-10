/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
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
    
    public List<Ratings> getAllRatings() throws Exception{
        List<Ratings> result = Ratings.getAllRatings();
        return result;
    } 
    
    public List<Ratings> getAllNotAcceptedRatings() throws Exception{
        List<Ratings> result = Ratings.getAllNotAcceptedRatings();
        return result;
    }
            
    public List<Ratings> getAllRatingsByRatinger(Integer user_id) throws Exception{
        List<Ratings> result = Ratings.getAllRatingsByRatinger(user_id);
        return result;
    }
            
    public List<Ratings> getAllRatingsByRatinged(Integer user_id) throws Exception{
        List<Ratings> result = Ratings.getAllRatingsByRatinged(user_id);
        return result;
    }
            
    public Boolean updateRatingById(Ratings rating) throws Exception{
        Boolean result = Ratings.updateRatingById(rating);
        return result;
    }
            
    public Boolean acceptRating(Integer id) throws Exception{
        Boolean result = Ratings.acceptRating(id);
        return result;
    }
            
    public Boolean deleteRatingById(Integer id) throws Exception{
        Boolean result = Ratings.deleteRatingById(id);
        return result;
    }
    
    public String createRating(Ratings rating) throws Exception{
        String result = Ratings.createRating(rating);
        return result;
    }
          
}
