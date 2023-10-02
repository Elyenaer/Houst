package components;

import javax.swing.JLabel;

import setting.desing.Design;

public class CustomLabel extends JLabel{
	private static final long serialVersionUID = 1L;

	public CustomLabel(String text,int fontSize,boolean bold) {
		setText(text);
		setFont(Design.getFont(fontSize,bold));
		setForeground(Design.componentsForeground);
	}
	
	public CustomLabel(String text) {
		setText(text);
		setFont(Design.getFont(10,true));
		setForeground(Design.componentsForeground);
	}

}
