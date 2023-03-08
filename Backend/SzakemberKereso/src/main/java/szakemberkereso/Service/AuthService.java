/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import szakemberkereso.Configuration.Roles;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class AuthService {

    public static boolean isUserAuthorized(Integer userId, Roles[] roles) throws Exception {
        Users user = Users.getUserById(userId);
        if (user != null) {
            Roles role = Roles.getRoleByCode(user.getAccessType());
            for (Roles roleName : roles) {
                if(role.equals(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
