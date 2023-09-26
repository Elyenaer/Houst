package support;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class FunctionCurrency {
	
	public static String bigDecimalToCurrencyBR(BigDecimal value) {
	    if (value == null) {
	        return "";
	    }
	    NumberFormat brCurrencyFormat = NumberFormat.getCurrencyInstance();
	    String formattedValue = brCurrencyFormat.format(value);
	    return formattedValue;
	}

}
