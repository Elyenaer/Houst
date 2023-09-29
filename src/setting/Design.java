package setting;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.register.StockBrokerageRegister;

public class Design {
	
	public static Color mainBackground = new Color(10,10,10);
	public static Color mainForeground = new Color(255,255,255);	
	
	public static Color componentsBackground = new Color(0,0,0);
	public static Color componentsForeground = new Color(255,255,255);	
	public static Color componentsBackground2 = new Color(85,85,85);
	public static Color componentsForeground2 = new Color(255,255,255);	
	
	public static Font getFont(int size,boolean bold) {
		return new Font("SansSerif",bold ? Font.BOLD : Font.PLAIN,size);
	}
	
	public static Icon delete() {
		return loadImageAsIcon("delete.png");
	}
	
	public static Icon report() {
		return loadImageAsIcon("report.png");
	}
	
	public static Icon save() {
		return loadImageAsIcon("save.png");
	}
	
	public static Icon update() {
		return loadImageAsIcon("update.png");
	}
	
	public static Icon clear() {
		return loadImageAsIcon("clear.png");
	}
	
	public static Icon add() {
		return loadImageAsIcon("add.png");
	}
	
	public static Icon open() {
		return loadImageAsIcon("open.png");
	}
	
	public static Icon stockBrokerageIcon(StockBrokerageRegister stockBrokerageRegister) {
		if(stockBrokerageRegister.getId()==1) {
			return loadImageAsIcon("genial_investimentos.png");
		}else if(stockBrokerageRegister.getId()==2) {
			return loadImageAsIcon("xp_investimentos.png");
		}
		return new ImageIcon();
	}
	
	private static Icon loadImageAsIcon(String imageName) {
        try {
            InputStream inputStream = Design.class.getClassLoader().getResourceAsStream(imageName);
            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                return new ImageIcon(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon();
    }
	
}
