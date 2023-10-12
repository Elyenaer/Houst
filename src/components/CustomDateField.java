package components;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import setting.desing.Design;
import support.FunctionDate;

public class CustomDateField extends JFormattedTextField {
	private static final long serialVersionUID = 1L;

	public CustomDateField() {
        init();
    }
	
	private void init(){
		setDateFormat("##/##/####");
		setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
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
	    String text = getText();
	    if (text == null || text.isEmpty()) {
	        return null; 
	    }
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return LocalDate.parse(text, dateFormatter);
	}
	
	public void setDate(String date) {
		setText(FunctionDate.databaseToString(date));
	}
}