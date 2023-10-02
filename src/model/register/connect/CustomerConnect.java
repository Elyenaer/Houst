package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.CustomerRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class CustomerConnect { 
	private ManagerAccess ma;
    private final static String table = "customer";
    
    public CustomerConnect() throws FileNotFoundException, IOException {
    	ma = new ManagerAccess();
    }
    
    private ArrayList<CustomerRegister> convertArray(String data) {
        ArrayList<CustomerRegister> registers = new ArrayList<CustomerRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }
    
    private CustomerRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public CustomerRegister convert(JSONObject jsonObject) {
    	try {
    		CustomerRegister register = new CustomerRegister();
            register.setId(jsonObject.getInt("customer_id"));
            register.setCpf(jsonObject.getString("cpf"));
            register.setName(jsonObject.getString("name"));
            return register;
    	}catch (Exception e) {
    		return null;
    	}        
    }

    public ArrayList<CustomerRegister> get() {
        try {
            Map<String, String> parameters = Map.of(
            		"db_user", ma.getUser(),
                    "db_pass", ma.getPass()
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertArray(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public CustomerRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
            		"db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "customer_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }
    
    public CustomerRegister getByCpf(String cpf) {
        try {
            Map<String, String> parameters = Map.of(
            		"db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "cpf", String.valueOf(cpf)
            );

            String data = DatabaseConnect.start(table, parameters, "get");            
            if(data.equalsIgnoreCase("[]")) {
            	return null;
            }
            return convertRegister(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }
  
    public boolean put(CustomerRegister register) {
        try {        	
            Map<String, String> parameters = Map.of(
            		"db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    
                    "name", register.getName(),
                    "cpf", register.getCpf(),
                    "customer_id", String.valueOf(register.getId())
            );

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }
    
    public int post(CustomerRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    
                    "name", register.getName(),
                    "cpf", register.getCpf()    
            );
            String data = DatabaseConnect.start(table, parameters, "post");
            return FunctionApi.getId(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(CustomerRegister register) {
        try {
            Map<String, String> parameters = Map.of(
            		"db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "customer_id", String.valueOf(register.getId())
            );
            String data = DatabaseConnect.start(table,parameters,"delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }

}
