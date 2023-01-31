/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.AdsCounties;
import szakemberkereso.Model.Counties;

/**
 *
 * @author Sharkz
 */
public class AdsCountiesService {
        
    public String addNewCountyToAd(AdsCounties ad_county){
        String result = AdsCounties.addNewCountyToAd(ad_county);
        return result;
    }
    
    public String deleteCountyFromAd(AdsCounties ad_county){
        String result = AdsCounties.deleteCountyFromAd(ad_county);
        return result;
    }
    
    public List<Counties> getAllCountiesByAd(Integer ad_id_in){
        List<Counties> result = AdsCounties.getAllCountiesByAd(ad_id_in);
        return result;
    }
    
}
