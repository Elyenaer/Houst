package frame.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomRadioButton;
import components.CustomTextArea;
import components.CustomTextField;
import components.CustomToggleButton;
import model.register.register.MetricRegister;
import setting.desing.DesignIcon;
import support.Message;

public class MetricRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	
	private CustomLabel LBtitleImport,LBname,LBdescription,LBstatus;
	private CustomTextField TFtitleImport,TFname;
	private CustomTextArea TAdescription;
	private CustomToggleButton TBstatus;
	private CustomRadioButton RBtype;
	private CustomIconButton BTopen,BTsave,BTclear,BTdelete;	
	
	private boolean stateNewRegister = true;
	
	public MetricRegisterFrame() {
		init();
	}
	
	private void init() {
		this.setTitle("CADASTRO DE MÉTRICAS");
		this.setSize(360,415);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void initInitiation() {
		LBtitleImport = new CustomLabel("TÍTULO IMPORT:");
		LBname = new CustomLabel("NOME:");
		LBdescription = new CustomLabel("DESCRIÇÃO:");
		LBstatus = new CustomLabel("STATUS");
		
		TFtitleImport = new CustomTextField();
		TFname = new CustomTextField();
		
		TAdescription = new CustomTextArea();
		TBstatus = new CustomToggleButton("ATIVO","INATIVO");
		RBtype = new CustomRadioButton(110,80);
		
		BTopen = new CustomIconButton(DesignIcon.open(),32,32);
		BTsave = new CustomIconButton(DesignIcon.save(),32,32);
		BTclear = new CustomIconButton(DesignIcon.clear(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
	}

	@Override
	public void initPosition() {
		LBname.setBounds(30,30,50,25);
		TFname.setBounds(70,30,210,25);
		BTopen.setBounds(290,26,32,32);
		
		LBtitleImport.setBounds(30,70,100,25);		
		TFtitleImport.setBounds(120,70,200,25);
		
		LBdescription.setBounds(30,110,100,25);
		TAdescription.setBounds(30,135,290,60);
		
		RBtype.setBounds(30,210,110,80);
		
		LBstatus.setBounds(195,268,80,25);
		TBstatus.setBounds(245,270,70,20);
				
		BTsave.setBounds(285,320,32,32);
		BTclear.setBounds(205,320,32,32);
		BTdelete.setBounds(245,320,32,32);
	}

	@Override
	public void initFormat() {
		RBtype.setTitle("TIPO");
		RBtype.addButton("TEXTO");
		RBtype.addButton("DECIMAL");
		
		TBstatus.setSelected(true);//active
	}

	@Override
	public void initEvent() {
		BTsave.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					if(stateNewRegister){
						save();				
					}else {
						update();
					}
				}
			}
		);
		BTdelete.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					delete();
				}
			}
		);
		BTclear.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					clear();
				}
			}
		);
		BTopen.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					openTable();
				}
			}
		);
	}

	@Override
	public void initAdd() {
		this.add(LBtitleImport);
		this.add(LBname);
		this.add(LBdescription);
		this.add(LBstatus);
		
		this.add(TFtitleImport);
		this.add(TFname);
		
		this.add(TAdescription);
		this.add(TBstatus);
		this.add(RBtype);
		
		this.add(BTdelete);
		this.add(BTsave);
		this.add(BTopen);
		this.add(BTclear);
	}

	private void clear() {
		TFname.setText("");
		TFtitleImport.setText("");
		TAdescription.setText("");
		TBstatus.setSelected(true);
		RBtype.clear();
	}
	
	private MetricRegister getRegister() {
		MetricRegister register = new MetricRegister();
		register.setName(TFname.getText());
		if(register.getName().equalsIgnoreCase("")) {
			Message.Warning("NOME INVÁLIDO!",true);
			return null;
		}
		register.setTitleImport(TFtitleImport.getText());
		if(register.getTitleImport().equalsIgnoreCase("")) {
			Message.Warning("TÍTULO IMPORT INVÁLIDO!",true);
			return null;
		}
		String type = RBtype.getSelected();
		if(type==null) {
			Message.Warning("SELECIONE UM TIPO DE VARIÁVEL",true);
			return null;			
		}else {
			if(type.equalsIgnoreCase("TEXTO")) {
				register.setType('t');
			}else if(type.equalsIgnoreCase("DECIMAL")){
				register.setType('d');
			}
		}
		if(TBstatus.isSelected()) {
			register.setStatus('a');
		}else {
			register.setStatus('i');
		}
		return register;
	}
	
	private void save() {
		getRegister();
	}
	
	private void delete() {
		
	}
	
	private void update() {
		
	}
	
	private void openTable() {
		
	}
	
}
