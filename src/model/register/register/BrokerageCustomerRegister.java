package model.register.register;

public class BrokerageCustomerRegister {
	private int brokerageCustomerId;
	private int CustomerId;
	private int stockBrokerageId;
	private String code;
	
	public int getBrokerageCustomerId() {
		return brokerageCustomerId;
	}
	public void setBrokerageCustomerId(int brokerageCustomerId) {
		this.brokerageCustomerId = brokerageCustomerId;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}
	public int getStockBrokerageId() {
		return stockBrokerageId;
	}
	public void setStockBrokerageId(int stockBrokerageId) {
		this.stockBrokerageId = stockBrokerageId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
