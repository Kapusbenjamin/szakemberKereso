/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Counties;

/**
 *
 * @author Sharkz
 */
public class CountiesService {
    
    public List<Counties> getAllCounties(){
        List<Counties> result = Counties.getAllCounties();
        return result;
    }
    
}
