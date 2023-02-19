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
    
    public String createUser(Users user){
        //Business logic
        if(user.getAddress().getStaircase() == null){
            user.getAddress().setStaircase("");
        }
        if(user.getAddress().getFloor() == null){
            user.getAddress().setFloor(-1);
        }
        if(user.getAddress().getDoor() == null){
            user.getAddress().setDoor(-1);
        }
        
        String result = Users.createUser(user);
        return result;
    }
    
    public String createUserWorker(Users user){
        //Business logic
        if(user.getAddress().getStaircase() == null){
            user.getAddress().setStaircase("");
        }
        if(user.getAddress().getFloor() == null){
            user.getAddress().setFloor(-1);
        }
        if(user.getAddress().getDoor() == null){
            user.getAddress().setDoor(-1);
        }
        if(user.getCompany().getAddress().getStaircase() == null){
            user.getCompany().getAddress().setStaircase("");
        }
        if(user.getCompany().getAddress().getFloor() == null){
            user.getCompany().getAddress().setFloor(-1);
        }
        if(user.getCompany().getAddress().getDoor() == null){
            user.getCompany().getAddress().setDoor(-1);
        }
        
        String result = Users.createUserWorker(user);
        return result;
    }
    
    public String updateUser(Users user){
        String result = Users.updateUser(user);
        return result;
    }
    
    public String changePassword(Users user){
        String result = Users.changePassword(user);
        return result;
    }
    
    public String validateEmailByToken(String token){
        String result = Users.validateEmailByToken(token);
        return result;
    }
    
    public Boolean forgotPassword(String email){
        Boolean result = Users.forgotPassword(email);
        return result;
    }
        
    public String resetPassword(String email, String password, String pwtoken){
        if(Users.resetPassword(email,password,pwtoken)){
            return "Password succesfully changed.";
        }
        else{
            return "There is some problem!";
        }
    }
    
}
