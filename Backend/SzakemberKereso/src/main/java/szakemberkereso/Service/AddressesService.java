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
    
    public Addresses getAddressById(Integer id){
        Addresses result = Addresses.getAddressById(id);
        return result;
    }
    
    public String createAddress(Addresses a){
        String result = Addresses.createAddress(a);
        return result;
    }
    
    public String updateAddressById(Addresses a){
        String result = Addresses.updateAddressById(a);
        return result;
    }
    
    public Boolean deleteAddressById(Integer id){
        Boolean result = Addresses.deleteAddressById(id);
        return result;
    }
    
}
