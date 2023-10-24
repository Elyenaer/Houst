package setting.function;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FunctionIcon {
	
    public static Icon scaleIcon(Icon icon, int width, int height) {        
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();

        int newWidth = originalWidth;
        int newHeight = originalHeight;

        if (originalWidth > width || originalHeight > height) {
            double widthRatio = (double) width / originalWidth;
            double heightRatio = (double) height / originalHeight;
            
            double ratio = Math.min(widthRatio, heightRatio);
            
            newWidth = (int) (originalWidth * ratio);
            newHeight = (int) (originalHeight * ratio);
        }

        Image scaledImage = ((ImageIcon) icon).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

}
