package model.register.register;

import java.time.LocalDate;

public class EntryMetricRegister {
	private int entryMetricId;
	private int metricId;
	private int stockId;
	private LocalDate date;
	
	public int getEntryMetricId() {
		return entryMetricId;
	}
	public void setEntryMetricId(int entryMetricId) {
		this.entryMetricId = entryMetricId;
	}
	public int getMetricId() {
		return metricId;
	}
	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}	
}
