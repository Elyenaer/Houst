package model.register.register;

import java.math.BigDecimal;

public class EntryPropertyValueRegister {
	private int entryPropertyValueId;
	private int entryPropertyId;
	private int year;
	private BigDecimal value;
	public int getEntryPropertyValueId() {
		return entryPropertyValueId;
	}
	public void setEntryPropertyValueId(int entryPropertyValueId) {
		this.entryPropertyValueId = entryPropertyValueId;
	}
	public int getEntryPropertyId() {
		return entryPropertyId;
	}
	public void setEntryPropertyId(int entryPropertyId) {
		this.entryPropertyId = entryPropertyId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
