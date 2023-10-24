package components;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import setting.desing.Design;
import setting.function.FunctionDate;

public class CustomDateField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;

	public CustomDateField() {
        init(12);
    }
	
	public CustomDateField(int fontSize) {
        init(fontSize);
    }
	
	private void init(int fontSize){
		setDateFormat("##/##/####");
		setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
		setFont(Design.getFont(fontSize,false));
	 }

    private void setDateFormat(String format) {
        try {
            MaskFormatter formatter = new MaskFormatter(format);
            formatter.install(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public void setCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        setText(dateFormat.format(currentDate));
    }
	
	public LocalDate getDate() {
		try {
			String text = getText();
		    if (text == null || text.isEmpty()) {
		        return null; 
		    }
		    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    return LocalDate.parse(text, dateFormatter);
		}catch (Exception e) {
			return null;
		}
	    
	}
	
	public void setDate(String date) {
		setText(FunctionDate.databaseToString(date));
	}
	
	public void setDate(LocalDate date) {
		try {
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String formattedDate = date.format(dateFormatter);

	        setText(formattedDate);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }		
	}
	
	public void clear() {
		setText("");
	}
}