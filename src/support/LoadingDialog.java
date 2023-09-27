package support;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import setting.Design;

public class LoadingDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JLabel loadingLabel;

    public LoadingDialog(JFrame parentFrame) {
        loadingLabel = new JLabel("CARREGANDO...");
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
    				loadingLabel.setText("CARREGANDO");
    				cont++;
    			}else if(cont==1) {
    				loadingLabel.setText("CARREGANDO.");
    				cont++;
    			}else if(cont==2) {
    				loadingLabel.setText("CARREGANDO..");
    				cont++;
    			}else if(cont==3) {
    				loadingLabel.setText("CARREGANDO...");
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
    }
}