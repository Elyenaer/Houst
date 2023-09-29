package setting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ManagerAccess {
	private static Properties props;
    
    public ManagerAccess(){
    	init();
    }
    
    private void init() {
        props = new Properties();
        try (InputStream resourceStream = ManagerAccess.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (resourceStream != null) {
                props.load(resourceStream);
            } else {
                throw new IOException("db.properties not found in the classpath.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getPass() {
    	return props.getProperty("password");
    }
    
    public String getUser() {
    	return props.getProperty("user");
    }
    
}
