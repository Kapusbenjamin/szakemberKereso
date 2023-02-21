/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package szakemberkereso.Model;

/**
 *
 * @author Sharkz
 */
public enum Roles {
    
    USER, WORKER, ADMIN;
    
    public static Roles getRoleByCode(Integer roleCode){
        switch (roleCode) {
            case 0:
                return Roles.USER;
            case 1:
                return Roles.WORKER;
            case 2:
                return Roles.ADMIN;
            default:
                return null;
        }
    }
    
}
