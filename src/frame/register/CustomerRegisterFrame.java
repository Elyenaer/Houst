package frame.register;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JTextField;

import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTable;
import components.CustomTableRegister;
import components.CustomTextField;
import frame.brokerage.importing.BrokerageReportImportFrame;
import model.register.connect.CustomerConnect;
import model.register.register.CustomerRegister;
import model.view.connect.BrokerageCustomerViewConnect;
import model.view.register.BrokerageCustomerView;
import setting.desing.DesignIcon;
import setting.support.TextMask;
import support.LoadingDialog;
import support.Message;
import support.Valid;

public class CustomerRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	
	private CustomerRegister customer;	
	private ArrayList<BrokerageCustomerView> brokerageCustomerRegisters;
	private BrokerageReportImportFrame frame;
	
	private String code; //come to before frame
	private int stockBrokerageId = 0; //come to before frame
	
	private JTextField TFname,TFcpf;
	private JLabel LBname,LBcpf,LBbrokerageCustomer;
	private CustomIconButton BTsave,BTdelete,BTclear,BTbrokerageCustomerAdd,BTopen;
	private CustomTable TBbrokerageCustomer;
	
	private boolean stateNewRegister = true; //true is saving and false is updating
	
	public CustomerRegisterFrame(CustomerRegister customer,String code,int stockBrokerageId,BrokerageReportImportFrame frame) {
		super();
		init(customer, code, stockBrokerageId, frame);
	}
	
	public void init(CustomerRegister register,String code,int stockBrokerageId,BrokerageReportImportFrame frame) {		
		this.frame = frame;
		this.stockBrokerageId = stockBrokerageId;
		this.code = code;
		frame.setEnabled(false);
		setTitle("CADASTRO DE CLIENTE");
		setRegister(register);	
		reOrganize();
		setLocationRelativeTo(frame);
	}
	
	@Override
	public void initInitiation(){
		TFname = new CustomTextField();
		TFcpf = new CustomTextField(TextMask.cpf);		
		
		LBname = new CustomLabel("NOME:");
		LBcpf = new CustomLabel("CPF:");
		
		BTopen = new CustomIconButton(DesignIcon.open(),32,32,"ABRIR CADASTROS");
		
		BTsave = new CustomIconButton(DesignIcon.add(),32,32,"SALVAR REGISTRO");
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32,"EXCLUIR REGISTRO");
		BTclear = new CustomIconButton(DesignIcon.clear(),32,32,"LIMPAR TELA");
		
		LBbrokerageCustomer = new CustomLabel("CADASTROS EM CORRETORAS");
		BTbrokerageCustomerAdd = new CustomIconButton(DesignIcon.add(),24,24,"INCLUIR UM REGISTRO DE CLIENTE NA CORRETORA");
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
		
		BTdelete.setBounds(260,370,32,32);
	}
	
	@Override
	public void initFormat(){
		TBbrokerageCustomer.setColumnWidth(1,100);		
	};
	
	@Override
	public void initEvent(){
		BTsave.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					if(stateNewRegister) {
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
		BTbrokerageCustomerAdd.addActionListener(
			new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					openBrokerageCustomer();
				}
			}
		);
		TBbrokerageCustomer.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                   
                	openBrokerageCustomer(brokerageCustomerRegisters.get(TBbrokerageCustomer.table.getSelectedRow()),null,0);
                	
                }
            }
        });
	}
	
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
				brokerageCustomerRegisters = new BrokerageCustomerViewConnect().getByCustomerId(customer.getId());
				ArrayList<Object[]> rows = new ArrayList<Object[]>();
				for(BrokerageCustomerView b: brokerageCustomerRegisters) {
					Object[] row = {						
						b.getStockBrokerageName(),
						b.getCode()
					};
					rows.add(row);
				}
				TBbrokerageCustomer.setRows(rows);
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
		customer.setCpf(TFcpf.getText().replace(".","").replace("-",""));
		customer.setName(TFname.getText());
		return true;
	}
	
	private void setRegister(CustomerRegister register) {
		this.customer = register;
		TFcpf.setText(customer.getCpf());
		TFname.setText(customer.getName());
		if(customer.getId()>0) {
			stateNewRegister = false;
			reOrganize();
			fillTable();
		}
	}

	private void clear() {
		customer = new CustomerRegister();
		TFcpf.setText("");
		TFname.setText("");
		stateNewRegister = true;
		reOrganize();
	}
	
	private void reOrganize() {
		if(stateNewRegister) {
			this.setSize(new Dimension(415,200));			
			BTsave.changeIcon(DesignIcon.add());			
			BTsave.setBounds(340,110,32,32);
			BTclear.setBounds(300,110,32,32);			
		}else {
			this.setSize(new Dimension(415,465));			
			BTsave.changeIcon(DesignIcon.save());
			BTsave.setBounds(340,370,32,32);
			BTclear.setBounds(300,370,32,32);			
		}		
		BTdelete.setVisible(!stateNewRegister);
		TBbrokerageCustomer.setVisible(!stateNewRegister);
		BTbrokerageCustomerAdd.setVisible(!stateNewRegister);
		LBbrokerageCustomer.setVisible(!stateNewRegister);
	}
	
	private void save() {
		try {			
			if(getRegister()) {				
				LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
		        Thread loadingThread = new Thread(() -> {	            
			        loadingDialog.showLoading();
			        try {	
			        	int id = new CustomerConnect().post(customer);
						if(id>0) {
							loadingDialog.hideLoading(); 
							Message.Success("CLIENTE CADASTRADO COM SUCESSO!");
							customer.setId(id);
							setRegister(customer);
						}								    
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
						if(new CustomerConnect().put(customer)) {
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
						if(new CustomerConnect().delete(customer)) {
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
	
	private void openBrokerageCustomer() {
		if(code!=null && stockBrokerageId>0) {
			openBrokerageCustomer(null,code,stockBrokerageId);
			code = null;
			stockBrokerageId = 0;
		}else {
			openBrokerageCustomer(null,null,0);
		}
	}
	
	private void openBrokerageCustomer(BrokerageCustomerView brokerageCustomerRegister,String code,int stockBrokerageId) {
		Thread loadingThread = new Thread(() -> {	 
	        try {
	        	new BrokerageCustomerRegisterFrame(this,customer,brokerageCustomerRegister,code,stockBrokerageId).setVisible(true);
	        	this.setEnabled(false);
	        }catch(Exception e) {
				Message.Error(this.getClass().getName(),"openBrokerageCustomer", e);
			}            
        });	        
        loadingThread.start();
	}

	@Override
	public void closeScreen() {
		this.frame.setEnabled(true);
		this.frame.checkPanels();
		this.dispose();
	}
		
}
