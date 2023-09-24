package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BrokerageReportRegister {
	//brokerage data
	private StockBrokerageRegister stockBrokerage;
	private CustomerRegister customer;
	private ArrayList<TitleRegister> stocks;
	private String invoiceNumber;
	private String tradingDate;
	//business briefing
	private BigDecimal debentures;
	private BigDecimal spotSales;
	private BigDecimal spotPurchases;
	private BigDecimal optionsPurchases;
	private BigDecimal optionsSales;
	private BigDecimal forwardOperation;
	private BigDecimal valueOfPublicSecuritiesOperation;
	private BigDecimal operationValue;
	//financial briefing
	private BigDecimal netValueOperation;
	private BigDecimal settlementFee;
	private BigDecimal registrationFee;
	private BigDecimal totalCBLC;
	private BigDecimal termOptionsFee;
	private BigDecimal anaFee;
	private BigDecimal emoluments;
	private BigDecimal totalBovespa;
	private BigDecimal clearing;
	private BigDecimal execution;
	private BigDecimal inHouseExecution;
	private BigDecimal iss;
	private BigDecimal irrfBase;
	private BigDecimal irrf;
	private BigDecimal issPisCofins;
	private BigDecimal totalBrokerageExpenses;
	private String netAmountForDate;
	private BigDecimal netAmountFor;
		
	public StockBrokerageRegister getStockBrokerage() {
		return stockBrokerage;
	}
	public void setStockBrokerage(StockBrokerageRegister stockBrokerage) {
		this.stockBrokerage = stockBrokerage;
	}
	public CustomerRegister getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerRegister customer) {
		this.customer = customer;
	}
	public ArrayList<TitleRegister> getStocks() {
		return stocks;
	}
	public void setStocks(ArrayList<TitleRegister> stocks) {
		this.stocks = stocks;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getTradingDate() {
		return tradingDate;
	}
	public void setTradingDate(String tradingDate) {
		this.tradingDate = tradingDate;
	}
	public BigDecimal getDebentures() {
		return debentures;
	}
	public void setDebentures(BigDecimal debentures) {
		this.debentures = debentures;
	}
	public BigDecimal getSpotSales() {
		return spotSales;
	}
	public void setSpotSales(BigDecimal spotSales) {
		this.spotSales = spotSales;
	}
	public BigDecimal getSpotPurchases() {
		return spotPurchases;
	}
	public void setSpotPurchases(BigDecimal spotPurchases) {
		this.spotPurchases = spotPurchases;
	}
	public BigDecimal getOptionsPurchases() {
		return optionsPurchases;
	}
	public void setOptionsPurchases(BigDecimal optionsPurchases) {
		this.optionsPurchases = optionsPurchases;
	}
	public BigDecimal getOptionsSales() {
		return optionsSales;
	}
	public void setOptionsSales(BigDecimal optionsSales) {
		this.optionsSales = optionsSales;
	}
	public BigDecimal getForwardOperation() {
		return forwardOperation;
	}
	public void setForwardOperation(BigDecimal forwardOperation) {
		this.forwardOperation = forwardOperation;
	}
	public BigDecimal getValueOfPublicSecuritiesOperation() {
		return valueOfPublicSecuritiesOperation;
	}
	public void setValueOfPublicSecuritiesOperation(BigDecimal valueOfPublicSecuritiesOperation) {
		this.valueOfPublicSecuritiesOperation = valueOfPublicSecuritiesOperation;
	}
	public BigDecimal getOperationValue() {
		return operationValue;
	}
	public void setOperationValue(BigDecimal operationValue) {
		this.operationValue = operationValue;
	}
	public BigDecimal getNetValueOperation() {
		return netValueOperation;
	}
	public void setNetValueOperation(BigDecimal netValueOperation) {
		this.netValueOperation = netValueOperation;
	}
	public BigDecimal getSettlementFee() {
		return settlementFee;
	}
	public void setSettlementFee(BigDecimal settlementFee) {
		this.settlementFee = settlementFee;
	}
	public BigDecimal getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(BigDecimal registrationFee) {
		this.registrationFee = registrationFee;
	}
	public BigDecimal getTotalCBLC() {
		return totalCBLC;
	}
	public void setTotalCBLC(BigDecimal totalCBLC) {
		this.totalCBLC = totalCBLC;
	}
	public BigDecimal getTermOptionsFee() {
		return termOptionsFee;
	}
	public void setTermOptionsFee(BigDecimal termOptionsFee) {
		this.termOptionsFee = termOptionsFee;
	}
	public BigDecimal getAnaFee() {
		return anaFee;
	}
	public void setAnaFee(BigDecimal anaFee) {
		this.anaFee = anaFee;
	}
	public BigDecimal getEmoluments() {
		return emoluments;
	}
	public void setEmoluments(BigDecimal emoluments) {
		this.emoluments = emoluments;
	}
	public BigDecimal getTotalBovespa() {
		return totalBovespa;
	}
	public void setTotalBovespa(BigDecimal totalBovespa) {
		this.totalBovespa = totalBovespa;
	}
	public BigDecimal getClearing() {
		return clearing;
	}
	public void setClearing(BigDecimal clearing) {
		this.clearing = clearing;
	}
	public BigDecimal getExecution() {
		return execution;
	}
	public void setExecution(BigDecimal execution) {
		this.execution = execution;
	}
	public BigDecimal getInHouseExecution() {
		return inHouseExecution;
	}
	public void setInHouseExecution(BigDecimal inHouseExecution) {
		this.inHouseExecution = inHouseExecution;
	}
	public BigDecimal getIss() {
		return iss;
	}
	public void setIss(BigDecimal iss) {
		this.iss = iss;
	}
	
	public BigDecimal getIrrfBase() {
		return irrfBase;
	}
	public void setIrrfBase(BigDecimal irrfBase) {
		this.irrfBase = irrfBase;
	}
	public BigDecimal getIrrf() {
		return irrf;
	}
	public void setIrrf(BigDecimal irrf) {
		this.irrf = irrf;
	}
	public BigDecimal getIssPisCofins() {
		return issPisCofins;
	}
	public void setIssPisCofins(BigDecimal issPisCofins) {
		this.issPisCofins = issPisCofins;
	}
	public BigDecimal getTotalBrokerageExpenses() {
		return totalBrokerageExpenses;
	}
	public void setTotalBrokerageExpenses(BigDecimal totalBrokerageExpenses) {
		this.totalBrokerageExpenses = totalBrokerageExpenses;
	}
	public String getNetAmountForDate() {
		return netAmountForDate;
	}
	public void setNetAmountForDate(String netAmountForDate) {
		this.netAmountForDate = netAmountForDate;
	}
	public BigDecimal getNetAmountFor() {
		return netAmountFor;
	}
	public void setNetAmountFor(BigDecimal netAmountFor) {
		this.netAmountFor = netAmountFor;
	}
	
	
	
}
