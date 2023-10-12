package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.TitleRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;

public class TitleConnect {
    private ManagerAccess ma;
    private final static String table = "title"; 

    public TitleConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    public ArrayList<TitleRegister> convertArray(String data) {
        ArrayList<TitleRegister> registers = new ArrayList<TitleRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private TitleRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public TitleRegister convert(JSONObject jsonObject) {
        TitleRegister register = new TitleRegister();
        register.setTitleId(jsonObject.getInt("title_id"));
        register.setBrokerageReportId(jsonObject.getInt("brokerage_report_id"));
        register.setBrokerageCustomerId(jsonObject.getInt("brokerage_customer_id"));
        register.setQ(jsonObject.getString("q").charAt(0)); 
        register.setNegotiation(jsonObject.getString("negotiation"));
        register.setNegotiationType(jsonObject.getString("negotiation_type").charAt(0)); 
        register.setMarketType(jsonObject.getString("market_type"));
        register.setDeadline(jsonObject.getString("deadline"));
        register.setTitleName(jsonObject.getString("title_name"));
        register.setQuantity(jsonObject.getInt("quantity"));
        register.setPrice(new BigDecimal(jsonObject.getString("price")));
        register.setPriceTotal(new BigDecimal(jsonObject.getString("price_total")));
        register.setOperationType(jsonObject.getString("operation_type").charAt(0)); 
        return register;
    }

    public ArrayList<TitleRegister> get() {
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

    public TitleRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "title_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean put(TitleRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            parameters.put("brokerage_report_id", String.valueOf(register.getBrokerageReportId()));
            parameters.put("brokerage_customer_id", String.valueOf(register.getBrokerageCustomerId()));
            parameters.put("q", String.valueOf(register.getQ()));
            parameters.put("negotiation", register.getNegotiation());
            parameters.put("negotiation_type", String.valueOf(register.getNegotiationType()));
            parameters.put("market_type", register.getMarketType());
            parameters.put("deadline", register.getDeadline());
            parameters.put("title_name", register.getTitleName());
            parameters.put("quantity", String.valueOf(register.getQuantity()));
            parameters.put("price", register.getPrice().toString());
            parameters.put("price_total", register.getPriceTotal().toString());
            parameters.put("operation_type", String.valueOf(register.getOperationType()));
            parameters.put("title_id", String.valueOf(register.getTitleId()));

            String data = DatabaseConnect.start(table, parameters, "put");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(TitleRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            
            parameters.put("brokerage_report_id", String.valueOf(register.getBrokerageReportId()));
            parameters.put("brokerage_customer_id", String.valueOf(register.getBrokerageCustomerId()));
            parameters.put("q", String.valueOf(register.getQ()));
            parameters.put("negotiation", register.getNegotiation());
            parameters.put("negotiation_type", String.valueOf(register.getNegotiationType()));
            parameters.put("market_type", register.getMarketType());
            parameters.put("deadline", register.getDeadline());
            parameters.put("title_name", register.getTitleName());
            parameters.put("quantity", String.valueOf(register.getQuantity()));
            parameters.put("price", register.getPrice().toString());
            parameters.put("price_total", register.getPriceTotal().toString());
            parameters.put("operation_type", String.valueOf(register.getOperationType()));
            
            String data = DatabaseConnect.start(table, parameters, "post");            
            return FunctionApi.getId(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(TitleRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "title_id", String.valueOf(register.getTitleId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}

