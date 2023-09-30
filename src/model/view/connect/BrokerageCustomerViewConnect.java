package model.view.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.view.register.BrokerageCustomerView;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class BrokerageCustomerViewConnect {
    private ManagerAccess ma;
    private final static String table = "brokerage_customer";

    public BrokerageCustomerViewConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<BrokerageCustomerView> convertArray(String data) {
        ArrayList<BrokerageCustomerView> records = new ArrayList<BrokerageCustomerView>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            records.add(convert(jsonArray.getJSONObject(i)));
        }
        return records;
    }

    private BrokerageCustomerView convertRecord(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public BrokerageCustomerView convert(JSONObject jsonObject) {    	
        BrokerageCustomerView record = new BrokerageCustomerView();
        record.setBrokerageCustomerId(jsonObject.getInt("brokerage_customer_id"));
        record.setCustomerName(jsonObject.getString("customer_name"));
        record.setStockBrokerageName(jsonObject.getString("stock_brokerage_name"));
        record.setCode(jsonObject.getString("code"));
        record.setCustomerId(jsonObject.getInt("customer_id"));
        record.setStockBrokerageId(jsonObject.getInt("stock_brokerage_id"));
        return record;
    }

    public ArrayList<BrokerageCustomerView> get() {
        try {
            Map<String, String> parameters = Map.of(
                "db_user", ma.getUser(),
                "db_pass", ma.getPass()
            );

            String data = DatabaseConnect.start(table, parameters, "getView");
            return convertArray(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public BrokerageCustomerView get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                "db_user", ma.getUser(),
                "db_pass", ma.getPass(),
                "brokerage_customer_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "getView");
            return convertRecord(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }
    
    public ArrayList<BrokerageCustomerView> getByCustomerId(int customerId) {
        try {
            Map<String, String> parameters = Map.of(
                "db_user", ma.getUser(),
                "db_pass", ma.getPass(),
                "customer_id", String.valueOf(customerId)
            );
            String data = DatabaseConnect.start(table, parameters, "getView");
            return convertArray(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "getByCustomerId", e);
            return null;
        }
    }

    public ArrayList<BrokerageCustomerView> getByStockBrokerage(int stockBrokerageId) {
        try {
            Map<String, String> parameters = Map.of(
                "db_user", ma.getUser(),
                "db_pass", ma.getPass(),
                "stock_brokerage_id", String.valueOf(stockBrokerageId)
            );

            String data = DatabaseConnect.start(table, parameters, "getView");
            return convertArray(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "getByStockBrokerage", e);
            return null;
        }
    }

    public boolean put(BrokerageCustomerView record) {
        try {
            Map<String, String> parameters = Map.of(
                "db_user", ma.getUser(),
                "db_pass", ma.getPass(),

                "customer_id", String.valueOf(record.getBrokerageCustomerId()),
                "stock_brokerage_id", String.valueOf(record.getStockBrokerageName()),
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

    public int post(BrokerageCustomerView record) {
        try {
            Map<String, String> parameters = Map.of(
                "db_user", ma.getUser(),
                "db_pass", ma.getPass(),

                "customer_id", String.valueOf(record.getBrokerageCustomerId()),
                "stock_brokerage_id", String.valueOf(record.getStockBrokerageName()),
                "code", record.getCode()
            );
            String data = DatabaseConnect.start(table, parameters, "post");
            return FunctionApi.getId(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(BrokerageCustomerView record) {
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
