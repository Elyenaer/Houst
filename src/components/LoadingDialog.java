package components;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import setting.desing.Design;
import setting.desing.DesignIcon;

public class LoadingDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JLabel loadingLabel;
	private String message = "CARREGANDO";

    public LoadingDialog(JFrame parentFrame) {
        init(parentFrame,message);       
    }
    
    public LoadingDialog(JFrame parentFrame,String message) {
    	init(parentFrame,message);
	}
    
    private void init(JFrame parentFrame,String message) {
    	this.setIconImage(DesignIcon.icon16x16());
    	this.message = message;
    	loadingLabel = new JLabel(message+"...");
        loadingLabel.setHorizontalAlignment(JLabel.CENTER);  
        
        getContentPane().setBackground(Design.componentsBackground2);
        loadingLabel.setForeground(Design.componentsForeground2);
        loadingLabel.setFont(Design.getFont(10,true));
        
        add(loadingLabel);        
        setSize(300, 100);
        setLocationRelativeTo(parentFrame);
        setResizable(false);
    }
    
    private void process() {
    	Thread loadingThread = new Thread(() -> {
    		int cont = 0;
    		while(this.isVisible()) {
    			if(cont==0) {
    				loadingLabel.setText(message);
    				cont++;
    			}else if(cont==1) {
    				loadingLabel.setText(message+".");
    				cont++;
    			}else if(cont==2) {
    				loadingLabel.setText(message+"..");
    				cont++;
    			}else if(cont==3) {
    				loadingLabel.setText(message+"...");
    				cont++;
    			}else {
    				cont=0;
    			}
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	});
    	loadingThread.start();  
    }

    public void showLoading() {
        setVisible(true);
        process();
    }

    public void hideLoading() {
        setVisible(false);
        dispose();
    }
}