/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import szakemberkereso.Model.Companies;

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
        
        Companies.createCompany(company);
    }
    
    public void updateCompanyById(Companies company) throws Exception{
        Companies.updateCompanyById(company);
    }
    
    public void deleteCompanyById(Companies company) throws Exception{
        Companies.deleteCompanyById(company);
    }
    
}
