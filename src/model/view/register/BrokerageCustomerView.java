package model.view.register;

public class BrokerageCustomerView {
	private int brokerageCustomerId;
	private String customerName;
	private String stockBrokerageName;
	private String code;
	private int customerId;
	private int stockBrokerageId;
	private String cpf;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getStockBrokerageId() {
		return stockBrokerageId;
	}
	public void setStockBrokerageId(int stockBrokerageId) {
		this.stockBrokerageId = stockBrokerageId;
	}
	public int getBrokerageCustomerId() {
		return brokerageCustomerId;
	}
	public void setBrokerageCustomerId(int brokerageCustomerId) {
		this.brokerageCustomerId = brokerageCustomerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStockBrokerageName() {
		return stockBrokerageName;
	}
	public void setStockBrokerageName(String stockBrokerageName) {
		this.stockBrokerageName = stockBrokerageName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
