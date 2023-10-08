package components;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import setting.desing.Design;

public class CustomMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	
	public CustomMenu(String title) {
		init(title);
	}
			
	private void init(String title) {
		this.setText(title);
		
		JPopupMenu menuPopup = getPopupMenu();
        menuPopup.setLayout(new BoxLayout(menuPopup, BoxLayout.Y_AXIS));
        menuPopup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
		this.setOpaque(true);
		this.setBackground(Design.componentsBackground2);
		this.setForeground(Design.componentsForeground2);
	}

}
