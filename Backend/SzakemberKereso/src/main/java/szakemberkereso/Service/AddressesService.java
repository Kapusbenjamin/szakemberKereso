/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Addresses;

/**
 *
 * @author Sharkz
 */
public class AddressesService {
    
    Addresses a = new Addresses();
    
    public Addresses getAddressById(Integer id){
        Addresses result = a.getAddressById(id);
        return result;
    }
    
}
