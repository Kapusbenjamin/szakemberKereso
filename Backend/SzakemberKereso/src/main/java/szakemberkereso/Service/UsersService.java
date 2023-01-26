/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class UsersService {
    
    public String getUserById(Integer id){
        String result = Users.getUserById(id);
        return result;
    }
    
//    public String createAddress(Addresses a){
//        String result = Addresses.createAddress(a);
//        return result;
//    }
//    
//    public String updateAddressById(Addresses a){
//        String result = Addresses.updateAddressById(a);
//        return result;
//    }
//    
//    public Boolean deleteAddressById(Integer id){
//        Boolean result = Addresses.deleteAddressById(id);
//        return result;
//    }
    
    
}
