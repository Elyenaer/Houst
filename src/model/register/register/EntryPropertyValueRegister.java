package model.register.register;

import java.math.BigDecimal;
import java.util.ArrayList;

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
	
	public static int findMinYear(ArrayList<EntryPropertyValueRegister> list) {
        if (list.isEmpty()) {
            return 0;
        }

        int minYear = list.get(0).getYear();
        
        for (EntryPropertyValueRegister entry : list) {
            if (entry.getYear() < minYear) {
                minYear = entry.getYear();
            }
        }

        return minYear;
    }
	
}
