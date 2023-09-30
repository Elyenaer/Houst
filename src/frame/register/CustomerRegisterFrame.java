package frame.register;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTable;
import components.CustomTableRegister;
import components.CustomTextField;
import model.connect.BrokerageCustomerConnect;
import model.connect.CustomerConnect;
import model.connect.StockBrokerageConnect;
import model.register.BrokerageCustomerRegister;
import model.register.CustomerRegister;
import model.register.StockBrokerageRegister;
import setting.Design;
import setting.support.TextMask;
import support.LoadingDialog;
import support.Message;
import support.Valid;

public class CustomerRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	private CustomerRegister register;
	private ArrayList<BrokerageCustomerRegister> brokerageCustomerRegisters;
	
	private JTextField TFname,TFcpf;
	private JLabel LBname,LBcpf,LBbrokerageCustomer;
	private CustomIconButton BTsave,BTdelete,BTclear,BTbrokerageCustomerAdd,BTopen;
	private CustomTable TBbrokerageCustomer;
	
	private boolean  isSaving = true; //true is saving and false is updating
	
	public CustomerRegisterFrame() {
		super();
		setTitle("CADASTRO DE CLIENTE");
		this.setSize(new Dimension(415,465));
		
	}
	
	@Override
	public void init() {
		register = new CustomerRegister();		
	}
	
	@Override
	public void initInitiation(){
		TFname = new CustomTextField();
		TFcpf = new CustomTextField(TextMask.cpf);		
		
		LBname = new CustomLabel("NOME:");
		LBcpf = new CustomLabel("CPF:");
		
		BTopen = new CustomIconButton(Design.open(),32,32,"ABRIR CADASTROS");
		
		BTsave = new CustomIconButton(Design.add(),32,32,"SALVAR REGISTRO");
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
		BTdelete.setVisible(false);
	};
	
	@Override
	public void initEvent(){
		BTsave.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					if(isSaving) {
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
	
	protected void fillTable() {
		Thread loadingThread = new Thread(() -> {	        		        	
			try {
				
				/*
				 * 
				 * 
				 * 
				 * 
				 * 
				 * fazer o brokeragecustomer buscar por customer
				 * 
				 * 
				 * 
				 * 
				 */
				
				
				
				brokerageCustomerRegisters = new BrokerageCustomerConnect().get();
			} catch (IOException e) {
				e.printStackTrace();
			}		                
        });	        
        loadingThread.start(); 
	}
	
	private boolean getRegister() {		
		if(!Valid.validateCPF(TFcpf.getText())) {
			Message.Warning("CPF INVÁLIDO!",false);
			return false;
		}
		if(TFname.getText().equalsIgnoreCase("")) {
			Message.Warning("NOME INVÁLIDO!",false);
			return false;
		}
		register.setCpf(TFcpf.getText().replace(".","").replace("-",""));
		register.setName(TFname.getText());
		return true;
	}
	
	private void setRegister(CustomerRegister register) {
		this.register = register;
		TFcpf.setText(register.getCpf());
		TFname.setText(register.getName());
		isSaving = false;
		BTsave.changeIcon(Design.save());
		BTdelete.setVisible(true);
	}
	
	private void save() {
		try {			
			if(getRegister()) {				
				LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
		        Thread loadingThread = new Thread(() -> {	            
			        loadingDialog.showLoading();
			        try {			        	
						if(new CustomerConnect().post(register)>0) {
							loadingDialog.hideLoading(); 
							Message.Success("CLIENTE CADASTRADO COM SUCESSO!");
						}			
						clear();					    
			        }catch (Exception e) {
			        	Message.Error(this.getClass().getName(),"save", e);
					}			                
		        });	        
		        loadingThread.start(); 
			}		
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"save", e);
		}
	}
	
	private void update() {
		try {			
			if(getRegister()) {				
				LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
		        Thread loadingThread = new Thread(() -> {	            
			        loadingDialog.showLoading();
			        try {			        	
						if(new CustomerConnect().put(register)) {
							loadingDialog.hideLoading(); 
							Message.Success("CLIENTE ATUALIZADO COM SUCESSO!");
						}			
						clear();					    
			        }catch (Exception e) {
			        	Message.Error(this.getClass().getName(),"update", e);
					}			                
		        });	        
		        loadingThread.start(); 
			}		
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"update", e);
		}
	}
	
	private void delete() {
		try {				
			if(!Message.Options("CONFIRMA A EXCLUSÃO?")) {
				return;
			}			
			if(getRegister()) {				
				LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
		        Thread loadingThread = new Thread(() -> {	            
			        loadingDialog.showLoading();
			        try {			        	
						if(new CustomerConnect().delete(register)) {
							loadingDialog.hideLoading(); 
							Message.Success("CLIENTE EXCLUÍDO COM SUCESSO!");
						}			
						clear();					    
			        }catch (Exception e) {
			        	Message.Error(this.getClass().getName(),"delete", e);
					}			                
		        });	        
		        loadingThread.start(); 
			}		
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"delete", e);
		}
	}
	
	private void clear() {
		TFcpf.setText("");
		TFname.setText("");
		isSaving = true;
		BTsave.changeIcon(Design.add());
		BTdelete.setVisible(false);
	}
	
	private void openTable() {
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("ID");
		titles.add("NOME");
		titles.add("CPF");		
		CustomTableRegister table = new CustomTableRegister(this,"CLIENTES",titles);
		
		LoadingDialog loadingDialog = new LoadingDialog(this,"BUSCANDO REGISTROS");
        Thread loadingThread = new Thread(() -> {	            
	        loadingDialog.showLoading();
	        try {
	        	ArrayList<CustomerRegister> registers = new CustomerConnect().get();				
				ArrayList<Object[]> rows = new ArrayList<Object[]>();
		        for(CustomerRegister r: registers) {
					Object[] rowData = {
							r.getId(),
							r.getName(),
							r.getCpf()
					};
					rows.add(rowData);				
				}
		        table.setRows(rows);	
		        
		        loadingDialog.hideLoading();					
            
	            table.setVisible(true);		
	    		if(table.getSelected()!=null) {
	    			int id = Integer.parseInt(table.getSelected().toString());    			
	    			 Optional<CustomerRegister> foundCustomer = registers.stream()
	    			            .filter(customer -> customer.getId() == id)
	    			            .findFirst();
	    			setRegister(foundCustomer.get());
	    		}    		
	        }catch(Exception e) {
				Message.Error(this.getClass().getName(),"openTable", e);
			}            
        });	        
        loadingThread.start();
	}
	
}
