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
import setting.desing.DesignIcon;

public class PropertyRegisterFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	

	private ArrayList<CustomerRegister> customers;
	private ArrayList<PropertyRegister> propertys;
	
	private CustomLabel LBcustomer,LBtypeProperty,LBbuyDate,LBsellDate,LBname,LBdescription;
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
		setSize(500,500);
	}

	@Override
	public void initInitiation() {
		LBcustomer = new CustomLabel("CLIENTE");
		LBtypeProperty = new CustomLabel("TIPO DE BEM");
		LBbuyDate = new CustomLabel("DATA DA COMPRA");
		LBsellDate = new CustomLabel("DATA DA VENDA");
		LBname = new CustomLabel("BEM");
		LBdescription = new CustomLabel("DESCRIÇÃO");
		
		CBcustomer = new CustomComboBox();
		CBtypeProperty = new CustomComboBox();
		
		TFname = new CustomTextField();		
		TAdescription = new CustomTextArea();
		
		DFbuy = new CustomDateField();
		DFsell = new CustomDateField();
		
		BTclear = new CustomIconButton(DesignIcon.clear(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
		BTopenTable = new CustomIconButton(DesignIcon.open(),32,32);
		BTaddValue = new CustomIconButton(DesignIcon.add(),32,32);
		
		TBvalue = new CustomTable();
	}

	@Override
	public void initPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initFormat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initAdd() {
		// TODO Auto-generated method stub
		
	}

}
