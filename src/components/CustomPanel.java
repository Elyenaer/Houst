package components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import setting.desing.Design;
import support.FunctionText;

public class CustomPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public CustomPanel(int width,int height) {
		this.setSize(width, height);
		init();
	}
		
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Design.mainBackground);
        g2d.fillRect(-100,-100, getWidth()+200, getHeight()+200);
        g2d.setColor(Design.mainForeground);
        g2d.drawRect(0,10,getWidth(), getHeight() - 11);
        g2d.dispose();
    }
	
	private void init() {
	
	}
	
	public void setTitle(String title){
        int width = FunctionText.getStringWidth(title,Design.getFont(10,true))+10;
        JLabel t = new CustomLabel(title,10,true);
        t.setOpaque(true);
        t.setBackground(Design.mainBackground);
        t.setHorizontalAlignment(JLabel.CENTER);
        this.add(t);
        t.setBounds(getWidth()/2-width/2,0,width,19);
	}
	
}
