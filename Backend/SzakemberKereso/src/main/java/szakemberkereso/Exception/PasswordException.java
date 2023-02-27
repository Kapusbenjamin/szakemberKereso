/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helix.pecscinemaweb.Exceptions;

import java.util.Date;

/**
 *
 * @author asus
 */
public class PasswordException extends Exception {
    private Date created; //homework
    

    public PasswordException(String msg) {
        super(msg);
    }
}
