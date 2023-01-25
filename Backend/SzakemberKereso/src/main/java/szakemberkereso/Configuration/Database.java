
package szakemberkereso.Configuration;

public class Database {
    
    private static final String persistenceUnitName = "szakemberkereso_SzakemberKereso_war_1.0-SNAPSHOTPU";
    
    public static String getPuName(){
        return Database.persistenceUnitName;
    }
}

