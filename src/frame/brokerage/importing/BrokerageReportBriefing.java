package frame.brokerage.importing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.CustomButton;
import components.CustomIconButton;
import components.CustomIconLabel;
import components.CustomLabel;
import components.LoadingDialog;
import frame.register.CustomerRegisterFrame;
import model.register.connect.BrokerageCustomerConnect;
import model.register.connect.BrokerageReportConnect;
import model.register.connect.CustomerConnect;
import model.register.register.CustomerRegister;
import model.view.register.BrokerageReportView;
import setting.desing.Design;
import setting.desing.DesignIcon;
import support.Message;

public class BrokerageReportBriefing extends JPanel {
	private static final long serialVersionUID = 1L;
	private BrokerageReportView register;
	private boolean selected = false;
	private boolean isChecking = true;
	private boolean customerOk = false;
	private boolean invoiceOk = false;
	
	private Color background = Design.componentsBackground;
	private int cornerRadius = 20;
	
	JLabel LBcustomerName,LBinvoiceNumber,LBdate,LBstockBrokerage,LBcheck;
	JButton BTdelete,BTopen,BTcustomerRegister;
	
	private final BrokerageReportImportFrame brokerageReportImportFrame;

	public BrokerageReportBriefing(BrokerageReportView register,BrokerageReportImportFrame brokerageReportImportFrame){
		this.register = register;
		this.brokerageReportImportFrame = brokerageReportImportFrame;
		initComponents();	
		setLayout(null);
		this.setBackground(Design.componentsBackground);	
        setOpaque(false);
        checking();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(background);
        
        int borderWidth = 1;
        if(selected) {
        	borderWidth = 3;
        }        
        Stroke borderStroke = new BasicStroke(borderWidth);
        g2d.setStroke(borderStroke);
        
        g2d.fillRoundRect(0,0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2d.setColor(Design.componentsForeground);
        g2d.drawRoundRect(0,0,getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2d.dispose();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }
	
	private void initComponents() {
		initInitiation();
		initPosition();
		initFormat();
		initEvent();	
		initAdd();
	}
	
	private void initInitiation() {
		LBcustomerName = new CustomLabel(register.getCustomerRegister().getName(),8,true);
		LBinvoiceNumber = new CustomLabel(register.getBrokerageReportRegister().getInvoiceNumber()+" ",9,true);
		LBdate = new CustomLabel(register.getBrokerageReportRegister().getTradingDate()+" ",9,true);
		LBstockBrokerage = new CustomIconLabel(DesignIcon.stockBrokerageIcon(register.getStockBrokerageRegister()),80,30);
		
		LBcheck = new CustomLabel("VALIDANDO...",9,false);
		
		BTdelete = new CustomIconButton(DesignIcon.delete16x16(),30,30);
		BTopen = new CustomIconButton(DesignIcon.report16x16(),30,30);
		BTcustomerRegister = new CustomButton("CLIENTE",9);
	}
	
	private void initPosition() {
		LBstockBrokerage.setBounds(10,10,80,30);
		LBcustomerName.setBounds(10,40,150,20);
		LBinvoiceNumber.setBounds(65,20,100,20);
		LBdate.setBounds(65,10,100,20);
		LBcheck.setBounds(10,75,90,16);
		BTdelete.setBounds(105,65,30,30);
		BTopen.setBounds(135,65,30,30);
		BTcustomerRegister.setBounds(10,70,90,22);
	}
	
	private void initFormat() {
		LBinvoiceNumber.setHorizontalAlignment(JLabel.RIGHT);
		LBdate.setHorizontalAlignment(JLabel.RIGHT);		
		BTcustomerRegister.setIcon(DesignIcon.warning16x16());	
		BTcustomerRegister.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void initEvent() {
		BTopen.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelected();
			}
		});
		BTdelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		BTcustomerRegister.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				openCustomerRegister();
			}
		});
	}
	
	private void initAdd() {
		this.add(LBstockBrokerage);
		this.add(LBcustomerName);
		this.add(LBinvoiceNumber);
		this.add(LBdate);
		this.add(LBcheck);
		this.add(BTdelete);
		this.add(BTopen);	
		this.add(BTcustomerRegister);
	}
	
	protected void checking() {		
		BTcustomerRegister.setVisible(false);
		LBcheck.setVisible(true);
		Thread checkingThread = new Thread(() -> {	
			try {
				if(new BrokerageReportConnect().checkRegister(register.getBrokerageReportRegister())) {
					LBcheck.setIcon(DesignIcon.error16x16());
					LBcheck.setText("JÁ SALVO!");		
					brokerageReportImportFrame.setBtDeleteDuplicatesVisible();
				}else {
					invoiceOk = true;
					int brokerageCustomerId = new BrokerageCustomerConnect().checkRegister(register.getBrokerageCustomerRegister());
					if(brokerageCustomerId==0) {
						LBcheck.setVisible(false);
						BTcustomerRegister.setVisible(true);					
					}else {
						register.getBrokerageReportRegister().setBrokerageCustomerId(brokerageCustomerId);
						for(int i=0;i<register.getTitles().size();i++) {
							register.getTitles().get(i).setBrokerageCustomerId(brokerageCustomerId);
						}
						LBcheck.setIcon(DesignIcon.checked16x16());
						LBcheck.setText("");
						customerOk = true;
					}
				}
				isChecking = false;
			} catch (IOException e) {
				e.printStackTrace();
			}			
        });	        
        
		Thread loadingThread = new Thread(() -> {	
			int cont = 0;
			String message = "VALIDANDO";
			while(isChecking){
				if(cont==0) {
					LBcheck.setText(message);
					cont++;
				}else if(cont==1) {
					LBcheck.setText(message+".");
					cont++;
				}else if(cont==2) {
					LBcheck.setText(message+"..");
					cont++;
				}else if(cont==3) {
					LBcheck.setText(message+"...");
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
        checkingThread.start();
	}
	
	protected void setSelected() {
		brokerageReportImportFrame.setRegister(register);
		for(BrokerageReportBriefing r: brokerageReportImportFrame.registers) {
			r.setSelected(false);
		}
		this.setSelected(true);
	}
	
	public BrokerageReportView getRegister() {
		if(customerOk && invoiceOk) {
			return register;
		}else {
			return null;
		}
	}
		
	public void setSelected(boolean selected) {
		if(selected) {
			if(this.selected) {
				return;
			}else {
				background = Design.componentsBackground2;
				paintComponent(getGraphics());				
			}	
		}else {
			if(!this.selected) {
				return;
			}else {
				background = Design.componentsBackground;
				paintComponent(getGraphics());
			}			
		}
		this.repaint();
		this.revalidate();
		this.selected = selected;		
	}

	public void deleteInvoice() {
		if(!invoiceOk) {
			brokerageReportImportFrame.delete(this);
		}
	}
	
	public void delete() {
		if(Message.Options("DESEJA REALMENTE EXCLUIR A NÂª " + register.getBrokerageReportRegister().getInvoiceNumber() + "?")) {
			brokerageReportImportFrame.delete(this);
		}		
	}

	public void openCustomerRegister() {
		LoadingDialog loadingDialog = new LoadingDialog(brokerageReportImportFrame);
        Thread loadingThread = new Thread(() -> {	            
	        loadingDialog.showLoading();
	        try {
				CustomerRegister r = new CustomerConnect().getByCpf(register.getCustomerRegister().getCpf());
				if(r==null) {
					r = register.getCustomerRegister();
					r.setId(0);
				}
				new CustomerRegisterFrame(r,register.getBrokerageCustomerRegister().getCode(),register.getStockBrokerageRegister().getId(),brokerageReportImportFrame).setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}	        
            loadingDialog.hideLoading();
        });	        
        loadingThread.start();   
	}
	
}
