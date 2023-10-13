package frame.brokerage.importing;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;

import components.CustomCurrencyField;
import components.CustomLabel;
import components.CustomPanel;
import model.register.register.BrokerageReportRegister;
import setting.desing.Design;
import support.FunctionBigDecimal;

public class StockPanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
    private int fontSize;
    private boolean isRegistering = false;

    private ArrayList<Component> components;
    
    private CustomLabel LBtermOptionsFeeT, LBtermOptionsFeeV, LBanaFeeT, LBanaFeeV, LBemolumentsT, LBemolumentsV, LBtotalBovespaT, LBtotalBovespaV;
    private CustomCurrencyField CFtermOptionsFeeV, CFanaFeeV, CFemolumentsV, CFtotalBovespaV;

    public StockPanel(int width, int height, int fontSize) {
        super(width, height);
        this.fontSize = fontSize;
        init();
        initComponents();
    }
    
    public StockPanel(int width, int height, int fontSize,boolean isRegistering) {
        super(width, height);
        this.fontSize = fontSize;
        this.isRegistering = isRegistering;
        init();
        initComponents();
    }

    private void init() {
        setLayout(null);
        this.setBackground(Design.mainBackground);
        this.setTitle("BOLSA");
    }

    private void initComponents() {
        initInitiation();
        initPosition();
        initFormat();
        initEvent();
        initAdd();
    }

    private void initInitiation() {        
        LBtermOptionsFeeT = new CustomLabel("Taxa de Opções a Termo", fontSize, true);
        LBanaFeeT = new CustomLabel("Taxa ANA", fontSize, true);
        LBemolumentsT = new CustomLabel("Emolumentos", fontSize, true);
        LBtotalBovespaT = new CustomLabel("Total Bovespa", fontSize, true);
        
        components = new ArrayList<Component>();
        
        if(isRegistering) {
        	CFtermOptionsFeeV = new CustomCurrencyField(9);
            CFanaFeeV = new CustomCurrencyField(9);
            CFemolumentsV = new CustomCurrencyField(9);
            CFtotalBovespaV = new CustomCurrencyField(9);

            components.add(LBtermOptionsFeeT);
            components.add(CFtermOptionsFeeV);

            components.add(LBanaFeeT);
            components.add(CFanaFeeV);

            components.add(LBemolumentsT);
            components.add(CFemolumentsV);

            components.add(LBtotalBovespaT);
            components.add(CFtotalBovespaV);
        }else {
            LBtermOptionsFeeV = new CustomLabel("", fontSize, true);
            LBtermOptionsFeeV.setHorizontalAlignment(JLabel.RIGHT);

            LBanaFeeV = new CustomLabel("", fontSize, true);
            LBanaFeeV.setHorizontalAlignment(JLabel.RIGHT);

            LBemolumentsV = new CustomLabel("", fontSize, true);
            LBemolumentsV.setHorizontalAlignment(JLabel.RIGHT);

            LBtotalBovespaV = new CustomLabel("", fontSize, true);
            LBtotalBovespaV.setHorizontalAlignment(JLabel.RIGHT);
            
            components.add(LBtermOptionsFeeT);
            components.add(LBtermOptionsFeeV);

            components.add(LBanaFeeT);
            components.add(LBanaFeeV);

            components.add(LBemolumentsT);
            components.add(LBemolumentsV);

            components.add(LBtotalBovespaT);
            components.add(LBtotalBovespaV);
        }
    }

    private void initPosition() {
    	int y1 = 20;
    	int y2 = 20;
    	int height = 15;
		if(isRegistering) {
			y2 = 18;
			height = 20;
		}		
		for(int i=0;i<components.size();i+=2) {
			components.get(i).setBounds(12,y1,200,15);
			components.get(i+1).setBounds(215,y2,100,height);
			y1 += 20;
			y2 += 20;
		}
    }
    
    private void initFormat() {
		
	}
	
	private void initEvent() {
		
	}
	
	private void initAdd() {
		for(int i=0;i<components.size();i++) {
			this.add(components.get(i));
		}
	}

	public void setRegister(BrokerageReportRegister register) {
		if(isRegistering) {
			CFtermOptionsFeeV.setValue(register.getTermOptionsFee());
		    CFanaFeeV.setValue(register.getAnaFee());
		    CFemolumentsV.setValue(register.getEmoluments());
		    CFtotalBovespaV.setValue(register.getTotalBovespa());	 
		}else {
			LBtermOptionsFeeV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getTermOptionsFee()));
		    LBanaFeeV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getAnaFee()));
		    LBemolumentsV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getEmoluments()));
		    LBtotalBovespaV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getTotalBovespa()));	    
		}
	   
	    this.revalidate();
	    this.repaint();
	}

	public BrokerageReportRegister getRegister(BrokerageReportRegister register) {
		register.setTermOptionsFee(CFtermOptionsFeeV.getValue());
	    register.setAnaFee(CFanaFeeV.getValue());
	    register.setEmoluments(CFemolumentsV.getValue());
	    register.setTotalBovespa(CFtotalBovespaV.getValue());
	   
	    return register;
	}
	
	public void clear() {
    	for (int i = 0; i < components.size(); i++) {
    		if(components.get(i) instanceof CustomCurrencyField) {
    			((CustomCurrencyField) components.get(i)).clear();
    		}
        }
		
		this.revalidate();
		this.repaint();
	}
}
