/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Service;

import szakemberkereso.Model.Roles;
import szakemberkereso.Model.Users;

/**
 *
 * @author Sharkz
 */
public class AuthService {

    public boolean isUserAuthorized(Integer userId, Roles roleName) {
        Users user = Users.getUserById(userId);
        if (user != null) {
            Roles role = Roles.getRoleByCode(user.getAccessType());
            if (role.equals(roleName)) {
                return true;
            }
        }
        return false;
    }
    
}
