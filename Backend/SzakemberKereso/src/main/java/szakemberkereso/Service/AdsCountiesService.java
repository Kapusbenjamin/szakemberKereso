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
import szakemberkereso.Model.Ads;
import szakemberkereso.Model.AdsCounties;
import szakemberkereso.Model.Counties;

/**
 *
 * @author Sharkz
 */
public class AdsCountiesService {
        
    public void addNewCountyToAd(AdsCounties ad_county) throws Exception{
        //a USER jogosultságú user-ek nem módosíthatnak hirdetéseket
        if (AuthService.isUserAuthorized(ad_county.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak a saját hirdetésüket módosíthatják
            if(!Objects.equals(ad_county.getCurrentUserId(), Ads.getAdById(ad_county.getAdId()).getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            AdsCounties.addNewCountyToAd(ad_county);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
        }
    }
    
    public void deleteCountyFromAd(AdsCounties ad_county) throws Exception{
        //a USER jogosultságú user-ek nem törölhetnek megyét hirdetésből
        if (AuthService.isUserAuthorized(ad_county.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak a saját hirdetésüket módosíthatják
            if(!Objects.equals(ad_county.getCurrentUserId(), Ads.getAdById(ad_county.getAdId()).getUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            AdsCounties.deleteCountyFromAd(ad_county);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
        }
    }
    
    public List<Counties> getAllCountiesByAd(Integer ad_id_in) throws Exception{
        List<Counties> result = AdsCounties.getAllCountiesByAd(ad_id_in);
        return result;
    }
    
}
