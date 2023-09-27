package connect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ManagerAccess {
	private static Properties props;
    
    ManagerAccess() throws FileNotFoundException, IOException{
    	props = new Properties();
        props.load(new FileInputStream("db.properties"));
    }
    
    public static String getPass() {
    	return props.getProperty("password");
    }
    
    public static String getUser() {
    	return props.getProperty("user");
    }
    
}
