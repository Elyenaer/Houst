package frame.brokerage.importing;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;

import components.CustomLabel;
import components.CustomPanel;
import model.BrokerageReportRegister;
import setting.Design;
import support.FunctionCurrency;

public class ClearingPanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
    private int fontSize;

    private ArrayList<Component> components;
    private JLabel LBnetValueOperationT, LBnetValueOperationV, LBsettlementFeeT,
            LBsettlementFeeV, LBregistrationFeeT, LBregistrationFeeV, LBtotalCBLC_T, LBtotalCBLC_V;

    public ClearingPanel(int width, int height, int fontSize) {
        super(width, height);
        this.fontSize = fontSize;
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
        LBnetValueOperationV = new CustomLabel("", fontSize, true);
        LBnetValueOperationV.setHorizontalAlignment(JLabel.RIGHT);

        LBsettlementFeeT = new CustomLabel("Taxa de liquidação", fontSize, true);
        LBsettlementFeeV = new CustomLabel("", fontSize, true);
        LBsettlementFeeV.setHorizontalAlignment(JLabel.RIGHT);

        LBregistrationFeeT = new CustomLabel("Taxa de registro", fontSize, true);
        LBregistrationFeeV = new CustomLabel("", fontSize, true);
        LBregistrationFeeV.setHorizontalAlignment(JLabel.RIGHT);

        LBtotalCBLC_T = new CustomLabel("Total CBLC", fontSize, true);
        LBtotalCBLC_V = new CustomLabel("", fontSize, true);
        LBtotalCBLC_V.setHorizontalAlignment(JLabel.RIGHT);

        components = new ArrayList<Component>();

        components.add(LBnetValueOperationT);
        components.add(LBnetValueOperationV);

        components.add(LBsettlementFeeT);
        components.add(LBsettlementFeeV);

        components.add(LBregistrationFeeT);
        components.add(LBregistrationFeeV);

        components.add(LBtotalCBLC_T);
        components.add(LBtotalCBLC_V);
    }

    private void initPosition() {
        int y = 20;
        for (int i = 0; i < components.size(); i += 2) {
            components.get(i).setBounds(20, y, 200, 15);
            components.get(i + 1).setBounds(215, y, 100, 15);
            y += 20;
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
        LBnetValueOperationV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getNetValueOperation()));
        LBsettlementFeeV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getSettlementFee()));
        LBregistrationFeeV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getRegistrationFee()));
        LBtotalCBLC_V.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getTotalCBLC()));
        this.revalidate();
        this.repaint();
    }
}
