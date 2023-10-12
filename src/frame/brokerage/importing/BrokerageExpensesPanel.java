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

public class BrokerageExpensesPanel extends CustomPanel {
    private static final long serialVersionUID = 1L;
    private int fontSize;
    private boolean isRegistering = false;

    private ArrayList<Component> components;
    private CustomLabel LBclearingT, LBclearingV, LBexecutionT, LBexecutionV, LBinHouseExecutionT, LBinHouseExecutionV,
            LBissT, LBissV, LBirrfT, LBirrfV, LBissPisCofinsT, LBissPisCofinsV,LBtotalBrokerageExpensesT,
            LBtotalBrokerageExpensesV, LBnetAmountForT, LBnetAmountForV;
    private CustomCurrencyField CFclearingV,CFexecutionV, CFinHouseExecutionV, CFissV, CFirrfV, CFissPisCofinsV,CFtotalBrokerageExpensesV, CFnetAmountForV;

    public BrokerageExpensesPanel(int width, int height, int fontSize) {
        super(width, height);
        this.fontSize = fontSize;
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
        LBclearingV = new CustomLabel("", fontSize, true);
        LBclearingV.setHorizontalAlignment(JLabel.RIGHT);

        LBexecutionT = new CustomLabel("Execução", fontSize, true);
        LBexecutionV = new CustomLabel("", fontSize, true);
        LBexecutionV.setHorizontalAlignment(JLabel.RIGHT);

        LBinHouseExecutionT = new CustomLabel("Execução casa", fontSize, true);
        LBinHouseExecutionV = new CustomLabel("", fontSize, true);
        LBinHouseExecutionV.setHorizontalAlignment(JLabel.RIGHT);

        LBissT = new CustomLabel("ISS", fontSize, true);
        LBissV = new CustomLabel("", fontSize, true);
        LBissV.setHorizontalAlignment(JLabel.RIGHT);

        LBirrfT = new CustomLabel("IRRF", fontSize, true);
        LBirrfV = new CustomLabel("", fontSize, true);
        LBirrfV.setHorizontalAlignment(JLabel.RIGHT);

        LBissPisCofinsT = new CustomLabel("ISS PIS COFINS", fontSize, true);
        LBissPisCofinsV = new CustomLabel("", fontSize, true);
        LBissPisCofinsV.setHorizontalAlignment(JLabel.RIGHT);

        LBtotalBrokerageExpensesT = new CustomLabel("Total corretagem/Despesas", fontSize, true);
        LBtotalBrokerageExpensesV = new CustomLabel("", fontSize, true);
        LBtotalBrokerageExpensesV.setHorizontalAlignment(JLabel.RIGHT);


        LBnetAmountForT = new CustomLabel("Líquido", fontSize, true);
        LBnetAmountForV = new CustomLabel("", fontSize, true);
        LBnetAmountForV.setHorizontalAlignment(JLabel.RIGHT);

        components = new ArrayList<Component>();
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
        for (int i = 0; i < components.size(); i++) {
            this.add(components.get(i));
        }
    }

    public void setRegister(BrokerageReportRegister register) {
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

        this.revalidate();
        this.repaint();
    }
}
