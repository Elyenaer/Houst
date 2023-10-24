package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.EntryPropertyRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import setting.function.FunctionApi;

public class EntryPropertyConnect {
    private ManagerAccess ma;
    private final static String table = "entry_property";

    public EntryPropertyConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<EntryPropertyRegister> convertArray(String data) {
        ArrayList<EntryPropertyRegister> registers = new ArrayList<EntryPropertyRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private EntryPropertyRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public EntryPropertyRegister convert(JSONObject jsonObject) {
        EntryPropertyRegister register = new EntryPropertyRegister();
        register.setEntryPropertyId(jsonObject.getInt("entry_property_id"));
        register.setCustomerId(jsonObject.getInt("customer_id"));
        register.setPropertyId(jsonObject.getInt("property_id"));
        register.setName(jsonObject.getString("name"));
        register.setDescription(jsonObject.getString("description"));
        register.setBuyDate(LocalDate.parse(jsonObject.getString("buy_date")));
        register.setSellDate(LocalDate.parse(jsonObject.getString("sell_date")));
        return register;
    }

    public ArrayList<EntryPropertyRegister> get() {
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

    public EntryPropertyRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "entry_property_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(EntryPropertyRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("customer_id", String.valueOf(register.getCustomerId()));
            parameters.put("property_id", String.valueOf(register.getPropertyId()));
            parameters.put("name",register.getName());
            parameters.put("description",register.getDescription());
            parameters.put("buy_date", register.getBuyDate().toString());
            parameters.put("sell_date", register.getSellDate() != null ? register.getSellDate().toString() : "");
            parameters.put("entry_property_id", String.valueOf(register.getEntryPropertyId()));

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(EntryPropertyRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("customer_id", String.valueOf(register.getCustomerId()));
            parameters.put("property_id", String.valueOf(register.getPropertyId()));
            parameters.put("name",register.getName());
            parameters.put("description",register.getDescription());
            parameters.put("buy_date", register.getBuyDate().toString());
            parameters.put("sell_date", register.getSellDate() != null ? register.getSellDate().toString() : "");

            String data = DatabaseConnect.start(table, parameters, "post");
            return FunctionApi.getId(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(EntryPropertyRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "entry_property_id", String.valueOf(register.getEntryPropertyId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
