package model;

import java.math.BigDecimal;

public class TitleRegister {
	private String negotiation;
	private char negotiationType;
	private String marketType;
	private String titleName;
	private int quantity;
	private BigDecimal price;
	private BigDecimal priceTotal;
	private char operationType;
	
	public String getNegotiation() {
		return negotiation;
	}
	public void setNegotiation(String negotiation) {
		this.negotiation = negotiation;
	}
	public char getNegotiationType() {
		return negotiationType;
	}
	public void setNegotiationType(char negotiationType) {
		this.negotiationType = negotiationType;
	}
	public String getMarketType() {
		return marketType;
	}
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
	}
	public char getOperationType() {
		return operationType;
	}
	public void setOperationType(char operationType) {
		this.operationType = operationType;
	}
	
}
