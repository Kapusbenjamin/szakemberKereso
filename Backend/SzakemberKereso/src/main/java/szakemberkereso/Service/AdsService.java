/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Ads;

/**
 *
 * @author Sharkz
 */
public class AdsService {
    
    public Integer createAd(Ads ad){
        Integer result = Ads.createAd(ad);
        return result;
    }
    
    public String updateAd(Ads ad){
        String result = Ads.updateAd(ad);
        return result;
    }
    
    public List<Ads> getAllAcceptedAds(){
        List<Ads> result = Ads.getAllAcceptedAds();
        return result;
    }
    
    public List<Ads> getAllNonAcceptedAds(){
        List<Ads> result = Ads.getAllNonAcceptedAds();
        return result;
    }
    
    public Boolean acceptAds(Integer id){
        Boolean result = Ads.acceptAds(id);
        return result;
    }
    
    public List<Ads> getAllAds(){
        List<Ads> result = Ads.getAllAds();
        return result;
    }
    
    public List<Ads> getAllAdsByUserId(Integer user_id){
        List<Ads> result = Ads.getAllAdsByUserId(user_id);
        return result;
    }
    
    public Ads getAdsById(Integer id){
        Ads result = Ads.getAdsById(id);
        return result;
    }
    
    public Boolean deleteAd(Integer id){
        Boolean result = Ads.deleteAd(id);
        return result;
    }
    
    
    
    
    
}
