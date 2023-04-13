/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Addresses;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class AddressesService {
    
    public Addresses getAddressById(Integer id) throws Exception{
        Addresses result = Addresses.getAddressById(id);
        return result;
    }
    
//    public String createAddress(Addresses a){
//        //Business logic
//        if(a.getStaircase() == null){
//            a.setStaircase("");
//        }
//        if(a.getFloor() == null){
//            a.setFloor(-1);
//        }
//        if(a.getDoor() == null){
//            a.setDoor(-1);
//        }
//        
//        String result = Addresses.createAddress(a);
//        return result;
//    }
    
    public void updateAddressById(Addresses a) throws Exception{
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
        
        if (AuthService.isUserAuthorized(a.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            //a user-ek csak a saját vagy a saját cég address-üket módosíthatják
            if(!Objects.equals(Addresses.getAddressById(a.getId()).getId(), Users.getUserById(a.getCurrentUserId()).getAddressId()) && !Objects.equals(Addresses.getAddressById(a.getId()).getId(), Users.getUserById(a.getCurrentUserId()).getCompany().getAddressId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
            }
            Addresses.updateAddressById(a);
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
    public void deleteAddressById(Addresses address) throws Exception{
        if (AuthService.isUserAuthorized(address.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.USER, Roles.WORKER})) {
            if(!AuthService.isUserAuthorized(address.getCurrentUserId(), new Roles[]{Roles.ADMIN})){
                //a nem ADMIN jogosultságú user-ek csak a saját address-üket törölhetik
                if(!Objects.equals(Addresses.getAddressById(address.getId()).getId(), Users.getUserById(address.getCurrentUserId()).getAddressId()) && !Objects.equals(Addresses.getAddressById(address.getId()).getId(), Users.getUserById(address.getCurrentUserId()).getCompany().getAddressId())){
                    throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez!");
                }
            }
            Addresses.deleteAddressById(address.getId());
        }
        else{
            throw new AuthenticationFailedException("Nem sikerült azonosítani!");
        }
    }
    
}
