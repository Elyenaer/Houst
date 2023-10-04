package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.MetricRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class MetricConnect {
    private ManagerAccess ma;
    private final static String table = "metric";

    public MetricConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<MetricRegister> convertArray(String data) {
        ArrayList<MetricRegister> registers = new ArrayList<MetricRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private MetricRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public MetricRegister convert(JSONObject jsonObject) {
        MetricRegister register = new MetricRegister();
        register.setMetricId(jsonObject.getInt("metric_id"));
        register.setTitleImport(jsonObject.getString("title_import"));
        register.setName(jsonObject.getString("name"));
        register.setDescription(jsonObject.getString("description"));
        register.setType(jsonObject.getString("type").charAt(0));
        register.setStatus(jsonObject.getString("status").charAt(0));
        return register;
    }

    public List<MetricRegister> get() {
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

    public MetricRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "metric_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(MetricRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("title_import", register.getTitleImport());
            parameters.put("name", register.getName());
            parameters.put("description", register.getDescription());
            parameters.put("type", String.valueOf(register.getType()));
            parameters.put("status", String.valueOf(register.getStatus()));
            parameters.put("metric_id", String.valueOf(register.getMetricId()));

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(MetricRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("title_import", register.getTitleImport());
            parameters.put("name", register.getName());
            parameters.put("description", register.getDescription());
            parameters.put("type", String.valueOf(register.getType()));
            parameters.put("status", String.valueOf(register.getStatus()));

            String data = DatabaseConnect.start(table, parameters, "post");
            return FunctionApi.getId(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(MetricRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "id", String.valueOf(register.getMetricId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
