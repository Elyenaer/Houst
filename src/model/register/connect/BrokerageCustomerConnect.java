package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.BrokerageCustomerRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class BrokerageCustomerConnect {
    private ManagerAccess ma;
    private final static String table = "brokerage_customer";

    public BrokerageCustomerConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<BrokerageCustomerRegister> convertArray(String data) {
        ArrayList<BrokerageCustomerRegister> records = new ArrayList<BrokerageCustomerRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            records.add(convert(jsonArray.getJSONObject(i)));
        }
        return records;
    }

    private BrokerageCustomerRegister convertRecord(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public BrokerageCustomerRegister convert(JSONObject jsonObject) {
        BrokerageCustomerRegister record = new BrokerageCustomerRegister();
        record.setBrokerageCustomerId(jsonObject.getInt("brokerage_customer_id"));
        record.setCustomerId(jsonObject.getInt("customer_id"));
        record.setStockBrokerageId(jsonObject.getInt("stock_brokerage_id"));
        record.setCode(jsonObject.getString("code"));
        return record;
    }

    public ArrayList<BrokerageCustomerRegister> get() {
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

    public BrokerageCustomerRegister getById(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_customer_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRecord(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "getById", e);
            return null;
        }
    }
    
    public ArrayList<BrokerageCustomerRegister> getByCustomerId(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "customer_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertArray(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "getByCustomerId", e);
            return null;
        }
    }
    
    public ArrayList<BrokerageCustomerRegister> getByStockBrokerage(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "stock_brokerage_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertArray(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "getByStockBrokerage", e);
            return null;
        }
    }
    
    public int checkRegister(BrokerageCustomerRegister register) {
    	try {    
    		Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());            
            parameters.put("stock_brokerage_id", String.valueOf(register.getStockBrokerageId()));            
            parameters.put("code", register.getCode());
            
            String data = DatabaseConnect.start(table, parameters, "get");
            
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                int brokerageCustomerId = jsonObject.getInt("brokerage_customer_id");
                return brokerageCustomerId;
            }
            
            return 0;
        } catch (Exception e) {
            return checkRegister(register,0);
        }
    }
    
    public int checkRegister(BrokerageCustomerRegister register,int cont) {
    	String data = "";
    	try {        	
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("stock_brokerage_id", String.valueOf(register.getStockBrokerageId()));
            parameters.put("code", register.getCode());
            data = DatabaseConnect.start(table, parameters, "get");
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                int brokerageCustomerId = jsonObject.getInt("brokerage_customer_id");
                return brokerageCustomerId;
            }
            
            return 0;
        } catch (Exception e) {
        	System.out.println("Connect Error -> " + data);
            if(cont<10) {
            	return checkRegister(register,cont++);
            }else {
            	support.Message.Error(this.getClass().getName(), "checkRegister", e);
            	return 0;
            }            
        }
    }

    public boolean put(BrokerageCustomerRegister record) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),

                    "customer_id", String.valueOf(record.getCustomerId()),
                    "stock_brokerage_id", String.valueOf(record.getStockBrokerageId()),
                    "code", record.getCode(),
                    "brokerage_customer_id", String.valueOf(record.getBrokerageCustomerId())
            );

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(BrokerageCustomerRegister record) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),

                    "customer_id", String.valueOf(record.getCustomerId()),
                    "stock_brokerage_id", String.valueOf(record.getStockBrokerageId()),
                    "code", record.getCode()
            );
            String data = DatabaseConnect.start(table, parameters, "post");
            return FunctionApi.getId(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(BrokerageCustomerRegister record) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_customer_id", String.valueOf(record.getBrokerageCustomerId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
