package model.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.BrokerageCustomerRegister;
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

    public BrokerageCustomerRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_customer_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRecord(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
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
