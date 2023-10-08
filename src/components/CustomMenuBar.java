package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JMenuBar;

import setting.desing.Design;

public class CustomMenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	public CustomMenuBar() {
		init();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Design.componentsBackground2);
        g2d.fillRect(-100,-100, getWidth()+200, getHeight()+200);
        g2d.dispose();
    }
		
	private void init() {
		
	}
}
