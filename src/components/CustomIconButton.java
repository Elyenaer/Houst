package components;

import javax.swing.Icon;
import javax.swing.JButton;

import setting.desing.Design;
import support.FunctionIcon;

public class CustomIconButton extends JButton {
    private static final long serialVersionUID = 1L;
    private int width,height;

    public CustomIconButton(Icon icon,int width,int height) {
    	init(icon,width,height,null);
    }
    
    public CustomIconButton(Icon icon,int width,int height,String toolTip) {
    	init(icon,width,height,toolTip);
    }
    
    private void init(Icon icon,int width,int height,String toolTip){
    	this.width = width;
    	this.height = height;
    	setIcon(FunctionIcon.scaleIcon(icon,(int)(width*0.5),(int)(height*0.5)));   	
        setBackground(Design.componentsBackground);
        if(toolTip!=null) {
        	setToolTipText(toolTip);
        }        
    }
       
    public void setScaleIcon(Icon icon) {
    	setIcon(FunctionIcon.scaleIcon(icon,(int)(width*0.5),(int)(height*0.5)));   	
    }
}


