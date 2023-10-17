package model.register.register;

import java.time.LocalDate;

public class EntryPropertyRegister {
	private int entryPropertyId;
	private int customerId;
	private int propertyId;
	private LocalDate buyDate;
	private LocalDate sellDate;
	public int getEntryPropertyId() {
		return entryPropertyId;
	}
	public void setEntryPropertyId(int entryPropertyId) {
		this.entryPropertyId = entryPropertyId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public LocalDate getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(LocalDate buyDate) {
		this.buyDate = buyDate;
	}
	public LocalDate getSellDate() {
		return sellDate;
	}
	public void setSellDate(LocalDate sellDate) {
		this.sellDate = sellDate;
	}
	
}
