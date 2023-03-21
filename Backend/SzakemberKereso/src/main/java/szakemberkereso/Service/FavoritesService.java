/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Favorites;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class FavoritesService {
    
    public List<Favorites> getAllfavoritesByUserId(Favorites favorite) throws Exception{
        if (AuthService.isUserAuthorized(favorite.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            List<Favorites> result = Favorites.getAllfavoritesByUserId(favorite.getCurrentUserId());
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public Favorites getFavoriteById(Favorites favorite) throws Exception{
        if (AuthService.isUserAuthorized(favorite.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak a saját kedvencüket kérhetik le
            if(!Objects.equals(favorite.getCurrentUserId(), Favorites.getFavoriteById(favorite.getId()).getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Favorites result = Favorites.getFavoriteById(favorite.getId());
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
            
    public void deleteFavorite(Favorites favorite) throws Exception{
        if (AuthService.isUserAuthorized(favorite.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            Favorites.deleteFavorite(favorite);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public void addFavorite(Favorites favorite) throws Exception{
        if (AuthService.isUserAuthorized(favorite.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            //a user-ek csak saját maguknak adhatnak hozzá kedvencet
            if(!Objects.equals(favorite.getCurrentUserId(), favorite.getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Favorites.addFavorite(favorite);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
}
