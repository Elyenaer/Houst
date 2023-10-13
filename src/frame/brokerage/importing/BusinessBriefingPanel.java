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

public class BusinessBriefingPanel extends CustomPanel{
	private static final long serialVersionUID = 1L;
	private int fontSize;
	
	private ArrayList<Component> components;
	private CustomLabel LBdebenturesT,LBdebenturesV,LBspotSalesT,LBspotSalesV,LBspotPurchasesT,
		LBspotPurchasesV,LBoptionsPurchasesV,LBoptionsPurchasesT,LBoptionsSalesV,LBoptionsSalesT,
		LBforwardOperationV,LBforwardOperationT,LBvalueOfPublicSecuritiesOperationT,LBvalueOfPublicSecuritiesOperationV,
		LBoperationValueT,LBoperationValueV;
	private CustomCurrencyField NFdebenturesV,NFspotSalesV,NFspotPurchasesV,NFoptionsPurchasesV,NFoptionsSalesV,
		NFforwardOperationV,NFvalueOfPublicSecuritiesOperationV,NFoperationValueV;
	
	private boolean isRegistering = false;
	
	public BusinessBriefingPanel(int width,int height,int fontSize) {
		super(width, height);
		this.fontSize = fontSize;
		init();
        initComponents();
	}	
	
	public BusinessBriefingPanel(int width,int height,int fontSize,boolean isRegistering) {
		super(width, height);
		this.isRegistering = isRegistering;
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
		LBspotSalesT = new CustomLabel("Vendas á vista",fontSize,true);		
		LBspotPurchasesT = new CustomLabel("Compras à vista",fontSize,true);		
		LBoptionsPurchasesT = new CustomLabel("Opções de compra",fontSize,true);				
		LBoptionsSalesT = new CustomLabel("Opções de venda",fontSize,true);		
		LBforwardOperationT = new CustomLabel("Operações à termo",fontSize,true);				
		LBvalueOfPublicSecuritiesOperationT = new CustomLabel("Valor das opera. c/ titulos públic. (V. Nom.)",fontSize,true);		
		LBoperationValueT = new CustomLabel("Valor das operações",fontSize,true);
		
		components = new ArrayList<Component>();
		
		if(!isRegistering) {
			LBdebenturesV = new CustomLabel("",fontSize,true);
			LBdebenturesV.setHorizontalAlignment(JLabel.RIGHT);
			
			LBspotSalesV = new CustomLabel("",fontSize,true);
			LBspotSalesV.setHorizontalAlignment(JLabel.RIGHT);
			
			LBspotPurchasesV = new CustomLabel("",fontSize,true);
			LBspotPurchasesV.setHorizontalAlignment(JLabel.RIGHT);
			
			LBoptionsPurchasesV = new CustomLabel("",fontSize,true);
			LBoptionsPurchasesV.setHorizontalAlignment(JLabel.RIGHT);
					
			LBoptionsSalesV = new CustomLabel("",fontSize,true);
			LBoptionsSalesV.setHorizontalAlignment(JLabel.RIGHT);
			
			LBforwardOperationV = new CustomLabel("",fontSize,true);
			LBforwardOperationV.setHorizontalAlignment(JLabel.RIGHT);
					
			LBvalueOfPublicSecuritiesOperationV = new CustomLabel("",fontSize,true);
			LBvalueOfPublicSecuritiesOperationV.setHorizontalAlignment(JLabel.RIGHT);
			
			LBoperationValueV = new CustomLabel("",fontSize,true);
			LBoperationValueV.setHorizontalAlignment(JLabel.RIGHT);
			
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
		}else {
			NFdebenturesV = new CustomCurrencyField(9);		
			NFspotSalesV = new CustomCurrencyField(9);		
			NFspotPurchasesV = new CustomCurrencyField(9);			
			NFoptionsPurchasesV = new CustomCurrencyField(9);					
			NFoptionsSalesV = new CustomCurrencyField(9);			
			NFforwardOperationV = new CustomCurrencyField(9);						
			NFvalueOfPublicSecuritiesOperationV = new CustomCurrencyField(9);				
			NFoperationValueV = new CustomCurrencyField(9);	
			
			components.add(LBdebenturesT);
			components.add(NFdebenturesV);
			
			components.add(LBspotSalesT);
			components.add(NFspotSalesV);
			
			components.add(LBspotPurchasesT);
			components.add(NFspotPurchasesV);
			
			components.add(LBoptionsPurchasesT);
			components.add(NFoptionsPurchasesV);
			
			components.add(LBoptionsSalesT);
			components.add(NFoptionsSalesV);		
			
			components.add(LBforwardOperationT);
			components.add(NFforwardOperationV);
					
			components.add(LBvalueOfPublicSecuritiesOperationT);
			components.add(NFvalueOfPublicSecuritiesOperationV);
			
			components.add(LBoperationValueT);
			components.add(NFoperationValueV);
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
		if(!isRegistering) {
			LBdebenturesV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getDebentures()));		
			LBspotSalesV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getSpotSales()));		
			LBspotPurchasesV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getSpotPurchases()));		
			LBoptionsPurchasesV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getOptionsPurchases()));					
			LBoptionsSalesV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getOptionsSales()));			
			LBforwardOperationV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getForwardOperation()));				
			LBvalueOfPublicSecuritiesOperationV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getValueOfPublicSecuritiesOperation()));		
			LBoperationValueV.setText(FunctionBigDecimal.bigDecimalToCurrencyBR(register.getOperationValue()));		
		}else {
			NFdebenturesV.setValue(register.getDebentures());		
			NFspotSalesV.setValue(register.getSpotSales());		
			NFspotPurchasesV.setValue(register.getSpotPurchases());		
			NFoptionsPurchasesV.setValue(register.getOptionsPurchases());					
			NFoptionsSalesV.setValue(register.getOptionsSales());			
			NFforwardOperationV.setValue(register.getForwardOperation());				
			NFvalueOfPublicSecuritiesOperationV.setValue(register.getValueOfPublicSecuritiesOperation());		
			NFoperationValueV.setValue(register.getOperationValue());	
		}
					
		this.revalidate();
		this.repaint();
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
