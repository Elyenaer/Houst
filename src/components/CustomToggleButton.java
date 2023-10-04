package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JToggleButton;

import setting.desing.Design;

public class CustomToggleButton extends JToggleButton {
    private static final long serialVersionUID = 1L;
    private Color onColor = new Color(50, 170, 50);
    private Color offColor = new Color(170, 70, 70);
    private String onText = "ON";
    private String offText = "OFF";

    public CustomToggleButton() {
        init();
    }
    
    public CustomToggleButton(String onText, String offText) {
        init();
        setText(onText, offText);
    }
    
    private void init() {
    	setPreferredSize(new Dimension(60, 30));
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected()) {
            g2d.setColor(onColor);
        } else {
            g2d.setColor(offColor);
        }        
        int arc = 15;
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        g2d.setFont(Design.getFont(10,true));
        g2d.setColor(Design.componentsForeground);
        int stringWidth = g2d.getFontMetrics().stringWidth(isSelected() ? onText : offText);
        int x = (getWidth() - stringWidth) / 2;
        int y = (getHeight() - g2d.getFontMetrics().getHeight()) / 2 + g2d.getFontMetrics().getAscent();
        g2d.drawString(isSelected() ? onText : offText, x, y);        
        g2d.dispose();
    }

    public void setText(String onText, String offText) {
        this.onText = onText;
        this.offText = offText;
    }
   
}