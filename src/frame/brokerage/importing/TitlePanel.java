package frame.brokerage.importing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import components.CustomDateField;
import components.CustomIconButton;
import components.CustomIconLabel;
import components.CustomLabel;
import components.CustomTextField;
import components.LoadingDialog;
import model.register.register.BrokerageReportRegister;
import model.register.register.StockBrokerageRegister;
import model.view.connect.BrokerageCustomerViewConnect;
import model.view.register.BrokerageCustomerView;
import model.view.register.BrokerageReportView;
import setting.desing.Design;
import setting.desing.DesignIcon;
import support.Message;

public class TitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;
    private int fontSize;
    private boolean isRegistering = false;

    private CustomIconLabel LBstockBrokerage;
    private CustomLabel LBcustomerCode,LBcustomerCpf,LBcustomerName,LBinvoiceNumber,LBtradingDate,LBjustName;
    private CustomTextField TFinvoiceNumber,TFcode;
    private CustomDateField DFdate;
    private CustomIconButton BTsearch,BTaddTitle;
    
    private BrokerageCustomerView brokerageCustomer;
    private BrokerageReportView brokerageReportView;
    private StockBrokerageRegister stockBrokerage;
    private BrokerageReportRegisterFrame parentFrame;

    public TitlePanel(int width, int height, int fontSize) {
        this.fontSize = fontSize;
        init();
        initComponents();
    }
    
    public TitlePanel(int width, int height, int fontSize,boolean isRegistering,BrokerageReportRegisterFrame parentFrame) {
    	this.parentFrame = parentFrame;
    	this.isRegistering = isRegistering;
        this.fontSize = fontSize;
        init();
        initComponents();
    }

    private void init() {
        setLayout(null);
        this.setBackground(Design.mainBackground);
    }

    private void initComponents() {
        initInitiation();
        initPosition();
        initFormat();
        initEvent();
        initAdd();
    }

    private void initInitiation() {
    	LBstockBrokerage = new CustomIconLabel(null,350,60);    	
    	LBcustomerCode = new CustomLabel("CÓDIGO:",fontSize, true);
    	LBcustomerCpf = new CustomLabel("",fontSize, true);
    	LBcustomerName = new CustomLabel("",fontSize+4, true);
    	LBinvoiceNumber = new CustomLabel("",fontSize, true);
    	LBtradingDate = new CustomLabel("",fontSize, true);
    	
    	if(isRegistering) {
    		TFcode = new CustomTextField();
    		TFinvoiceNumber = new CustomTextField();
    		DFdate = new CustomDateField();    		
    		LBjustName = new CustomLabel("",fontSize,true);
    	}
    	
    	BTsearch = new CustomIconButton(DesignIcon.search16x16(),32,32);
    	BTaddTitle = new CustomIconButton(DesignIcon.add(),32,32);
    	
    }

    private void initPosition() {
    	LBstockBrokerage.setBounds(0,0,350,60);    	
    	    	
    	if(isRegistering){
    		LBcustomerCode.setBounds(0,82,100,25);
    		TFcode.setBounds(48,82,150,25); 
    		BTsearch.setBounds(205,78,32,32);
    		
    		LBinvoiceNumber.setBounds(525,3,150,20);
    		TFinvoiceNumber.setBounds(540,0,110,25); 
    		
    		LBtradingDate.setBounds(505,37,150,20);
    		DFdate.setBounds(540,35,110,25);
    		
    		LBcustomerName.setBounds(230,5,250,20);   
    		LBjustName.setBounds(230,25,250,20); 
    		LBcustomerCpf.setBounds(230,40,150,20); 
    		BTaddTitle.setBounds(615,78,32,32);    		
    	}else{
    		LBinvoiceNumber.setBounds(495,0,150,20);
    		LBcustomerCode.setBounds(0,90,100,25);
    		LBtradingDate.setBounds(495,20,150,20);
    		
    		LBcustomerName.setBounds(0,70,600,25); 
    		LBcustomerCpf.setBounds(495,90,150,25); 
    	}
    }

    private void initFormat() {
    	if(!isRegistering) {
    		LBinvoiceNumber.setHorizontalAlignment(JLabel.RIGHT);
        	LBtradingDate.setHorizontalAlignment(JLabel.RIGHT);
        	LBcustomerCpf.setHorizontalAlignment(JLabel.RIGHT);
    	} else {
    		DFdate.setVisible(false);
        	TFinvoiceNumber.setVisible(false);
        	BTaddTitle.setVisible(false);
    	}
    	this.setVisible(false);
    }

    private void initEvent() {
    	BTsearch.addActionListener(    			
    		new ActionListener() {
			
			@Override
				public void actionPerformed(ActionEvent e) {
					setBrokerageCustomer();					
				}
    		}
    	);
    	BTaddTitle.addActionListener(
				new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					 parentFrame.openTitleRegister(null);
				}
			}
		);
    }

    private void initAdd() {
        this.add(LBcustomerCode);
        this.add(LBstockBrokerage);
    	this.add(LBcustomerCpf);
    	this.add(LBcustomerName);
    	this.add(LBinvoiceNumber);
    	this.add(LBtradingDate);
    	
    	if(isRegistering) {
    		this.add(TFcode); 
    		this.add(TFinvoiceNumber); 
    		this.add(DFdate); 
    		this.add(BTsearch);
    		this.add(LBjustName);
    		this.add(BTaddTitle);
    	}    	
    }

    public BrokerageReportRegister getRegister(BrokerageReportRegister register) {
    	register.setStockBrokerageId(stockBrokerage.getId());
    	register.setBrokerageCustomerId(brokerageReportView.getBrokerageCustomerRegister().getBrokerageCustomerId());
    	register.setInvoiceNumber(TFinvoiceNumber.getText());
    	register.setTradingDate(DFdate.getDate());
    	
    	return register;
    }
    
    public int getBrokerageCustomerId() {
    	return brokerageCustomer.getBrokerageCustomerId();
    }
    
    public void setRegister(BrokerageReportView register) {
        setStockBrokerage(register.getStockBrokerageRegister());
        LBcustomerCpf.setText("CPF: "+ register.getCustomerRegister().getCpf());
        
        if(isRegistering) {          
        	brokerageReportView = register;
        	
        	LBjustName.setText(register.getCustomerRegister().getName());        	
	    	LBcustomerName.setText("CLIENTE:");
	    	
	    	LBinvoiceNumber.setText("Nº");
	    	TFinvoiceNumber.setText(register.getBrokerageReportRegister().getInvoiceNumber());
	    	TFinvoiceNumber.setVisible(true);
	    	
	    	LBtradingDate.setText("DATA:");
	    	DFdate.setDate(register.getBrokerageReportRegister().getTradingDate());	    	
	    	DFdate.setVisible(true);
	    	
	    	LBcustomerCode.setVisible(true);
	    	TFcode.setText(register.getBrokerageCustomerRegister().getCode());
	    	TFcode.setVisible(true);
	    	TFcode.setEnabled(false);
	    	
	    	BTaddTitle.setVisible(true);
	    	BTsearch.setEnabled(false);
        }else {
        	LBinvoiceNumber.setText("Nº "+register.getBrokerageReportRegister().getInvoiceNumber());
        	LBtradingDate.setText("DATA: " + register.getBrokerageReportRegister().getTradingDate());        	
        	LBcustomerCode.setText("CÓDIGO: " + register.getBrokerageCustomerRegister().getCode());        	
        	LBcustomerName.setText("CLIENTE: " + register.getCustomerRegister().getName());        	
        }
            	    	
    	this.setVisible(true);
        this.revalidate();
        this.repaint();
    }
    
    public void setStockBrokerage(StockBrokerageRegister register) {
    	this.stockBrokerage = register;
    	LBstockBrokerage.setIcon(DesignIcon.stockBrokerageIcon(register));
    	TFcode.setVisible(true);
    	BTsearch.setVisible(true);
    	BTsearch.setEnabled(true);;
    	LBcustomerCode.setVisible(true);    	
    	this.setVisible(true);    	
    }
    
    public void setBrokerageCustomer() {
    	LoadingDialog loadingDialog = new LoadingDialog(parentFrame);
    	Thread thread = new Thread(()-> {
    		try {
    			loadingDialog.showLoading();
				brokerageCustomer = new BrokerageCustomerViewConnect().getByCode(TFcode.getText(),stockBrokerage.getId());
				if(brokerageCustomer==null) {
					Message.Warning("CÓDIGO NÃO ENCONTRADO!",true);
				}else {
					LBcustomerCpf.setText("CPF: "+ brokerageCustomer.getCpf());
			    	LBjustName.setText(brokerageCustomer.getCustomerName());
			    	LBcustomerName.setText("CLIENTE:");
			    	LBinvoiceNumber.setText("Nº");
			    	LBtradingDate.setText("DATA:");
			    	DFdate.setVisible(true);
			    	BTaddTitle.setVisible(true);
			    	TFinvoiceNumber.setVisible(true);	
			    	TFcode.setEnabled(false);
			    	BTsearch.setEnabled(false);
			    	parentFrame.setStockBrokerageEnabled(false);
				}
				loadingDialog.hideLoading();
			} catch (IOException e) {				
				e.printStackTrace();
			}
    	});
    	thread.start();
    }

    public void clear() {
    	LBstockBrokerage.clear();
        LBcustomerCode.setVisible(false);
        LBcustomerCpf.setText("");
        LBcustomerName.setText("");
        LBinvoiceNumber.setText("");
        LBtradingDate.setText("");
        LBjustName.setText("");
        TFinvoiceNumber.setText("");
        TFinvoiceNumber.setVisible(false);
        TFcode.setText("");
        TFcode.setVisible(false);
        TFcode.setEnabled(true);
        DFdate.clear();
        DFdate.setVisible(false);
        BTsearch.setVisible(false);
        BTaddTitle.setVisible(false);
    }
}
