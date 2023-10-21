package frame.register;

import java.util.ArrayList;

import components.CustomComboBox;
import components.CustomCurrencyField;
import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import model.register.register.EntryPropertyValueRegister;
import setting.desing.DesignIcon;
import support.Message;

public class EntryValueRegisterFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	private PropertyRegisterFrame frame;
	private ArrayList<Integer> yearsAvailable;
	private EntryPropertyValueRegister register;
	
	private CustomLabel LByear,Lbvalue;
	private CustomComboBox CByear;
	private CustomCurrencyField TFvalue;
	private CustomIconButton BTsave,BTdelete;
	
	public EntryValueRegisterFrame(PropertyRegisterFrame frame,ArrayList<Integer> yearsAvailable) {
		this.frame = frame;
		this.yearsAvailable = yearsAvailable;
		init();
	}
	
	private void init() {
		this.setTitle("VALORES");
		this.setSize(250,190);
		this.setLocationRelativeTo(frame);
		setYear();
	}
	
	private void setYear() {
		CByear.addItem("");
		for(Integer i: yearsAvailable) {
			CByear.addItem(i+"");
		}
	}

	@Override
	public void initInitiation() {
		LByear = new CustomLabel("ANO:");
		Lbvalue = new CustomLabel("VALOR:");
		
		CByear = new CustomComboBox();
		TFvalue = new CustomCurrencyField();
		
		BTsave = new CustomIconButton(DesignIcon.add(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
	}

	@Override
	public void initPosition() {		
		LByear.setBounds(30,30,100,25);
		CByear.setBounds(100,30,100,25);
		
		Lbvalue.setBounds(30,60,100,25);
		TFvalue.setBounds(100,60,100,25);
		
		BTsave.setBounds(170,100,32,32);
		BTdelete.setBounds(130,100,32,32);
	}

	@Override
	public void initFormat() {
		
	}

	@Override
	public void initEvent() {
		
	}

	@Override
	public void initAdd() {
		this.add(LByear);
		this.add(Lbvalue);
		
		this.add(CByear);
		this.add(TFvalue);
		
		this.add(BTsave);
		this.add(BTdelete);
	}

	private boolean getRegister() {
		if(CByear.getSelectedIndex()<1) {
			Message.Warning("ANO INVÁLIDO!",true);
			return false;
		}
		if(TFvalue.getValue().doubleValue()<=0) {
			Message.Warning("VALOR INVÁLIDO!",true);
			return false;
		}
		
		
		
		return true;
	}
}
