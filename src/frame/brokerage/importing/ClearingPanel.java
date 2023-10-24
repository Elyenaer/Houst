package frame.brokerage.importing;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;

import components.CustomCurrencyField;
import components.CustomLabel;
import components.CustomPanel;
import model.register.register.BrokerageReportRegister;
import setting.desing.Design;
import setting.function.FunctionBigDecimal;

public class ClearingPanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
    private int fontSize;
    private boolean isRegistering = false;

    private ArrayList<Component> components;
    private CustomLabel LBnetValueOperationT, LBnetValueOperationV, LBsettlementFeeT,
            LBsettlementFeeV, LBregistrationFeeT, LBregistrationFeeV, LBtotalCBLC_T, LBtotalCBLC_V;
    private CustomCurrencyField CFnetValueOperationV,CFsettlementFeeV,CFregistrationFeeV,CFtotalCBLC_V;

    public ClearingPanel(int width, int height, int fontSize) {
        super(width, height);
        this.fontSize = fontSize;
        init();
        initComponents();
    }

    public ClearingPanel(int width, int height, int fontSize,boolean isRegistering) {
        super(width, height);
        this.fontSize = fontSize;
        this.isRegistering = isRegistering;
        init();
        initComponents();
    }
    
    private void init() {
        setLayout(null);
        this.setBackground(Design.mainBackground);
        this.setTitle("CLEARING");
    }

    private void initComponents() {
        initInitiation();
        initPosition();
        initFormat();
        initEvent();
        initAdd();
    }

    private void initInitiation() {
    	LBnetValueOperationT = new CustomLabel("Valor líquido da operação", fontSize, true);
        LBsettlementFeeT = new CustomLabel("Taxa de liquidação", fontSize, true);
        LBregistrationFeeT = new CustomLabel("Taxa de registro", fontSize, true);
        LBtotalCBLC_T = new CustomLabel("Total CBLC", fontSize, true);
        
        components = new ArrayList<Component>();
        
        if(isRegistering) {
            CFnetValueOperationV = new CustomCurrencyField(9);
            CFsettlementFeeV = new CustomCurrencyField(9);
            CFregistrationFeeV = new CustomCurrencyField(9);
            CFtotalCBLC_V = new CustomCurrencyField(9);

            components.add(LBnetValueOperationT);
            components.add(CFnetValueOperationV);

            components.add(LBsettlementFeeT);
            components.add(CFsettlementFeeV);

            components.add(LBregistrationFeeT);
            components.add(CFregistrationFeeV);

            components.add(LBtotalCBLC_T);
            components.add(CFtotalCBLC_V);
            
        }else {
            LBnetValueOperationV = new CustomLabel("", fontSize, true);
            LBnetValueOperationV.setHorizontalAlignment(JLabel.RIGHT);

            LBsettlementFeeV = new CustomLabel("", fontSize, true);
            LBsettlementFeeV.setHorizontalAlignment(JLabel.RIGHT);

            LBregistrationFeeV = new CustomLabel("", fontSize, true);
            LBregistrationFeeV.setHorizontalAlignment(JLabel.RIGHT);

            LBtotalCBLC_V = new CustomLabel("", fontSize, true);
            LBtotalCBLC_V.setHorizontalAlignment(JLabel.RIGHT);

            components.add(LBnetValueOperationT);
            components.add(LBnetValueOperationV);

            components.add(LBsettlementFeeT);
            components.add(LBsettlementFeeV);

            components.add(LBregistrationFeeT);
            components.add(LBregistrationFeeV);

            components.add(LBtotalCBLC_T);
            components.add(LBtotalCBLC_V);
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
        for (int i = 0; i < components.size(); i++) {
            this.add(components.get(i));
        }
    }

    public void setRegister(BrokerageReportRegister register) {
    	if(isRegistering) {
    		CFnetValueOperationV.setValue(register.getNetValueOperation());
	        CFsettlementFeeV.setValue(register.getSettlementFee());
	        CFregistrationFeeV.setValue(register.getRegistrationFee());
	        CFtotalCBLC_V.setValue(register.getTotalCBLC());
    	}else {
    		LBnetValueOperationV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getNetValueOperation()));
	        LBsettlementFeeV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getSettlementFee()));
	        LBregistrationFeeV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getRegistrationFee()));
	        LBtotalCBLC_V.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getTotalCBLC()));
    	}
       
        this.revalidate();
        this.repaint();
    }
    
    public BrokerageReportRegister getRegister(BrokerageReportRegister register) {
    	register.setNetValueOperation(CFnetValueOperationV.getValue());
        register.setSettlementFee(CFsettlementFeeV.getValue());
        register.setRegistrationFee(CFregistrationFeeV.getValue());
        register.setTotalCBLC(CFtotalCBLC_V.getValue());
        
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
