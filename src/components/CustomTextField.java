package components;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import setting.Design;
import setting.support.TextMask;

public class CustomTextField extends JFormattedTextField{
	private static final long serialVersionUID = 1L;

	public CustomTextField() {
		setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
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
