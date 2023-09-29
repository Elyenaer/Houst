package components;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import setting.support.TextMask;

public class CustomTextField extends JFormattedTextField{
	private static final long serialVersionUID = 1L;

	public CustomTextField() {
		
	}
	
	 public CustomTextField(TextMask mask) {
        if (mask == TextMask.cpf) {
            setMask("###.###.###-##");
        }
	 }

    private void setMask(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.install(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
