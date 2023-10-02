package setting.desing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.register.register.StockBrokerageRegister;

public class DesignIcon {
	
	public static BufferedImage icon16x16() {
		return loadImage("icon16x16.png");
	}
	
	public static Icon delete() {
		return loadImageAsIcon("delete.png");
	}
	
	public static Icon report() {
		return loadImageAsIcon("report.png");
	}
	
	public static Icon delete16x16() {
		return loadImageAsIcon("delete16x16.png");
	}
	
	public static Icon report16x16() {
		return loadImageAsIcon("report16x16.png");
	}
	
	public static Icon save() {
		return loadImageAsIcon("save.png");
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
	
	public static Icon error16x16() {
		return loadImageAsIcon("error16x16.png");
	}
	
	public static Icon checked16x16() {
		return loadImageAsIcon("checked16x16.png");
	}
	
	public static Icon warning16x16() {
		return loadImageAsIcon("warning16x16.png");
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
	
	private static BufferedImage loadImage(String imageName) {
        try {
            InputStream inputStream = Design.class.getClassLoader().getResourceAsStream(imageName);
            if (inputStream != null) {
                return ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(0, 0, 0);
    }
	
}
