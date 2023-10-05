package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.EntryMetricRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class EntryMetricConnect {
    private ManagerAccess ma;
    private final static String table = "entry_metric"; 

    public EntryMetricConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<EntryMetricRegister> convertArray(String data) {
        ArrayList<EntryMetricRegister> registers = new ArrayList<EntryMetricRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private EntryMetricRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public EntryMetricRegister convert(JSONObject jsonObject) {
        EntryMetricRegister register = new EntryMetricRegister();
        register.setEntryMetricId(jsonObject.getInt("entry_metric_id"));
        register.setMetricId(jsonObject.getInt("metric_id"));
        register.setStockId(jsonObject.getInt("stock_id"));
        // Aqui você precisa adaptar para a data e o valor apropriados
        // Exemplo:
        // register.setDate(LocalDate.parse(jsonObject.getString("date")));
        // register.setValue(jsonObject.getDouble("value"));
        return register;
    }

    public ArrayList<EntryMetricRegister> get() {
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

    public EntryMetricRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "entry_metric_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
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
            // Aqui você precisa adaptar para a data e o valor apropriados
            // Exemplo:
            // parameters.put("date", register.getDate().toString());
            // parameters.put("value", String.valueOf(register.getValue()));
            parameters.put("entry_metric_id", String.valueOf(register.getEntryMetricId()));

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
            // Aqui você precisa adaptar para a data e o valor apropriados
            // Exemplo:
            // parameters.put("date", register.getDate().toString());
            // parameters.put("value", String.valueOf(register.getValue()));

            String data = DatabaseConnect.start(table, parameters, "post");            
            return FunctionApi.getId(data);
        catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(EntryMetricRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "id", String.valueOf(register.getEntryMetricId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
