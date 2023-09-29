package frame.customer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTable;
import components.CustomTextField;
import model.connect.CustomerConnect;
import model.register.CustomerRegister;
import setting.Design;
import setting.support.TextMask;
import support.Message;
import support.Valid;

public class CustomerRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	private CustomerRegister register;
	
	private JTextField TFname,TFcpf;
	private JLabel LBname,LBcpf,LBbrokerageCustomer;
	private CustomIconButton BTsave,BTdelete,BTclear,BTbrokerageCustomerAdd,BTopen;
	private CustomTable TBbrokerageCustomer;
	
	public CustomerRegisterFrame() {
		super();
		setTitle("CADASTRO DE CLIENTE");
		this.setSize(new Dimension(415,465));
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void initInitiation(){
		TFname = new CustomTextField();
		TFcpf = new CustomTextField(TextMask.cpf);		
		
		LBname = new CustomLabel("NOME:");
		LBcpf = new CustomLabel("CPF:");
		
		BTopen = new CustomIconButton(Design.open(),32,32,"ABRIR CADASTROS");
		
		BTsave = new CustomIconButton(Design.save(),32,32,"SALVAR REGISTRO");
		BTdelete = new CustomIconButton(Design.delete(),32,32,"EXCLUIR REGISTRO");
		BTclear = new CustomIconButton(Design.clear(),32,32,"LIMPAR TELA");
		
		LBbrokerageCustomer = new CustomLabel("CADASTROS EM CORRETORAS");
		BTbrokerageCustomerAdd = new CustomIconButton(Design.add(),24,24,"INCLUIR UM REGISTRO DE CLIENTE NA CORRETORA");
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("CORRETORA");
		titles.add("CÓDIGO");
		TBbrokerageCustomer = new CustomTable(titles);
	};
	
	@Override
	public void initPosition(){
		TFname.setBounds(70,70,300,25);
		TFcpf.setBounds(60,30,100,25);
		
		LBname.setBounds(30,70,200,25);
		LBcpf.setBounds(30,30,200,25);
		
		BTopen.setBounds(339,28,32,32);
		
		LBbrokerageCustomer.setBounds(30,120,200,25);
		BTbrokerageCustomerAdd.setBounds(345,120,24,24);
		TBbrokerageCustomer.setBounds(30,150,338,200);
		
		BTsave.setBounds(340,370,32,32);
		BTdelete.setBounds(260,370,32,32);
		BTclear.setBounds(300,370,32,32);	
	};
	
	@Override
	public void initFormat(){
		TBbrokerageCustomer.setColumnWidth(1,100);
		DefaultTableModel dtm = (DefaultTableModel) TBbrokerageCustomer.table.getModel();
		dtm.setRowCount(0);
		for(int i= 0;i<20;i++) {
			Object[] rowData = {
		            "test",
		            "test",
		        };
			dtm.addRow(rowData);
		}
	};
	
	@Override
	public void initEvent(){
		BTsave.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					save();
				}
			}
		);
		BTdelete.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			}
		);
		BTclear.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			}
		);
	};
	
	@Override
	public void initAdd(){
		this.add(BTclear);
		this.add(BTopen);
		this.add(TFname);
		this.add(TFcpf);
		this.add(LBname);
		this.add(LBcpf);
		this.add(BTsave);
		this.add(BTdelete);		
		
		this.add(LBbrokerageCustomer);
		this.add(BTbrokerageCustomerAdd);
		this.add(TBbrokerageCustomer);
	};
	
	private boolean getRegister() {
		register = new CustomerRegister();
		if(!Valid.validateCPF(TFcpf.getText())) {
			int r = 0;
			Message.Warning(this,"CPF INVÁLIDO!",false);
			return false;
		}
		if(TFname.getText().equalsIgnoreCase("")) {
			Message.Warning(this,"NOME INVÁLIDO!",false);
			return false;
		}
		register.setCpf(TFcpf.getText().replace(".","").replace("-",""));
		register.setName(TFname.getText());
		return true;
	}
	
	private void save() {
		try {
			if(getRegister()) {
				new CustomerConnect().post(register);
				Message.Success("CLIENTE CADASTRADO COM SUCESSO!",false);
			}		
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"save", e);
		}
	}
	
	private void clear() {
		TFcpf.setText("");
		TFname.setText("");
	}
	
}
