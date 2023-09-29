package model.connect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.TitleRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;

public class TitleConnect {
	private final static String table = "title";
	
	public ArrayList<TitleRegister> get() {
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
	
	public TitleRegister get(int id) {
		try {
			Map<String, String> parameters = Map.of(
					"db_user", ManagerAccess.getUser(),
	                "db_pass", ManagerAccess.getPass(),
	                "title_id", id+""
	            );
	   	
			String data = DatabaseConnect.start(table,parameters,"get");	
			return convertRegister(data);
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"get",e);
			return null;
		}		
	}
	
	private ArrayList<TitleRegister> convertArray(String data) {
		ArrayList<TitleRegister> registers = new ArrayList<TitleRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }
	
	
	public boolean put(TitleRegister register) {
	    try {
	        Map<String, String> parameters = Map.of(
	                "db_user", ManagerAccess.getUser(),
	                "db_pass", ManagerAccess.getPass()
	            );

	        JSONObject jsonData = convertToJson(register);
	        String data = DatabaseConnect.start(table,parameters,"put");
	        return true;
	    } catch (Exception e) {
	        support.Message.Error(this.getClass().getName(), "put", e);
	        return false;
	    }
	}

	/*public boolean delete(int id) {
	    try {
	        Map<String, String> parameters = Map.of(
	                "db_user", ManagerAccess.getUser(),
	                "db_pass", ManagerAccess.getPass(),
	                "title_id", String.valueOf(id)
	            );

	        return DatabaseConnect.delete(table, parameters);
	    } catch (Exception e) {
	        support.Message.Error(this.getClass().getName(), "delete", e);
	        return false;
	    }
	}*/
	
	private TitleRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }
	
	public TitleRegister convert(JSONObject jsonObject) {
	    TitleRegister register = new TitleRegister();
	    register.setId(jsonObject.getInt("title_id"));
	    register.setBrokerageId(jsonObject.getInt("brokerage_id"));
	    register.setCustomerId(jsonObject.getInt("customer_id"));
	    register.setQ(jsonObject.getString("q").charAt(0));
	    register.setNegotiation(jsonObject.getString("negotiation"));
	    register.setNegotiationType(jsonObject.getString("negotiation_type").charAt(0));
	    register.setMarketType(jsonObject.getString("market_type"));
	    register.setDeadline(jsonObject.getString("deadline"));
	    register.setTitleName(jsonObject.getString("title_name"));
	    register.setQuantity(jsonObject.getInt("quantity"));
	    register.setPrice(new BigDecimal(jsonObject.getString("price")));
	    register.setPriceTotal(new BigDecimal(jsonObject.getString("price_total")));
	    register.setOperationType(jsonObject.getString("operation_type").charAt(0));
	    return register;
	}
	
	private JSONObject convertToJson(TitleRegister register) {
	    JSONObject json = new JSONObject();
	    json.put("title_id", register.getId());
	    json.put("brokerage_id", register.getBrokerageId());
	    json.put("customer_id", register.getCustomerId());
	    json.put("q", String.valueOf(register.getQ()));
	    json.put("negotiation", register.getNegotiation());
	    json.put("negotiation_type", String.valueOf(register.getNegotiationType()));
	    json.put("market_type", register.getMarketType());
	    json.put("deadline", register.getDeadline());
	    json.put("title_name", register.getTitleName());
	    json.put("quantity", register.getQuantity());
	    json.put("price", register.getPrice().toString());
	    json.put("price_total", register.getPriceTotal().toString());
	    json.put("operation_type", String.valueOf(register.getOperationType()));
	    return json;
	}

}
