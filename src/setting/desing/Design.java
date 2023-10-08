package setting.desing;

import java.awt.Color;
import java.awt.Font;

public class Design {
	
	public static Color mainBackground = new Color(20,20,20);
	public static Color mainForeground = new Color(255,255,255);	
	
	public static Color componentsBackground = new Color(0,0,0);
	public static Color componentsForeground = new Color(255,255,255);	
	public static Color componentsBackground2 = new Color(85,85,85);
	public static Color componentsForeground2 = new Color(255,255,255);	
	
	public static Color componentsBackgroundOk = new Color(0,150,0);
	public static Color componentsForegroundOk = new Color(255,255,255);	
	
	public static Font getFont(int size,boolean bold) {
		return new Font("SansSerif",bold ? Font.BOLD : Font.PLAIN,size);
	}

}
