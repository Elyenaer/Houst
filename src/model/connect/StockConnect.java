package model.connect;

import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import model.register.StockRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;

public class StockConnect {
	private final static String table = "stock";
		
	public ArrayList<StockRegister> get() {
		try {
			Map<String, String> parameters = Map.of(
	                "db_user", ManagerAccess.getUser(),
	                "db_pass", ManagerAccess.getPass()
	            );
	   	
			String data = DatabaseConnect.start(table,parameters,"get");	
			return convertArray(data);
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"get",e);
			return null;
		}		
	}
	
	public StockRegister get(int id) {
		try {
			Map<String, String> parameters = Map.of(
					"db_user", ManagerAccess.getUser(),
	                "db_pass", ManagerAccess.getPass(),
	                "stock_id", id+""
	            );
	   	
			String data = DatabaseConnect.start(table,parameters,"get");	
			return convertRegister(data);
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"get",e);
			return null;
		}		
	}
	
	private ArrayList<StockRegister> convertArray(String data) {
		ArrayList<StockRegister> registers = new ArrayList<StockRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }
	
	private StockRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }
	
	private StockRegister convert(JSONObject jsonObject) {
		StockRegister register = new StockRegister();
		register.setStockId(jsonObject.getInt("stock_id"));
		register.setSymbol(jsonObject.getString("symbol"));
		register.setStatus(jsonObject.getString("status").charAt(0));
		return register;		
	}
	
}