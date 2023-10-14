package model.register.connect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import model.register.register.BrokerageReportRegister;
import setting.DatabaseConnect;
import setting.ManagerAccess;
import support.FunctionApi;
import support.FunctionDate;

public class BrokerageReportConnect {
    private ManagerAccess ma;
    private final static String table = "brokerage_report"; 

    public BrokerageReportConnect() throws FileNotFoundException, IOException {
        ma = new ManagerAccess();
    }

    public ArrayList<BrokerageReportRegister> convertArray(String data) {
        ArrayList<BrokerageReportRegister> registers = new ArrayList<BrokerageReportRegister>();
        JSONArray jsonArray = new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            registers.add(convert(jsonArray.getJSONObject(i)));
        }
        return registers;
    }

    private BrokerageReportRegister convertRegister(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return convert(jsonArray.getJSONObject(0));
    }

    public BrokerageReportRegister convert(JSONObject jsonObject) {
        BrokerageReportRegister register = new BrokerageReportRegister();
        register.setBrokerageReportId(jsonObject.getInt("brokerage_report_id"));
        register.setStockBrokerageId(jsonObject.getInt("stock_brokerage_id"));
        register.setBrokerageCustomerId(jsonObject.getInt("brokerage_customer_id"));
        register.setInvoiceNumber(jsonObject.getString("invoice_number"));
        register.setTradingDate(FunctionDate.databaseToLocalDate(jsonObject.getString("trading_date")));
        register.setDebentures(new BigDecimal(jsonObject.getString("debentures")));
        register.setSpotSales(new BigDecimal(jsonObject.getString("spot_sales")));
        register.setSpotPurchases(new BigDecimal(jsonObject.getString("spot_purchases")));
        register.setOptionsPurchases(new BigDecimal(jsonObject.getString("options_purchases")));
        register.setOptionsSales(new BigDecimal(jsonObject.getString("options_sales")));
        register.setForwardOperation(new BigDecimal(jsonObject.getString("forward_operation")));
        register.setValueOfPublicSecuritiesOperation(new BigDecimal(jsonObject.getString("value_of_public_securities_operation")));
        register.setOperationValue(new BigDecimal(jsonObject.getString("operation_value")));
        register.setNetValueOperation(new BigDecimal(jsonObject.getString("net_value_operation")));
        register.setSettlementFee(new BigDecimal(jsonObject.getString("settlement_fee")));
        register.setRegistrationFee(new BigDecimal(jsonObject.getString("registration_fee")));
        register.setTotalCBLC(new BigDecimal(jsonObject.getString("total_cblc")));
        register.setTermOptionsFee(new BigDecimal(jsonObject.getString("term_options_fee")));
        register.setAnaFee(new BigDecimal(jsonObject.getString("ana_fee")));
        register.setEmoluments(new BigDecimal(jsonObject.getString("emoluments")));
        register.setTotalBovespa(new BigDecimal(jsonObject.getString("total_bovespa")));
        register.setClearing(new BigDecimal(jsonObject.getString("clearing")));
        register.setExecution(new BigDecimal(jsonObject.getString("execution")));
        register.setInHouseExecution(new BigDecimal(jsonObject.getString("in_house_execution")));
        register.setIss(new BigDecimal(jsonObject.getString("iss")));
        register.setIrrfBase(new BigDecimal(jsonObject.getString("irrf_base")));
        register.setIrrf(new BigDecimal(jsonObject.getString("irrf")));
        register.setIssPisCofins(new BigDecimal(jsonObject.getString("iss_pis_cofins")));
        register.setTotalBrokerageExpenses(new BigDecimal(jsonObject.getString("total_brokerage_expenses")));
        register.setNetAmountForDate(FunctionDate.databaseToLocalDate(jsonObject.getString("net_amount_for_date")));
        register.setNetAmountFor(new BigDecimal(jsonObject.getString("net_amount_for")));
        return register;
    }

    public List<BrokerageReportRegister> get() {
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

    public BrokerageReportRegister get(int id) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_report_id", String.valueOf(id)
            );

            String data = DatabaseConnect.start(table, parameters, "get");
            return convertRegister(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "get", e);
            return null;
        }
    }

    public boolean checkRegister(BrokerageReportRegister register) {
    	try {  	
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());            
            parameters.put("stock_brokerage_id", String.valueOf(register.getStockBrokerageId()));            
            parameters.put("invoice_number", register.getInvoiceNumber());
            
            String data = DatabaseConnect.start(table, parameters, "get");
                       
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.length() > 0) {
                return true;
            }
            
            return false;
        } catch (Exception e) {
            return checkRegister(register,0);
        }
    }
    
    public boolean checkRegister(BrokerageReportRegister register,int cont) {
    	String data = "";
    	try {        	
    		Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());            
            parameters.put("stock_brokerage_id", String.valueOf(register.getStockBrokerageId()));            
            parameters.put("invoice_number", register.getInvoiceNumber());
            
            data = DatabaseConnect.start(table, parameters, "get");
            
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.length() > 0) {
                return true;
            }
            
            return false;
        } catch (Exception e) {
        	System.out.println("Connect Error -> " + data);
            if(cont<10) {
            	return checkRegister(register,cont++);
            }else {
            	support.Message.Error(this.getClass().getName(), "checkRegister", e);
            	return false;
            }            
        }
    }
    
    public boolean put(BrokerageReportRegister register) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            
            parameters.put("brokerage_report_id", String.valueOf(register.getBrokerageReportId()));
            parameters.put("stock_brokerage_id", String.valueOf(register.getStockBrokerageId()));
            parameters.put("brokerage_customer_id", String.valueOf(register.getBrokerageCustomerId()));
            parameters.put("invoice_number", register.getInvoiceNumber());
            parameters.put("trading_date", FunctionDate.localDateToDatabase(register.getTradingDate()));
            parameters.put("debentures", register.getDebentures().toString());
            parameters.put("spot_sales", register.getSpotSales().toString());
            parameters.put("spot_purchases", register.getSpotPurchases().toString());
            parameters.put("options_purchases", register.getOptionsPurchases().toString());
            parameters.put("options_sales", register.getOptionsSales().toString());
            parameters.put("forward_operation", register.getForwardOperation().toString());
            parameters.put("value_of_public_securities_operation", register.getValueOfPublicSecuritiesOperation().toString());
            parameters.put("operation_value", register.getOperationValue().toString());
            parameters.put("net_value_operation", register.getNetValueOperation().toString());
            parameters.put("settlement_fee", register.getSettlementFee().toString());
            parameters.put("registration_fee", register.getRegistrationFee().toString());
            parameters.put("total_cblc", register.getTotalCBLC().toString());
            parameters.put("term_options_fee", register.getTermOptionsFee().toString());
            parameters.put("ana_fee", register.getAnaFee().toString());
            parameters.put("emoluments", register.getEmoluments().toString());
            parameters.put("total_bovespa", register.getTotalBovespa().toString());
            parameters.put("clearing", register.getClearing().toString());
            parameters.put("execution", register.getExecution().toString());
            parameters.put("in_house_execution", register.getInHouseExecution().toString());
            parameters.put("iss", register.getIss().toString());
            parameters.put("irrf_base", register.getIrrfBase().toString());
            parameters.put("irrf", register.getIrrf().toString());
            parameters.put("iss_pis_cofins", register.getIssPisCofins().toString());
            parameters.put("total_brokerage_expenses", register.getTotalBrokerageExpenses().toString());
            parameters.put("net_amount_for_date", FunctionDate.localDateToDatabase(register.getNetAmountForDate()));
            parameters.put("net_amount_for", register.getNetAmountFor().toString());

            String data = DatabaseConnect.start(table, parameters, "put");
            
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "put", e);
            return false;
        }
    }

    public int post(BrokerageReportRegister register) {
    	try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("db_user", ma.getUser());
            parameters.put("db_pass", ma.getPass());
            
            parameters.put("stock_brokerage_id", String.valueOf(register.getStockBrokerageId()));
            parameters.put("brokerage_customer_id", String.valueOf(register.getBrokerageCustomerId()));
            parameters.put("invoice_number", register.getInvoiceNumber());
            parameters.put("trading_date", FunctionDate.localDateToDatabase(register.getTradingDate()));
            parameters.put("debentures", register.getDebentures().toString());
            parameters.put("spot_sales", register.getSpotSales().toString());
            parameters.put("spot_purchases", register.getSpotPurchases().toString());
            parameters.put("options_purchases", register.getOptionsPurchases().toString());
            parameters.put("options_sales", register.getOptionsSales().toString());
            parameters.put("forward_operation", register.getForwardOperation().toString());
            parameters.put("value_of_public_securities_operation", register.getValueOfPublicSecuritiesOperation().toString());
            parameters.put("operation_value", register.getOperationValue().toString());
            parameters.put("net_value_operation", register.getNetValueOperation().toString());
            parameters.put("settlement_fee", register.getSettlementFee().toString());
            parameters.put("registration_fee", register.getRegistrationFee().toString());
            parameters.put("total_cblc", register.getTotalCBLC().toString());
            parameters.put("term_options_fee", register.getTermOptionsFee().toString());
            parameters.put("ana_fee", register.getAnaFee().toString());
            parameters.put("emoluments", register.getEmoluments().toString());
            parameters.put("total_bovespa", register.getTotalBovespa().toString());
            parameters.put("clearing", register.getClearing().toString());
            parameters.put("execution", register.getExecution().toString());
            parameters.put("in_house_execution", register.getInHouseExecution().toString());
            parameters.put("iss", register.getIss().toString());
            parameters.put("irrf_base", register.getIrrfBase().toString());
            parameters.put("irrf", register.getIrrf().toString());
            parameters.put("iss_pis_cofins", register.getIssPisCofins().toString());
            parameters.put("total_brokerage_expenses", register.getTotalBrokerageExpenses().toString());
            parameters.put("net_amount_for_date", FunctionDate.localDateToDatabase(register.getNetAmountForDate()));
            parameters.put("net_amount_for", register.getNetAmountFor().toString());
            
            String data = DatabaseConnect.start(table, parameters, "post");
                        
            return FunctionApi.getId(data);
       	} catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "post", e);
            return 0;
        }
    }

    public boolean delete(BrokerageReportRegister register) {
        try {
            Map<String, String> parameters = Map.of(
                    "db_user", ma.getUser(),
                    "db_pass", ma.getPass(),
                    "brokerage_report_id", String.valueOf(register.getBrokerageReportId())
            );
            String data = DatabaseConnect.start(table, parameters, "delete");
            
            return FunctionApi.getSuccess(data);
        } catch (Exception e) {
            support.Message.Error(this.getClass().getName(), "delete", e);
            return false;
        }
    }
}
