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
    
    public static String getRoleByCode(Integer roleCode){
        switch (roleCode) {
            case 0:
                return Roles.USER.toString();
            case 1:
                return Roles.WORKER.toString();
            case 2:
                return Roles.ADMIN.toString();
            default:
                return null;
        }
    }
    
}
