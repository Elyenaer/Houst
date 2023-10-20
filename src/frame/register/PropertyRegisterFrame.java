package frame.register;

import java.util.ArrayList;


import components.CustomComboBox;
import components.CustomDateField;
import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTable;
import components.CustomTextArea;
import components.CustomTextField;
import model.register.register.CustomerRegister;
import model.register.register.PropertyRegister;
import model.view.register.BrokerageCustomerView;
import setting.desing.DesignIcon;

public class PropertyRegisterFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	

	private ArrayList<CustomerRegister> customers;
	private ArrayList<PropertyRegister> propertys;
	
	private CustomLabel LBcustomer,LBtypeProperty,LBbuyDate,LBsellDate,LBname,LBdescription,LBvalues;
	private CustomComboBox CBcustomer,CBtypeProperty;
	private CustomTextField TFname;
	private CustomTextArea TAdescription;
	private CustomDateField DFbuy,DFsell;
	private CustomIconButton BTclear,BTsave,BTdelete,BTopenTable,BTaddValue;
	private CustomTable TBvalue;
		
	public PropertyRegisterFrame() {
		init();
	}
	
	private void init() {
		setTitle("CADASTRO DE BENS");
		setLocationRelativeTo(null);
		setSize(800,370);
	}

	@Override
	public void initInitiation() {
		LBcustomer = new CustomLabel("CLIENTE:");
		LBtypeProperty = new CustomLabel("TIPO DE BEM:");
		LBbuyDate = new CustomLabel("DATA DA COMPRA:");
		LBsellDate = new CustomLabel("DATA DA VENDA:");
		LBname = new CustomLabel("BEM:");
		LBdescription = new CustomLabel("DESCRIÇÃO:");
		
		CBcustomer = new CustomComboBox();
		CBtypeProperty = new CustomComboBox();
		
		TFname = new CustomTextField();		
		TAdescription = new CustomTextArea();
		
		DFbuy = new CustomDateField();
		DFsell = new CustomDateField();
		
		BTclear = new CustomIconButton(DesignIcon.clear(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
		BTopenTable = new CustomIconButton(DesignIcon.open(),32,32);
		BTaddValue = new CustomIconButton(DesignIcon.add(),24,24);
		
		LBvalues = new CustomLabel("VALORES:");
		
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("ID");
		titles.add("ANO");
		titles.add("VALOR");
		TBvalue = new CustomTable(titles);
	}

	@Override
	public void initPosition() {
		LBcustomer.setBounds(30,30,100,25);
		CBcustomer.setBounds(80,30,330,25);
		
		LBtypeProperty.setBounds(30,60,100,25);		
		CBtypeProperty.setBounds(103,60,308,25);
		
		LBname.setBounds(30,90,100,25);
		TFname.setBounds(57,90,353,25);	
		
		LBbuyDate.setBounds(30,120,100,25);
		DFbuy.setBounds(130,120,80,25);
		
		LBsellDate.setBounds(240,120,100,25);		
		DFsell.setBounds(330,120,80,25);
		
		LBdescription.setBounds(30,153,100,25);		
		TAdescription.setBounds(28,175,380,100);
		
		LBvalues.setBounds(440,40,100,25);
		TBvalue.setBounds(440,70,310,205);
		BTaddValue.setBounds(730,40,24,24);
		
		/*
			
		
		BTclear.setBounds(,,,);
		BTdelete.setBounds(,,,);
		BTopenTable.setBounds(,,,);
		
		
		*/
	}

	@Override
	public void initFormat() {
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		for(int i=0;i<30;i++) {
			Object[] row = {						
				i,
				2000+i,
				"R$ 8.000,00"
			};
			rows.add(row);
		}
		TBvalue.setRows(rows);
	}

	@Override
	public void initEvent() {
		
	}

	@Override
	public void initAdd() {
		this.add(LBcustomer);
		this.add(LBtypeProperty);
		this.add(LBbuyDate);
		this.add(LBsellDate);
		this.add(LBname);
		this.add(LBdescription);
		
		this.add(CBcustomer);
		this.add(CBtypeProperty);
		
		this.add(TFname);	
		this.add(TAdescription);
		
		this.add(DFbuy);
		this.add(DFsell);
		
		this.add(BTclear);
		this.add(BTdelete);
		this.add(BTopenTable);
		this.add(BTaddValue);
		
		this.add(LBvalues);
		this.add(TBvalue);
	}

}
