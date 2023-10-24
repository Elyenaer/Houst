package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.StockBrokerageRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import setting.function.FunctionApi;

public class StockBrokerageConnect {
    private ManagerAccess ma;
    private final static String table = "stock_brokerage";

    public StockBrokerageConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    public ArrayList<StockBrokerageRegister> convertArray(String data) {
        ArrayList<StockBrokerageRegister> registers = new ArrayList<StockBrokerageRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private StockBrokerageRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public StockBrokerageRegister convert(JSONObject jsonObject) {
        StockBrokerageRegister register = new StockBrokerageRegister();
        register.setId(jsonObject.getInt("stock_brokerage_id"));
        register.setName(jsonObject.getString("name"));
        return register;
    }

    public ArrayList<StockBrokerageRegister> get() {
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

    public StockBrokerageRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "stock_brokerage_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            return get(id,0);
        }
    }
    
    private StockBrokerageRegister get(int id,int cont) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "stock_brokerage_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
        	if(cont<10) {
        		get(id, cont++);
        	}
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(StockBrokerageRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),

                    "name", register.getName(),
                    "stock_brokerage_id", String.valueOf(register.getId())
            );

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(StockBrokerageRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),

                    "name", register.getName()
            );
            String data = DatabaseConnect.start(table, parameters, "post");
            return FunctionApi.getId(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(StockBrokerageRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "stock_brokerage_id", String.valueOf(register.getId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
