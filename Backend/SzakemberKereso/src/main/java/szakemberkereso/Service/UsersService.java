/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.List;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class UsersService {
    
    public Users getUserById(Integer id){
        Users result = Users.getUserById(id);
        return result;
    }
    
    public Users loginUser(String email, String phone, String psw){
        //Business logic
        String us = email;
        if(email == null){
            us = phone;
        }
        Users result = Users.loginUser(us, psw);
        return result;
    }
    
    public Boolean logoutUser(Integer id){
        Boolean result = Users.logoutUser(id);
        return result;
    }
    
    public Boolean deleteUser(Integer id){
        Boolean result = Users.deleteUser(id);
        return result;
    }
    
    public Boolean changeAccess(Integer id){
        Boolean result = Users.changeAccess(id);
        return result;
    }
    
    public List<Users> getAllUsers(){
        List<Users> result = Users.getAllUsers();
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
