package model.view.register;

import java.time.LocalDate;

public class BrokerageReportViewBriefing {
	private int id;
	private String stockBrokerageName;
	private int stockBrokerageId;
	private String customerName;
	private int customerId;
	private String invoiceNumber;
	private LocalDate date;
	private String customerCode;
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockBrokerageName() {
		return stockBrokerageName;
	}
	public void setStockBrokerageName(String stockBrokerageName) {
		this.stockBrokerageName = stockBrokerageName;
	}
	public int getStockBrokerageId() {
		return stockBrokerageId;
	}
	public void setStockBrokerageId(int stockBrokerageId) {
		this.stockBrokerageId = stockBrokerageId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	

}
