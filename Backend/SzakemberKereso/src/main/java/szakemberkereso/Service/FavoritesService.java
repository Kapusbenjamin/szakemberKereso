/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Favorites;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class FavoritesService {
    
    public List<Favorites> getAllfavoritesByUserId(Integer user_id) throws Exception{
        List<Favorites> result = Favorites.getAllfavoritesByUserId(Users.getUserById(user_id).getId());
        return result;
    }
    
    public Favorites getFavoriteById(Integer id) throws Exception{
        Favorites result = Favorites.getFavoriteById(id);
        return result;
    }
            
    public void deleteFavorite(Integer id) throws Exception{
        Favorites.deleteFavorite(id);
    }
    
    public void addFavorite(Favorites favorite) throws Exception{
        Favorites.addFavorite(favorite);
    }
    
}
