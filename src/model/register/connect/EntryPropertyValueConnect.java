package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.EntryPropertyValueRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import setting.function.FunctionApi;

public class EntryPropertyValueConnect {
    private ManagerAccess ma;
    private final static String table = "entry_property_value"; 

    public EntryPropertyValueConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<EntryPropertyValueRegister> convertArray(String data) {
        ArrayList<EntryPropertyValueRegister> registers = new ArrayList<EntryPropertyValueRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private EntryPropertyValueRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public EntryPropertyValueRegister convert(JSONObject jsonObject) {
        EntryPropertyValueRegister register = new EntryPropertyValueRegister();
        register.setEntryPropertyValueId(jsonObject.getInt("entry_property_value_id"));
        register.setEntryPropertyId(jsonObject.getInt("entry_property_id"));
        register.setYear(jsonObject.getInt("year"));
        register.setValue(new BigDecimal(jsonObject.getString("value")));
        return register;
    }

    public ArrayList<EntryPropertyValueRegister> get() {
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

    public EntryPropertyValueRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "entry_property_value_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(EntryPropertyValueRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("entry_property_id", String.valueOf(register.getEntryPropertyId()));
            parameters.put("year", String.valueOf(register.getYear()));
            parameters.put("value", register.getValue().toString());
            parameters.put("entry_property_value_id", String.valueOf(register.getEntryPropertyValueId()));

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(EntryPropertyValueRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("entry_property_id", String.valueOf(register.getEntryPropertyId()));
            parameters.put("year", String.valueOf(register.getYear()));
            parameters.put("value", register.getValue().toString());

            String data = DatabaseConnect.start(table, parameters, "post");            
            return FunctionApi.getId(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(EntryPropertyValueRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "entry_property_value", String.valueOf(register.getEntryPropertyValueId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
