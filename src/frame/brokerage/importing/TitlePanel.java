package frame.brokerage.importing;

import javax.swing.JLabel;
import javax.swing.JPanel;

import components.CustomIconLabel;
import components.CustomLabel;
import model.register.register.BrokerageReportRegister;
import setting.Design;

public class TitlePanel extends JPanel{
	private static final long serialVersionUID = 1L;
    private int fontSize;

    private JLabel LBstockBrokerage, LBcustomerCode,LBcustomerCpf,LBcustomerName,LBinvoiceNumber,LBtradingDate;

    public TitlePanel(int width, int height, int fontSize) {
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
    	LBcustomerCode = new CustomLabel("",fontSize, true);
    	LBcustomerCpf = new CustomLabel("",fontSize, true);
    	LBcustomerName = new CustomLabel("",fontSize+4, true);
    	LBinvoiceNumber = new CustomLabel("",fontSize, true);
    	LBtradingDate = new CustomLabel("",fontSize, true);
    }

    private void initPosition() {
    	LBstockBrokerage.setBounds(0,0,350,60);
    	LBinvoiceNumber.setBounds(495,0,150,20);
    	LBtradingDate.setBounds(495,20,150,20);
    	
    	LBcustomerName.setBounds(0,70,600,25); 
    	LBcustomerCode.setBounds(0,90,100,25);
    	LBcustomerCpf.setBounds(495,90,150,25);    	   	
    }

    private void initFormat() {
    	LBinvoiceNumber.setHorizontalAlignment(JLabel.RIGHT);
    	LBtradingDate.setHorizontalAlignment(JLabel.RIGHT);
    	LBcustomerCpf.setHorizontalAlignment(JLabel.RIGHT);
    }

    private void initEvent() {

    }

    private void initAdd() {
        this.add(LBcustomerCode);
        this.add(LBstockBrokerage);
    	this.add(LBcustomerCpf);
    	this.add(LBcustomerName);
    	this.add(LBinvoiceNumber);
    	this.add(LBtradingDate);
    }

    public void setRegister(BrokerageReportRegister register) {        
        LBstockBrokerage.setIcon(Design.stockBrokerageIcon(register.getStockBrokerage()));
    	LBinvoiceNumber.setText("Nº "+register.getInvoiceNumber());
    	LBtradingDate.setText("DATA: " + register.getTradingDate());
    	
    	//LBcustomerCode.setText("CÓDIGO: " + register.getCustomer().getCode());
    	LBcustomerCpf.setText("CPF: "+ register.getCustomer().getCpf());
    	LBcustomerName.setText("CLIENTE: " + register.getCustomer().getName()); 
    	
        this.revalidate();
        this.repaint();
    }

}
