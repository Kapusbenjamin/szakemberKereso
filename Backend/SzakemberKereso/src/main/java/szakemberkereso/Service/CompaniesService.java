/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import szakemberkereso.Model.Addresses;
import szakemberkereso.Model.Companies;

/**
 *
 * @author Sharkz
 */
public class CompaniesService {
    
    
    public Companies getCompanyById(Integer id){
        Companies result = Companies.getCompanyById(id);
        return result;
    }
    
    public String createCompany(Companies company){
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
        
        String result = Companies.createCompany(company);
        return result;
    }
    
    public String updateCompanyById(Companies company){
        String result = Companies.updateCompanyById(company);
        return result;
    }
    
    public Boolean deleteCompanyById(Companies company){
        Boolean result = Companies.deleteCompanyById(company);
        return result;
    }
    
}
