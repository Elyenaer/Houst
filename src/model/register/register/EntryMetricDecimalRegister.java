package model.register.register;

import java.math.BigDecimal;

public class EntryMetricDecimalRegister extends EntryMetricRegister {
	private BigDecimal value;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
