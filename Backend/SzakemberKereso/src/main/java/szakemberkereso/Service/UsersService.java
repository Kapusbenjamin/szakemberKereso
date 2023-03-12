/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import com.helix.pecscinemaweb.Exceptions.PasswordException;
import java.util.List;
import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Addresses;
import szakemberkereso.Model.Companies;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class UsersService {
    
    public static boolean validatePassword(String pw) throws PasswordException {
        // 8 character
        if(pw.length() < 8){
            throw new PasswordException("The password is not long enough");
        }
        // lowercase letter
        else if(!pw.matches(".*[a-z].*")){
            throw new PasswordException("It must be a lowercase character");
        }
        // uppercase letter
        else if(!pw.matches(".*[A-Z].*")){
            throw new PasswordException("It must be an uppercase character");
        }
        // number
        else if(!pw.matches(".*[0-9].*")){
            throw new PasswordException("It must be a numeric character");
        }
        // Special character
        else if(!pw.matches(".*[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")){
            throw new PasswordException("It must be a special character");
        }
        else{
            return true;
        }
    }
    
    public Users getUserById(Users user) throws Exception{
        Users result = Users.getUserById(user.getId());
        
        //aktuális felhasználó jogosultsága alapján mit kaphat meg        
        if(!AuthService.isUserAuthorized(user.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
            if(result.getDeleted() != 1){
                result.setStatus(null);
                result.setLastLoginAt(null);
                result.setActivatedAt(null);
                result.setUpdatedAt(null);
                result.setDeleted(null);
                return result;
            }
            throw new NotFoundException("Ezt a felhasználót törölték vagy nem létezik!");
        }
        
        return result;
    }
    
    public Users loginUser(String email, String phone, String psw) throws Exception{
        //Business logic
        String us = email;
        if(email == null){
            us = phone;
        }
        Users result = Users.loginUser(us, psw);
        return result;
    }
    
    public Boolean logoutUser(Integer id) throws Exception{
        Boolean result = Users.logoutUser(id);
        return result;
    }
    
    public Boolean deleteUser(Users user) throws Exception{
        if(AuthService.isUserAuthorized(user.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            if(!AuthService.isUserAuthorized(user.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
                //a nem ADMIN jogosultságú user-ek csak saját magukat törölhetik
                if(!Objects.equals(user.getId(), user.getCurrentUserId())){
                    throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
                }
            }
            Boolean result = Users.deleteUser(user.getId());
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public Boolean changeAccess(Users user) throws Exception{
        //csak USER jogosultságú user-ek változtathatnak hozzáférést (csak worker-re)
        if(AuthService.isUserAuthorized(user.getCurrentUserId(), new Roles[]{Roles.USER})) {
            Boolean result = Users.changeAccess(user.getId());
            return result;
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public List<Users> getAllUsers(Integer userId) throws Exception{
        //csak ADMIN kérheti le
        if(AuthService.isUserAuthorized(userId, new Roles[]{Roles.ADMIN})){
            List<Users> result = Users.getAllUsers();
            return result;            
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public Integer createUser(Users user) throws Exception{
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
        
        Integer result = Users.createUser(user);
        return result;
    }
    
    public Integer createUserWorker(Users user) throws Exception{
        //Business logic
        //nem kötelező cím adatok
        if(user.getAddress().getStaircase() == null){
            user.getAddress().setStaircase("");
        }
        if(user.getAddress().getFloor() == null){
            user.getAddress().setFloor(-1);
        }
        if(user.getAddress().getDoor() == null){
            user.getAddress().setDoor(-1);
        }
        
        //cég nem kötelező
        if(user.getCompany() != null){
            if(user.getCompany().getAddress().getStaircase() == null){
                user.getCompany().getAddress().setStaircase("");
            }
            if(user.getCompany().getAddress().getFloor() == null){
                user.getCompany().getAddress().setFloor(-1);
            }
            if(user.getCompany().getAddress().getDoor() == null){
                user.getCompany().getAddress().setDoor(-1);
            }
        }
        else{
            //minden adat beállítása a megfelelő értékre, hogy ne legyen hiba és ne hozzon létre céget
            Companies c = new Companies(null, "", -1, "");
            Addresses a = new Addresses(null, -1, -1, "", "", "", "", -1, -1);
            c.setAddress(a);
            user.setCompany(c);
        }
        
        Integer result = Users.createUserWorker(user);
        return result;
    }
    
    public String updateUser(Users user) throws Exception{
        if (AuthService.isUserAuthorized(user.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //a user-ek csak a saját adataikat módosíthatják
            if(!Objects.equals(user.getId(), user.getCurrentUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            String result = Users.updateUser(user);
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public String changePassword(Users user) throws Exception{
        if(AuthService.isUserAuthorized(user.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //a user-ek csak a saját jelszavukat módosíthatják
            if(!Objects.equals(user.getId(), user.getCurrentUserId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            String result = Users.changePassword(user);
            return result;
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani.");
        }
    }
    
    public String validateEmailByToken(String token) throws Exception{
        String result = Users.validateEmailByToken(token);
        return result;
    }
    
    public Boolean forgotPassword(String email) throws Exception{
        Boolean result = Users.forgotPassword(email);
        return result;
    }
        
    public String resetPassword(String email, String password, String pwtoken) throws Exception{
        if(Users.resetPassword(email,password,pwtoken)){
            return "Password succesfully changed.";
        }
        else{
            return "There is some problem!";
        }
    }
    
}
