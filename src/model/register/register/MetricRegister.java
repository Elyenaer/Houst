package model.register.register;

public class MetricRegister {
	private int metricId;
	private String titleImport;
	private String name;
	private String description;
	private char type; // t -> text / d -> double,decimal
	private char status;
	
	public int getMetricId() {
		return metricId;
	}
	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}
	public String getTitleImport() {
		return titleImport;
	}
	public void setTitleImport(String titleImport) {
		this.titleImport = titleImport;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
}
