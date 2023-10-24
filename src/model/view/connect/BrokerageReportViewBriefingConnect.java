package model.view.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.connect.BrokerageCustomerConnect;
import model.register.connect.BrokerageReportConnect;
import model.register.connect.CustomerConnect;
import model.register.connect.StockBrokerageConnect;
import model.register.connect.TitleConnect;
import model.view.register.BrokerageReportView;
import model.view.register.BrokerageReportViewBriefing;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import setting.function.FunctionDate;

public class BrokerageReportViewBriefingConnect {
	private ManagerAccess ma;
    private final static String table = "brokerage_report"; 

    public BrokerageReportViewBriefingConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    private ArrayList<BrokerageReportViewBriefing> convertArray(String data) {
        ArrayList<BrokerageReportViewBriefing> registers = new ArrayList<BrokerageReportViewBriefing>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private BrokerageReportViewBriefing convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public BrokerageReportViewBriefing convert(JSONObject jsonObject) {
    	BrokerageReportViewBriefing register = new BrokerageReportViewBriefing();
        register.setInvoiceNumber(jsonObject.getString("invoice_number"));
        register.setCustomerName(jsonObject.getString("customer_name"));
        register.setId(jsonObject.getInt("brokerage_report_id"));
        register.setStockBrokerageName(jsonObject.getString("stock_brokerage_name"));
        register.setDate(FunctionDate.databaseToLocalDate(jsonObject.getString("trading_date")));
        register.setCustomerId(jsonObject.getInt("customer_id"));
        register.setStockBrokerageId(jsonObject.getInt("stock_brokerage_id"));
        register.setCustomerCode(jsonObject.getString("brokerage_customer_code"));
        return register;
    }

    public ArrayList<BrokerageReportViewBriefing> get() {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass()
            );

            String data = DatabaseConnect.start(table, parameters, "getView");
                        
            return convertArray(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public BrokerageReportViewBriefing get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_report_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }
    
    public BrokerageReportView getFullView(BrokerageReportViewBriefing register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_report_id", String.valueOf(register.getId()),
                    "code", String.valueOf(register.getCustomerCode()),
                    "customer_id", String.valueOf(register.getCustomerId()),
                    "stock_brokerage_id", String.valueOf(register.getStockBrokerageId())                    
            );

            String data = DatabaseConnect.start(table, parameters, "getView");
            JSONObject jsonObject = new JSONObject(data); 
            
            BrokerageReportView view = new BrokerageReportView();          
            view.setBrokerageReportRegister(new BrokerageReportConnect().convertArray(jsonObject.getJSONArray("brokerage_report").toString()).get(0));
            view.setBrokerageCustomerRegister(new BrokerageCustomerConnect().convertArray(jsonObject.getJSONArray("brokerage_customer").toString()).get(0));
            view.setCustomerRegister(new CustomerConnect().convertArray(jsonObject.getJSONArray("customer").toString()).get(0));
            view.setTitles(new TitleConnect().convertArray(jsonObject.getJSONArray("title").toString()));
            view.setStockBrokerageRegister(new StockBrokerageConnect().convertArray(jsonObject.getJSONArray("stock_brokerage").toString()).get(0));
            
            return view;
        } catch (Exception e) {
            setting.function.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

}
