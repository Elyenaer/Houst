package components;

import javax.swing.JButton;

import setting.desing.Design;

public class CustomButton extends JButton{
	private static final long serialVersionUID = 1L;

	public CustomButton(String text) {
		init(text,12);
	}
	
	public CustomButton(String text,int fontSize) {
		init(text,fontSize);
	}
	
	private void init(String text,int fontSize) {
		setText(text);
		setBackground(Design.mainBackground);
		setForeground(Design.mainForeground);
		setFont(Design.getFont(fontSize,true));
	}

}
