package support;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FunctionBigDecimal {
	
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
	
	public static String bigDecimalToDatabase(BigDecimal value) {
		return value.toString().replace(".",",");
	}
	
	public static BigDecimal stringToBigDecimal(String text) {
		try {
			return new BigDecimal(text.replace("R$ ","").replace(".","").replace(",","."));
		}catch (Exception e) {
			return null;
		}		
	}

}
