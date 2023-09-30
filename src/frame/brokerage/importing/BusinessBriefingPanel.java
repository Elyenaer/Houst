package frame.brokerage.importing;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;

import components.CustomLabel;
import components.CustomPanel;
import model.register.register.BrokerageReportRegister;
import setting.Design;
import support.FunctionCurrency;

public class BusinessBriefingPanel extends CustomPanel{
	private static final long serialVersionUID = 1L;
	private int fontSize;
	
	private ArrayList<Component> components;
	private JLabel LBdebenturesT,LBdebenturesV,LBspotSalesT,LBspotSalesV,LBspotPurchasesT,
		LBspotPurchasesV,LBoptionsPurchasesV,LBoptionsPurchasesT,LBoptionsSalesV,LBoptionsSalesT,
		LBforwardOperationV,LBforwardOperationT,LBvalueOfPublicSecuritiesOperationT,LBvalueOfPublicSecuritiesOperationV,
		LBoperationValueT,LBoperationValueV;
	
	public BusinessBriefingPanel(int width,int height,int fontSize) {
		super(width, height);
		this.fontSize = fontSize;
		init();
        initComponents();
	}	
	
	private void init() {
		setLayout(null);
		this.setBackground(Design.mainBackground);
		this.setTitle("RESUMO FINANCEIRO");
	}
		
	private void initComponents() {
		initInitiation();
		initPosition();
		initFormat();
		initEvent();	
		initAdd();    	
	}
	
	private void initInitiation() {		
		LBdebenturesT = new CustomLabel("Debentures",fontSize,true);
		LBdebenturesV = new CustomLabel("",fontSize,true);
		LBdebenturesV.setHorizontalAlignment(JLabel.RIGHT);
		
		LBspotSalesT = new CustomLabel("Vendas á vista",fontSize,true);
		LBspotSalesV = new CustomLabel("",fontSize,true);
		LBspotSalesV.setHorizontalAlignment(JLabel.RIGHT);
		
		LBspotPurchasesT = new CustomLabel("Compras à vista",fontSize,true);
		LBspotPurchasesV = new CustomLabel("",fontSize,true);
		LBspotPurchasesV.setHorizontalAlignment(JLabel.RIGHT);
		
		LBoptionsPurchasesT = new CustomLabel("Opções de compra",fontSize,true);
		LBoptionsPurchasesV = new CustomLabel("",fontSize,true);
		LBoptionsPurchasesV.setHorizontalAlignment(JLabel.RIGHT);
				
		LBoptionsSalesT = new CustomLabel("Opções de venda",fontSize,true);
		LBoptionsSalesV = new CustomLabel("",fontSize,true);
		LBoptionsSalesV.setHorizontalAlignment(JLabel.RIGHT);
		
		LBforwardOperationT = new CustomLabel("Operações à termo",fontSize,true);
		LBforwardOperationV = new CustomLabel("",fontSize,true);
		LBforwardOperationV.setHorizontalAlignment(JLabel.RIGHT);
				
		LBvalueOfPublicSecuritiesOperationT = new CustomLabel("Valor das opera. c/ titulos públic. (V. Nom.)",fontSize,true);
		LBvalueOfPublicSecuritiesOperationV = new CustomLabel("",fontSize,true);
		LBvalueOfPublicSecuritiesOperationV.setHorizontalAlignment(JLabel.RIGHT);
		
		LBoperationValueT = new CustomLabel("Valor das operações",fontSize,true);
		LBoperationValueV = new CustomLabel("",fontSize,true);
		LBoperationValueV.setHorizontalAlignment(JLabel.RIGHT);
		
		components = new ArrayList<Component>();
		
		components.add(LBdebenturesT);
		components.add(LBdebenturesV);
		
		components.add(LBspotSalesT);
		components.add(LBspotSalesV);
		
		components.add(LBspotPurchasesT);
		components.add(LBspotPurchasesV);
		
		components.add(LBoptionsPurchasesT);
		components.add(LBoptionsPurchasesV);
		
		components.add(LBoptionsSalesT);
		components.add(LBoptionsSalesV);		
		
		components.add(LBforwardOperationT);
		components.add(LBforwardOperationV);
				
		components.add(LBvalueOfPublicSecuritiesOperationT);
		components.add(LBvalueOfPublicSecuritiesOperationV);
		
		components.add(LBoperationValueT);
		components.add(LBoperationValueV);
	}
	
	private void initPosition() {
		int y = 20;
		for(int i=0;i<components.size();i+=2) {
			components.get(i).setBounds(20,y,200,15);
			components.get(i+1).setBounds(215,y,100,15);
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
		LBdebenturesV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getDebentures()));		
		LBspotSalesV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getSpotSales()));		
		LBspotPurchasesV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getSpotPurchases()));		
		LBoptionsPurchasesV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getOptionsPurchases()));					
		LBoptionsSalesV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getOptionsSales()));			
		LBforwardOperationV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getForwardOperation()));				
		LBvalueOfPublicSecuritiesOperationV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getValueOfPublicSecuritiesOperation()));		
		LBoperationValueV.setText(FunctionCurrency.bigDecimalToCurrencyBR(register.getOperationValue()));			
		this.revalidate();
		this.repaint();
	}
}
