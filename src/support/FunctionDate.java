package support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionDate {
	
	public static String StringToDatabase(String dataEntrada) {
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");
            Date data = formatoEntrada.parse(dataEntrada);
            String dataFormatada = formatoSaida.format(data);
            return dataFormatada;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
