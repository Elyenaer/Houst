package frame.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


import components.CustomComboBox;
import components.CustomDateField;
import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTable;
import components.CustomTextArea;
import components.CustomTextField;
import model.register.connect.CustomerConnect;
import model.register.connect.PropertyConnect;
import model.register.register.CustomerRegister;
import model.register.register.EntryPropertyRegister;
import model.register.register.EntryPropertyValueRegister;
import model.register.register.PropertyRegister;
import setting.desing.DesignIcon;
import support.Message;

public class PropertyRegisterFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<CustomerRegister> customers;
	private ArrayList<PropertyRegister> propertys;
	
	private EntryPropertyRegister register;
	private ArrayList<EntryPropertyValueRegister> values;
	
	private CustomLabel LBcustomer,LBtypeProperty,LBbuyDate,LBsellDate,LBname,LBdescription,LBvalues;
	private CustomComboBox CBcustomer,CBtypeProperty;
	private CustomTextField TFname;
	private CustomTextArea TAdescription;
	private CustomDateField DFbuy,DFsell;
	private CustomIconButton BTclear,BTsave,BTdelete,BTaddValue;
	private CustomTable TBvalue;
		
	public PropertyRegisterFrame() {
		init();
	}
	
	private void init() {
		setTitle("CADASTRO DE BENS");
		setLocationRelativeTo(null);
		setSize(795,390);
		
		register = new EntryPropertyRegister();
		values = new ArrayList<>();
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
		BTsave = new CustomIconButton(DesignIcon.add(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
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
		
		BTclear.setBounds(680,295,32,32);
		BTsave.setBounds(720,295,32,32);
		BTdelete.setBounds(640,295,32,32);
		
	}

	@Override
	public void initFormat() {
		Thread thread1 = new Thread(()-> {
			try {
				customers = new CustomerConnect().get();
				CBcustomer.addItem("");
				for(CustomerRegister r: customers) {
					CBcustomer.addItem(r.getName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		thread1.start();
		
		Thread thread2 = new Thread(()-> {
			try {
				propertys = new PropertyConnect().get();
				CBtypeProperty.addItem("");
				for(PropertyRegister r: propertys) {
					CBtypeProperty.addItem(r.getName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		thread2.start();
		
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
		BTaddValue.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				addValue();
			}
		});
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
		this.add(BTaddValue);
		this.add(BTsave);
		
		this.add(LBvalues);
		this.add(TBvalue);
	}

	private void getRegister() {
		try {
			if(CBcustomer.getSelectedIndex()<1) {
				Message.Warning("CLIENTE INVÁLIDO!",true);
				return;
			}
			if(CBtypeProperty.getSelectedIndex()<1) {
				Message.Warning("TIPO DE BEM INVÁLIDO!",true);
				return;
			}
			register.setName(TFname.getText());
			if(register.getName().equalsIgnoreCase("")) {
				Message.Warning("BEM INVÁLIDO!",true);
				return;
			}
			register.setBuyDate(DFbuy.getDate());
			if(register.getBuyDate()==null) {
				Message.Warning("DATA DE COMPRA INVÁLIDA!",true);
				return;
			}
			register.setSellDate(DFsell.getDate());
			register.setDescription(TAdescription.getText());		
			
		}catch(Exception e) {
			Message.Error(this.getClass().getName(),"getRegister", e);
		}
	}
	
	private void addValue() {
		try {
			LocalDate date = DFbuy.getDate();
			if(date==null) {
				Message.Warning("DATA INVÁLIDA!",true);
				return;
			}
			
			LocalDate today = LocalDate.now();
			int difference = today.getYear() - date.getYear() + 1;
			if(difference<1) {
				Message.Warning("SEM ANOS PARA INCLUIR!",true);
				return;
			}
			
			ArrayList<Integer> years = new ArrayList<Integer>();
			for(int i=0;i<difference;i++) {
				boolean found = false;
				for(EntryPropertyValueRegister v: values) {
					if(v.getYear()==date.getYear()+i) {
						found = true;
						break;
					}
				}
				if(!found) {
					years.add(date.getYear()+i);
				}
			}
			
			if(years.size()<1) {
				Message.Warning("TODOS OS ANOS DISPONÍVEIS JÁ FORAM INCLUÍDOS!",true);
				return;
			}
			
			new EntryValueRegisterFrame(this, years).setVisible(true);		
						
		}catch(Exception e) {
			Message.Error(this.getClass().getName(),"addValue", e);
		}
	}
	
	public void setValue(EntryPropertyValueRegister register) {
		values.add(register);
	}
	
}
