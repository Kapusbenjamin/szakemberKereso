/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Favorites;

/**
 *
 * @author Sharkz
 */
public class FavoritesService {
    
    public List<Favorites> getAllfavoritesByUserId(Integer user_id){
        List<Favorites> result = Favorites.getAllfavoritesByUserId(user_id);
        return result;
    }
    
    public Favorites getFavoriteById(Integer id){
        Favorites result = Favorites.getFavoriteById(id);
        return result;
    }
            
    public Boolean deleteFavorite(Integer id){
        Boolean result = Favorites.deleteFavorite(id);
        return result;
    }
    
    public String addFavorite(Favorites favorite){
        String result = Favorites.addFavorite(favorite);
        return result;
    }
    
}
