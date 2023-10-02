package model.view.register;

import java.util.ArrayList;

import model.register.register.BrokerageCustomerRegister;
import model.register.register.BrokerageReportRegister;
import model.register.register.CustomerRegister;
import model.register.register.StockBrokerageRegister;
import model.register.register.TitleRegister;

public class BrokerageReportView {
	private BrokerageReportRegister brokerageReportRegister;
	private BrokerageCustomerRegister brokerageCustomerRegister;
	private CustomerRegister customerRegister;
	private ArrayList<TitleRegister> titles;
	private StockBrokerageRegister stockBrokerageRegister;
	
	
	public StockBrokerageRegister getStockBrokerageRegister() {
		return stockBrokerageRegister;
	}
	public void setStockBrokerageRegister(StockBrokerageRegister stockBrokerageRegister) {
		this.stockBrokerageRegister = stockBrokerageRegister;
	}
	public CustomerRegister getCustomerRegister() {
		return customerRegister;
	}
	public void setCustomerRegister(CustomerRegister customerRegister) {
		this.customerRegister = customerRegister;
	}
	public BrokerageReportRegister getBrokerageReportRegister() {
		return brokerageReportRegister;
	}
	public void setBrokerageReportRegister(BrokerageReportRegister brokerageReportRegister) {
		this.brokerageReportRegister = brokerageReportRegister;
	}
	public BrokerageCustomerRegister getBrokerageCustomerRegister() {
		return brokerageCustomerRegister;
	}
	public void setBrokerageCustomerRegister(BrokerageCustomerRegister brokerageCustomerRegister) {
		this.brokerageCustomerRegister = brokerageCustomerRegister;
	}
	public ArrayList<TitleRegister> getTitles() {
		return titles;
	}
	public void setTitles(ArrayList<TitleRegister> titles) {
		this.titles = titles;
	}
	
}
