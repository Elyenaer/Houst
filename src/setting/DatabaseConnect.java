package setting;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

public class DatabaseConnect {
	
	public static String start(String table,Map<String, String> parameters,String method) {
		try {
			String apiUrl = "https://elyenaer.tech/houst/api/"+table+"/"+method+".php";

	        //create a URL with string of API
	        URL url = new URI(apiUrl).toURL();

	        //open HTTP connection with URL
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();	        

	        //allow send data
	        connection.setDoOutput(true);

	        //choice method
	        connection.setRequestMethod("POST");

	        //encode parameters in query format
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String, String> param : parameters.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(param.getKey());
	            postData.append('=');
	            postData.append(param.getValue());
	        }

	        //get a connection result
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());

	        //write data in request body
	        out.writeBytes(postData.toString());

	        //close output
	        out.flush();
	        out.close();

	        //get APi result
	        int responseCode = connection.getResponseCode();

	        //check if API result is ok (code 200 is successful)
	        if (responseCode == 200) {
	        	
	            //create reader to read API result
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();

	            //read line by line
	            while ((inputLine = reader.readLine()) != null) {
	                response.append(inputLine);
	            }

	            reader.close();
	            connection.disconnect();

	            //return result in String           
	            return response.toString();
	        } else {
	        	connection.disconnect();
	        	return "error:"+responseCode;
	        }	  	
		}catch(Exception e) {
			return "error:"+e;		
		}		
	}

	public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(20000);
        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
	
}
