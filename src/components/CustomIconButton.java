package components;

import javax.swing.Icon;
import javax.swing.JButton;

import setting.Design;
import support.FunctionIcon;

public class CustomIconButton extends JButton {
    private static final long serialVersionUID = 1L;

    public CustomIconButton(Icon icon,int width,int height) {
        setIcon(FunctionIcon.scaleIcon(icon,(int)(width*0.5),(int)(height*0.5)));
        setBackground(Design.componentsBackground);
    }
    
    public CustomIconButton(Icon icon,int width,int height,String toolTip) {
        setIcon(FunctionIcon.scaleIcon(icon,(int)(width*0.5),(int)(height*0.5)));
        setBackground(Design.componentsBackground);
        setToolTipText(toolTip);
    }
}


