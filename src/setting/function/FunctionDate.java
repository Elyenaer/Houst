package setting.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FunctionDate {
	
	public static String standardBRToDatabase(String date) {
        try {
            SimpleDateFormat formatEntry = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = formatEntry.parse(date);
            String formatDate = formatOut.format(newDate);
            return formatDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static LocalDate standardBRToLocalDate(String date) {
        try {
            String[] parts = date.split("/");
            int year = Integer.parseInt(parts[2]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[0]);
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static LocalDate databaseToLocalDate(String date) {
        try {
            String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String databaseToString(String date) {
        try {
            SimpleDateFormat formatEntry = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");
            Date newDate = formatEntry.parse(date);
            String formatDate = formatOut.format(newDate);
            return formatDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static String localDateToDatabase(LocalDate date) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String dataFormatada = date.format(formatter);	    
	    return dataFormatada;
	}

}
