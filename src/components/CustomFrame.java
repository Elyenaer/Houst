package components;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import setting.desing.Design;
import setting.desing.DesignIcon;

public abstract class CustomFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public CustomFrame() {
		this.setIconImage(DesignIcon.icon16x16());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		 getContentPane().setBackground(Design.mainBackground);
        initComponents();        
    }
	
	private void initComponents() {
		initInitiation();
		initPosition();
		initFormat();
		initEvent();	
		initAdd();    
		windowsClosing();
	}
	
	public abstract void initInitiation();	
	public abstract void initPosition();	
	public abstract void initFormat();	
	public abstract void initEvent();	
	public abstract void initAdd();
			
	private void windowsClosing() {
		this.addWindowListener((WindowListener) new WindowListener() {			
			public void windowOpened(WindowEvent e) {}			
			@Override
			public void windowIconified(WindowEvent e) {}			
			@Override
			public void windowDeiconified(WindowEvent e) {}			
			@Override
			public void windowDeactivated(WindowEvent e) {}			
			@Override
			public void windowClosing(WindowEvent e) {
				closeScreen();
			}			
			@Override
			public void windowClosed(WindowEvent e) {}			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
		
	public void closeScreen() {
		this.dispose();
	}
}
