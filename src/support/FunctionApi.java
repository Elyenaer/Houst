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
	    try {
	        JSONObject jsonObject = new JSONObject(response);
	        if(jsonObject.getBoolean("success")){
	        	return true;
	        }else {
	        	return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
