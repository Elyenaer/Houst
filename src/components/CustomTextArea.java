package components;

import javax.swing.JTextArea;

import setting.desing.Design;

public class CustomTextArea extends JTextArea {
	private static final long serialVersionUID = 1L;
	
	public CustomTextArea() {
		init();
	}
	
	private void init() {
		setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
	}

}
