/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package szakemberkereso.Configuration;

/**
 *
 * @author Sharkz
 */
public class Email {
    public enum EmailConfig{
        EMAIL("bkap100@gmail.com"), //SENDER
        PASSWORD("htmeuzroxpoyvgpo"),
        HOST("smtp.gmail.com"),
        PORT("465"),
        CONFIRMLINK("http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/validateEmailByToken?t="),
        PWLINK("http://127.0.0.1:8080/SzakemberKereso-1.0-SNAPSHOT/webresources/Users/");
        
        private String value;
        EmailConfig(String value){
            this.value = value;
        }
        
        public String get(){
            return this.value;
        }
    }
}
