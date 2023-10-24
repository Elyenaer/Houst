package frame.brokerage.importing;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;

import components.CustomCurrencyField;
import components.CustomDateField;
import components.CustomLabel;
import components.CustomPanel;
import model.register.register.BrokerageReportRegister;
import setting.desing.Design;
import setting.function.FunctionBigDecimal;

public class BrokerageExpensesPanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
    private int fontSize;
    private boolean isRegistering = false;

    private ArrayList<Component> components;
    private CustomLabel LBclearingT, LBclearingV, LBexecutionT, LBexecutionV, LBinHouseExecutionT, LBinHouseExecutionV,
            LBissT, LBissV, LBirrfT, LBirrfV, LBissPisCofinsT, LBissPisCofinsV,LBtotalBrokerageExpensesT,
            LBtotalBrokerageExpensesV, LBnetAmountForT, LBnetAmountForV;
    private CustomCurrencyField CFirrfBase,CFclearingV,CFexecutionV, CFinHouseExecutionV, CFissV, CFirrfV, CFissPisCofinsV,CFtotalBrokerageExpensesV, CFnetAmountForV;
    private CustomDateField DFnetAmountForV;

    public BrokerageExpensesPanel(int width, int height, int fontSize) {
        super(width, height);
        this.fontSize = fontSize;
        init();
        initComponents();
    }
    
    public BrokerageExpensesPanel(int width, int height, int fontSize,boolean isRegistering) {
        super(width, height);
        this.fontSize = fontSize;
        this.isRegistering = isRegistering;
        init();
        initComponents();
    }

    private void init() {
        setLayout(null);
        this.setBackground(Design.mainBackground);
        this.setTitle("CORRETAGEM / DESPESAS");
    }

    private void initComponents() {
        initInitiation();
        initPosition();
        initFormat();
        initEvent();
        initAdd();
    }

    private void initInitiation() {
        LBclearingT = new CustomLabel("Clearing", fontSize, true);
        LBexecutionT = new CustomLabel("Execução", fontSize, true);
        LBinHouseExecutionT = new CustomLabel("Execução casa", fontSize, true);
        LBissT = new CustomLabel("ISS", fontSize, true);
        LBirrfT = new CustomLabel("IRRF s/ operações, base", fontSize, true);
        LBissPisCofinsT = new CustomLabel("ISS PIS COFINS", fontSize, true);
        LBtotalBrokerageExpensesT = new CustomLabel("Total corretagem/Despesas", fontSize, true);
        LBnetAmountForT = new CustomLabel("Líquido para", fontSize, true);

        components = new ArrayList<Component>();
        
        if(!isRegistering) {
            LBclearingV = new CustomLabel("", fontSize, true);
            LBclearingV.setHorizontalAlignment(JLabel.RIGHT);

            LBexecutionV = new CustomLabel("", fontSize, true);
            LBexecutionV.setHorizontalAlignment(JLabel.RIGHT);

            LBinHouseExecutionV = new CustomLabel("", fontSize, true);
            LBinHouseExecutionV.setHorizontalAlignment(JLabel.RIGHT);

            LBissV = new CustomLabel("", fontSize, true);
            LBissV.setHorizontalAlignment(JLabel.RIGHT);

            LBirrfV = new CustomLabel("", fontSize, true);
            LBirrfV.setHorizontalAlignment(JLabel.RIGHT);

            LBissPisCofinsV = new CustomLabel("", fontSize, true);
            LBissPisCofinsV.setHorizontalAlignment(JLabel.RIGHT);

            LBtotalBrokerageExpensesV = new CustomLabel("", fontSize, true);
            LBtotalBrokerageExpensesV.setHorizontalAlignment(JLabel.RIGHT);

            LBnetAmountForV = new CustomLabel("", fontSize, true);
            LBnetAmountForV.setHorizontalAlignment(JLabel.RIGHT);
            
            components.add(LBclearingT);
            components.add(LBclearingV);

            components.add(LBexecutionT);
            components.add(LBexecutionV);

            components.add(LBinHouseExecutionT);
            components.add(LBinHouseExecutionV);

            components.add(LBissT);
            components.add(LBissV);

            components.add(LBirrfT);
            components.add(LBirrfV);

            components.add(LBissPisCofinsT);
            components.add(LBissPisCofinsV);

            components.add(LBtotalBrokerageExpensesT);
            components.add(LBtotalBrokerageExpensesV);

            components.add(LBnetAmountForT);
            components.add(LBnetAmountForV);
        }else {
            CFclearingV = new CustomCurrencyField(9);
            CFexecutionV = new CustomCurrencyField(9);
            CFinHouseExecutionV = new CustomCurrencyField(9);
            CFissV = new CustomCurrencyField(9);
            CFirrfV = new CustomCurrencyField(9);
            CFissPisCofinsV = new CustomCurrencyField(9);
            CFtotalBrokerageExpensesV = new CustomCurrencyField(9);
            CFnetAmountForV = new CustomCurrencyField(9);            
            CFirrfBase = new CustomCurrencyField(9);            
            DFnetAmountForV = new CustomDateField(9);
            
            components.add(LBclearingT);
            components.add(CFclearingV);

            components.add(LBexecutionT);
            components.add(CFexecutionV);

            components.add(LBinHouseExecutionT);
            components.add(CFinHouseExecutionV);

            components.add(LBissT);
            components.add(CFissV);

            components.add(LBirrfT);
            components.add(CFirrfV);

            components.add(LBissPisCofinsT);
            components.add(CFissPisCofinsV);

            components.add(LBtotalBrokerageExpensesT);
            components.add(CFtotalBrokerageExpensesV);

            components.add(LBnetAmountForT);
            components.add(CFnetAmountForV);
        }
        
    }

    private void initPosition() {
    	int y1 = 20;
    	int y2 = 20;
    	int height = 15;
		if(isRegistering) {
			y2 = 18;
			height = 20;
			CFirrfBase.setBounds(120,98,90,20);
			DFnetAmountForV.setBounds(70,158,70,20);
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
        if(isRegistering) {
        	this.add(CFirrfBase);
            this.add(DFnetAmountForV);
        }        
    }

    public void setRegister(BrokerageReportRegister register) {    	
    	if(isRegistering) {
    		CFclearingV.setValue(register.getClearing());
            CFexecutionV.setValue(register.getExecution());
            CFinHouseExecutionV.setValue(register.getInHouseExecution());
            CFissV.setValue(register.getIss());
            CFirrfBase.setValue(register.getIrrfBase());
            CFirrfV.setValue(register.getIrrf());
            CFissPisCofinsV.setValue(register.getIssPisCofins());
            CFtotalBrokerageExpensesV.setValue(register.getTotalBrokerageExpenses());
            DFnetAmountForV.setDate(register.getNetAmountForDate());
            CFnetAmountForV.setValue(register.getNetAmountFor());
    	}else {
    		LBclearingV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getClearing()));
            LBexecutionV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getExecution()));
            LBinHouseExecutionV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getInHouseExecution()));
            LBissV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getIss()));
            LBirrfT.setText("I.R.R.F s/ operações, base "+FunctionBigDecimal.bigDecimalToCurrencyBR(register.getIrrfBase()));
            LBirrfV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getIrrf()));
            LBissPisCofinsV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getIssPisCofins()));
            LBtotalBrokerageExpensesV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getTotalBrokerageExpenses()));
            LBnetAmountForT.setText("Líquido para "+register.getNetAmountForDate());
            LBnetAmountForV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getNetAmountFor()));
    	}

        this.revalidate();
        this.repaint();
    }
    
    public BrokerageReportRegister getRegister(BrokerageReportRegister register) {
    	register.setClearing(CFclearingV.getValue());
        register.setExecution(CFexecutionV.getValue());
        register.setInHouseExecution(CFinHouseExecutionV.getValue());
        register.setIss(CFissV.getValue());
        register.setIrrfBase(CFirrfBase.getValue());
        register.setIrrf(CFirrfV.getValue());
        register.setIssPisCofins(CFissPisCofinsV.getValue());
        register.setTotalBrokerageExpenses(CFtotalBrokerageExpensesV.getValue());
        register.setNetAmountForDate(DFnetAmountForV.getDate());
        register.setNetAmountFor(CFnetAmountForV.getValue());
    	
    	return register;
    }
    
    public void clear() {
    	for (int i = 0; i < components.size(); i++) {
    		if(components.get(i) instanceof CustomCurrencyField) {
    			((CustomCurrencyField) components.get(i)).clear();
    		}else if(components.get(i) instanceof CustomDateField) {
    			((CustomDateField) components.get(i)).clear();
    		}
        }
    	
    	CFirrfBase.clear();
		DFnetAmountForV.clear();
    	
    	this.revalidate();
		this.repaint();
    }
}
