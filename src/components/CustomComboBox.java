package components;

import javax.swing.JComboBox;

import setting.desing.Design;

public class CustomComboBox extends JComboBox<String>{
	private static final long serialVersionUID = 1L;
	
	public CustomComboBox() {
		setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
	}
	

}
