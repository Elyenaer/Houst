package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.EntryMetricDoubleRegister;
import model.register.register.EntryMetricRegister;
import model.register.register.EntryMetricTextRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class EntryMetricConnect {
    private ManagerAccess ma;
    private final static String table = "entry_metric"; 

    public EntryMetricConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<EntryMetricRegister> convertArray(String data,char type) {
    	ArrayList<EntryMetricRegister> registers = new ArrayList<>();    
        JSONArray jsonArray = new JSONArray(data);
        if(type=='d') {
        	for (int i = 0; i < jsonArray.length(); i++) {
        		EntryMetricDoubleRegister register = new EntryMetricDoubleRegister();
                registers.add(convert(jsonArray.getJSONObject(i),register));
            }
        }else if(type=='t') {
        	for (int i = 0; i < jsonArray.length(); i++) {
        		EntryMetricTextRegister register = new EntryMetricTextRegister();
                registers.add(convert(jsonArray.getJSONObject(i),register));
            }
        }
        
        return registers;
    }

    private EntryMetricRegister convertRegister(String data, EntryMetricRegister register) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0),register);
    }

    public EntryMetricRegister convert(JSONObject jsonObject, EntryMetricRegister register) {
        register.setEntryMetricId(jsonObject.getInt("entry_metric_id"));
        register.setMetricId(jsonObject.getInt("metric_id"));
        register.setStockId(jsonObject.getInt("stock_id"));
        register.setDate(LocalDate.parse(jsonObject.getString("date")));

        if (register instanceof EntryMetricDoubleRegister) {
            ((EntryMetricDoubleRegister) register).setValue(jsonObject.getDouble("value"));
        } else if (register instanceof EntryMetricTextRegister) {
            ((EntryMetricTextRegister) register).setValue(jsonObject.getString("value"));
        }

        return register;
    }

    public ArrayList<EntryMetricRegister> get(char type) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass()
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertArray(data,type);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public EntryMetricRegister get(int id,char type) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "entry_metric_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            EntryMetricRegister register;
            if(type=='d') {
            	register = new EntryMetricDoubleRegister();
            }else {
            	register = new EntryMetricTextRegister();
            }
            return convertRegister(data,register);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(EntryMetricRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            
            parameters.put("metric_id", String.valueOf(register.getMetricId()));
            parameters.put("stock_id", String.valueOf(register.getStockId()));
            parameters.put("date", register.getDate().toString());            
            parameters.put("entry_metric_id", String.valueOf(register.getEntryMetricId()));
            
            if(register instanceof EntryMetricTextRegister) {
            	parameters.put("value", String.valueOf(((EntryMetricTextRegister) register).getValue()));
            }else if(register instanceof EntryMetricDoubleRegister) {
            	parameters.put("value", String.valueOf(((EntryMetricDoubleRegister) register).getValue()));
            }
            
            parameters.put("type", String.valueOf(register.getEntryMetricId()));

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(EntryMetricRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            
            parameters.put("metric_id", String.valueOf(register.getMetricId()));
            parameters.put("stock_id", String.valueOf(register.getStockId()));
            parameters.put("entry_metric_id", String.valueOf(register.getEntryMetricId()));
            
            if(register instanceof EntryMetricTextRegister) {
            	parameters.put("value", String.valueOf(((EntryMetricTextRegister) register).getValue()));
            }else if(register instanceof EntryMetricDoubleRegister) {
            	parameters.put("value", String.valueOf(((EntryMetricDoubleRegister) register).getValue()));
            }
            
            parameters.put("type", String.valueOf(register.getEntryMetricId()));
            
            String data = DatabaseConnect.start(table, parameters, "post");            
            return FunctionApi.getId(data);
        }catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(EntryMetricRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    
                    "entry_metric_id", String.valueOf(register.getEntryMetricId()),
                    "type", String.valueOf(getType(register))
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
    
    private char getType(EntryMetricRegister register) {
    	return register instanceof EntryMetricTextRegister ? 't' : register instanceof EntryMetricDoubleRegister ? 'd' : ' ';
    }
}
