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
        //Business logic
        if(a.getStaircase() == null){
            a.setStaircase("");
        }
        if(a.getFloor() == null){
            a.setFloor(-1);
        }
        if(a.getDoor() == null){
            a.setDoor(-1);
        }
        
        String result = Addresses.createAddress(a);
        return result;
    }
    
    public String updateAddressById(Addresses a){
        //Business logic
        if(a.getStaircase() == null){
            a.setStaircase("");
        }
        if(a.getFloor() == null){
            a.setFloor(-1);
        }
        if(a.getDoor() == null){
            a.setDoor(-1);
        }
        
        String result = Addresses.updateAddressById(a);
        return result;
    }
    
    public Boolean deleteAddressById(Integer id){
        Boolean result = Addresses.deleteAddressById(id);
        return result;
    }
    
}
