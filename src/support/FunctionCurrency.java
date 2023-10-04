package support;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
	
	public static String bigDecimalToThousands(BigDecimal value) {
	    if (value == null) {
	        return "";
	    }
	    
	    DecimalFormat format = new DecimalFormat("#,###");
	    return format.format(value);
	}
	
	public static String bigDecimalToThousands(int value) {
	    DecimalFormat format = new DecimalFormat("#,###");
	    return format.format(value);
	}

}
