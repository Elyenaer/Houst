package components;

import javax.swing.Icon;
import javax.swing.JLabel;
import support.FunctionIcon;
import java.awt.Dimension;

public class CustomIconLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public CustomIconLabel(Icon icon, int width, int height) {
        Icon scaledIcon = FunctionIcon.scaleIcon(icon, width, height);
        setIcon(scaledIcon);
        setPreferredSize(new Dimension(width, height));
    }

}