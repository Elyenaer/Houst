package support;

import org.json.JSONObject;

public class FunctionApi {

	public static int getId(String response) {
	    try {
	        JSONObject jsonObject = new JSONObject(response);
	        return jsonObject.getInt("id");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}
	
	public static boolean getSuccess(String response) {
		JSONObject jsonObject = new JSONObject(response);
	    try {	        
	        if(jsonObject.getBoolean("success")){
	        	return true;
	        }else {
	        	return false;
	        }
	    } catch (Exception e) {
	    	try {
	    		if(jsonObject.getString("success").equalsIgnoreCase("true")){
		        	return true;
		        }else {
		        	return false;
		        }
	    	}catch (Exception e1) {
	    		Message.Error("support","FunctionApi","getSucces","response: " + jsonObject + "\nErro: " + e1.getMessage());
		        return false;
			}	        
	    }
	}
}
