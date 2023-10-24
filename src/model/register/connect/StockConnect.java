package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.StockRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import setting.function.FunctionApi;

public class StockConnect {
    private ManagerAccess ma;
    private final static String table = "stock"; 

    public StockConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
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

    public StockRegister convert(JSONObject jsonObject) {
        StockRegister register = new StockRegister();
        register.setStockId(jsonObject.getInt("stock_id"));
        register.setSymbol(jsonObject.getString("symbol"));
        register.setStatus(jsonObject.getString("status").charAt(0));
        return register;
    }

    public ArrayList<StockRegister> get() {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass()
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertArray(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }
    
    public ArrayList<StockRegister> getActive() {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    
                    "status", "a"
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertArray(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public StockRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "stock_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(StockRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("symbol", register.getSymbol());
            parameters.put("status", String.valueOf(register.getStatus()));
            parameters.put("stock_id", String.valueOf(register.getStockId()));

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(StockRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("symbol", register.getSymbol());
            parameters.put("status", String.valueOf(register.getStatus()));
            
            String data = DatabaseConnect.start(table, parameters, "post");            
            return FunctionApi.getId(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(StockRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "stock_id", String.valueOf(register.getStockId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
