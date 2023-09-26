package components;

import javax.swing.Icon;
import javax.swing.JLabel;
import support.FunctionIcon;
import java.awt.Dimension;

public class CustomIconLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    final int width,height;

    public CustomIconLabel(Icon icon, int width, int height) {
    	this.width = width;
    	this.height = height;    	     
        setPreferredSize(new Dimension(width, height));
        if(icon!=null) {
            this.changeIcon(icon);
    	}   
    }
    
    public void changeIcon(Icon icon) {
    	Icon scaledIcon = FunctionIcon.scaleIcon(icon, width, height);
        setIcon(scaledIcon);
        repaint();
        revalidate();
    }

}