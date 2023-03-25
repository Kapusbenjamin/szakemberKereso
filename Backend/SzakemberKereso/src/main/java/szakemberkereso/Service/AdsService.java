/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Ads;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class AdsService {
    
    public Integer createAd(Ads ad) throws Exception{
        //a USER jogosultságú user-ek nem hozhatnak létre hirdetéseket
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak saját maguknak hozhatnak létre hirdetéseket
            if(!Objects.equals(ad.getCurrentUserId(), ad.getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Integer result = Ads.createAd(ad);
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
        }
    }
    
    public void updateAd(Ads ad) throws Exception{
        //a USER jogosultságú user-ek nem módosíthatnak hirdetéseket
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak a saját hirdetésüket módosíthatják
            if(!Objects.equals(ad.getCurrentUserId(), Ads.getAdById(ad.getId()).getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Ads.updateAd(ad);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
        }
    }
    
    public List<Ads> getAllAcceptedAds() throws Exception{
        List<Ads> result = Ads.getAllAcceptedAds();
        return result;
    }
    
    public List<Ads> getAllNonAcceptedAds(Integer userId) throws Exception{
        //a nem ADMIN jogosultságú user-ek nem kérhetik le
        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})) {
            List<Ads> result = Ads.getAllNonAcceptedAds();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void acceptAd(Ads ad) throws Exception{
        //a nem ADMIN jogosultságú user-ek nem fogadhatják el
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN})) {
            Ads.acceptAd(ad.getId());
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<Ads> getAllAds(Integer userId) throws Exception{
        //a nem ADMIN jogosultságú user-ek nem kérhetik le
        if (AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})) {
            List<Ads> result = Ads.getAllAds();
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
        
    }
    
    public List<Ads> getAllAdsByUserId(Ads ad) throws Exception{
        //a USER jogosultságú user-ek nem módosíthatnak hirdetéseket
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER, Roles.USER})){
            if(AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.WORKER, Roles.USER})){
                if(AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.WORKER})){
                    //a WORKER-ek csak a saját hirdetésüknél kérhetik le a még nem elfogadottakat is
                    if(Objects.equals(ad.getCurrentUserId(), Users.getIdIfUserValid(ad.getUserId()))){
                        List<Ads> result = Ads.getAllAdsByUserId(ad.getUserId());
                        return result;
                    }
                }
                //a USER jogosultságú user-ek és azok a WORKER-ek akik más hirdetéseit akarják lekérni csak az elfogadott hirdetéseket láthatják
                List<Ads> ads = Ads.getAllAdsByUserId(Users.getIdIfUserValid(ad.getUserId()));
                List<Ads> result = new ArrayList<>();
                for(Ads a : ads){
                    if(a.getStatus() == 1){
                        result.add(a);
                    }
                }
                return result;
            }
            List<Ads> result = Ads.getAllAdsByUserId(Users.getIdIfUserValid(ad.getUserId()));
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public List<Ads> filteringAds(Ads ad) throws Exception{
        List<Ads> result = Ads.filteringAds(ad);
        return result;
    }
    
    public Ads getAdsById(Integer id) throws Exception{
        Ads result = Ads.getAdById(id);
        return result;
    }
    
    public void deleteAd(Ads ad) throws Exception{
        //a USER jogosultságú user-ek nem törölhetnek hirdetéseket
        //a nem ADMIN jogosultságú user-ek csak a saját hirdetésüket törölhetik
        if (AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
            Ads.deleteAd(ad.getId());
        }
        else if(AuthService.isUserAuthorized(ad.getCurrentUserId(), new Roles[]{Roles.WORKER})){
            if(!Objects.equals(ad.getCurrentUserId(), Ads.getAdById(ad.getId()).getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Ads.deleteAd(ad.getId());
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
        }        
    }
    
}
