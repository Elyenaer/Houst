package components;

import java.awt.Dimension;

import javax.swing.JMenuItem;

import setting.desing.Design;

public class CustomMenuItem extends JMenuItem{
	private static final long serialVersionUID = 1L;
	private int height = 25, width = 180;
	
	public CustomMenuItem(String title) {
		init(title);
	}
	
	public CustomMenuItem(String title,int width,int height) {
		this.height = height;
		this.width = width;
		init(title);
	}
	
	private void init(String title) {
		this.setText(title);
		this.setOpaque(true);
		this.setBackground(Design.componentsBackground2);
		this.setForeground(Design.componentsForeground2);
		this.setPreferredSize(new Dimension(width, height));
	}
}
