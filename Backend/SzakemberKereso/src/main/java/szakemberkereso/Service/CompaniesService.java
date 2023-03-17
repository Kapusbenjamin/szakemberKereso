/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import java.util.Objects;
import javax.mail.AuthenticationFailedException;
import javax.ws.rs.ForbiddenException;
import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Companies;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class CompaniesService {
    
    public Companies getCompanyById(Integer id) throws Exception{
        Companies result = Companies.getCompanyById(id);
        return result;
    }
    
    public void createCompany(Companies company) throws Exception{
        //Business logic
        if(company.getAddress().getStaircase() == null){
            company.getAddress().setStaircase("");
        }
        if(company.getAddress().getFloor() == null){
            company.getAddress().setFloor(-1);
        }
        if(company.getAddress().getDoor() == null){
            company.getAddress().setDoor(-1);
        }
        
        //USER jogosultsággal nem hozhat létre céget, és csak magának adhatja hozzá (currentUserId)
        if (AuthService.isUserAuthorized(company.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            Companies.createCompany(company);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void updateCompanyById(Companies company) throws Exception{
        //USER jogosultsággal nem lehet cége
        if (AuthService.isUserAuthorized(company.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak a saját cégeiket módosíthatják
            if(!Objects.equals(company.getId(), Users.getUserById(company.getCurrentUserId()).getCompanyId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Companies.updateCompanyById(company);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
    public void deleteCompanyById(Companies company) throws Exception{
        //USER jogosultsággal nem lehet cége
        if (AuthService.isUserAuthorized(company.getCurrentUserId(), new Roles[]{Roles.ADMIN, Roles.WORKER})){
            //a user-ek csak a saját cégeiket törölhetik
            if(!Objects.equals(company.getId(), Users.getUserById(company.getCurrentUserId()).getCompanyId())){
                throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
            }
            Companies.deleteCompanyById(company);
        }
        else{
            throw new ForbiddenException("Nincs jogosultsága ehhez a kéréshez.");
        }
    }
    
}
