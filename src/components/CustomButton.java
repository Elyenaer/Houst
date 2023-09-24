package components;

import javax.swing.JButton;

import setting.Design;

public class CustomButton extends JButton{
	private static final long serialVersionUID = 1L;

	public CustomButton(String text) {
		setText(text);
		setBackground(Design.mainBackground);
		setForeground(Design.mainForeground);
		setFont(Design.getFont(12,true));
	}

}
