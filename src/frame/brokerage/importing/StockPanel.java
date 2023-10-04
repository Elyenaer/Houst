package frame.brokerage.importing;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;

import components.CustomLabel;
import components.CustomPanel;
import model.register.register.BrokerageReportRegister;
import setting.desing.Design;
import support.FunctionCurrency;

public class StockPanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
    private int fontSize;

    private ArrayList<Component> components;
    
    private JLabel LBtermOptionsFeeT, LBtermOptionsFeeV, LBanaFeeT, LBanaFeeV, LBemolumentsT, LBemolumentsV, LBtotalBovespaT, LBtotalBovespaV;

    public StockPanel(int width, int height, int fontSize) {
        super(width, height);
        this.fontSize = fontSize;
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
        LBtermOptionsFeeV = new CustomLabel("", fontSize, true);
        LBtermOptionsFeeV.setHorizontalAlignment(JLabel.RIGHT);

        LBanaFeeT = new CustomLabel("Taxa ANA", fontSize, true);
        LBanaFeeV = new CustomLabel("", fontSize, true);
        LBanaFeeV.setHorizontalAlignment(JLabel.RIGHT);

        LBemolumentsT = new CustomLabel("Emolumentos", fontSize, true);
        LBemolumentsV = new CustomLabel("", fontSize, true);
        LBemolumentsV.setHorizontalAlignment(JLabel.RIGHT);

        LBtotalBovespaT = new CustomLabel("Total Bovespa", fontSize, true);
        LBtotalBovespaV = new CustomLabel("", fontSize, true);
        LBtotalBovespaV.setHorizontalAlignment(JLabel.RIGHT);
        
        components = new ArrayList<Component>();

        components.add(LBtermOptionsFeeT);
        components.add(LBtermOptionsFeeV);

        components.add(LBanaFeeT);
        components.add(LBanaFeeV);

        components.add(LBemolumentsT);
        components.add(LBemolumentsV);

        components.add(LBtotalBovespaT);
        components.add(LBtotalBovespaV);
    }

    private void initPosition() {
        int y = 20;
        for (int i = 0; i < components.size(); i += 2) {
            components.get(i).setBounds(12, y, 200, 15);
            components.get(i + 1).setBounds(215, y, 100, 15);
            y += 20;
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
	    LBtermOptionsFeeV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getTermOptionsFee()));
	    LBanaFeeV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getAnaFee()));
	    LBemolumentsV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getEmoluments()));
	    LBtotalBovespaV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getTotalBovespa()));	    
	    this.revalidate();
	    this.repaint();
	}

}
