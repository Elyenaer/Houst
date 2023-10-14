package frame.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import components.CustomComboBox;
import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTextField;
import components.LoadingDialog;
import model.register.connect.BrokerageCustomerConnect;
import model.register.connect.StockBrokerageConnect;
import model.register.register.BrokerageCustomerRegister;
import model.register.register.CustomerRegister;
import model.register.register.StockBrokerageRegister;
import model.view.register.BrokerageCustomerView;
import setting.desing.DesignIcon;
import support.Message;

public class BrokerageCustomerRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	
	private CustomerRegisterFrame frame;
	private CustomerRegister customerRegister;
	private ArrayList<StockBrokerageRegister> stockBrokerageRegisters;
	private BrokerageCustomerRegister register;
	private boolean stateNewRegister = false;
	
	private String code; //come to before frame
	private int stockBrokerageId = 0; //come to before frame
	
	private CustomLabel LBstockBrokerage,LBcustomer,LBcode;
	private CustomComboBox CBstockBrokerage;
	private CustomTextField TFcode;	
	private CustomIconButton BTsave,BTdelete;
	
	public BrokerageCustomerRegisterFrame(CustomerRegisterFrame frame,CustomerRegister customerRegister,BrokerageCustomerView register,String code,int stockBrokerageId) {
		this.frame = frame;
		this.customerRegister = customerRegister;	
		this.code = code;
		this.stockBrokerageId = stockBrokerageId;
		init(register);		
	}

	public void init(BrokerageCustomerView register) {
		setSize(400,240);
		setTitle("CADASTRO DE CLIENTE NA CORRETORA");
		setLocationRelativeTo(frame);
		LBcustomer.setText(customerRegister.getCpf() + " - " + customerRegister.getName());
		if(register!=null) {
			this.register  = new BrokerageCustomerRegister();
			this.register.setBrokerageCustomerId(register.getBrokerageCustomerId());
			this.register.setCode(register.getCode());
			this.register.setCustomerId(register.getCustomerId());
			this.register.setStockBrokerageId(register.getStockBrokerageId());
			stateNewRegister = false;
			setRegister(this.register);
		}else {
			this.register = new BrokerageCustomerRegister();
			stateNewRegister = true;					
		}		
	}

	@Override
	public void initInitiation() {
		LBstockBrokerage = new CustomLabel("CORRETORA:");
		LBcustomer = new CustomLabel("");
		LBcode = new CustomLabel("CÃ“DIGO:");
		
		CBstockBrokerage = new CustomComboBox();
		TFcode = new CustomTextField();	
		
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
		if(!stateNewRegister) {
			BTsave = new CustomIconButton(DesignIcon.save(),32,32);
		}else {
			BTsave = new CustomIconButton(DesignIcon.add(),32,32);
		}		
	}

	@Override
	public void initPosition() {
		LBcustomer.setBounds(30,30,440,25);
		LBstockBrokerage.setBounds(30,65,150,25);		
		LBcode.setBounds(30,100,150,25);
		
		CBstockBrokerage.setBounds(105,65,250,25);
		TFcode.setBounds(80,100,275,25);
		
		BTdelete.setBounds(285,140,32,32);
		BTsave.setBounds(325,140,32,32);
	}

	@Override
	public void initFormat() {
		Thread loadingThread = new Thread(() -> {	        		        	
			try {
				stockBrokerageRegisters =  new StockBrokerageConnect().get();
				for(StockBrokerageRegister s: stockBrokerageRegisters) {
					CBstockBrokerage.addItem(s.getName());
				}
				if(code!=null && stockBrokerageId>0) {
					for (int i = 0; i < stockBrokerageRegisters.size(); i++) {
					    StockBrokerageRegister brokerage = stockBrokerageRegisters.get(i);
					    if (brokerage.getId() == stockBrokerageId) {
					        CBstockBrokerage.setSelectedIndex(i);
					        break;
					    }
					}	
					TFcode.setText(code);
				}	
			} catch (IOException e) {
				e.printStackTrace();
			}		                
        });	        
        loadingThread.start(); 
	}

	@Override
	public void initEvent() {
		BTdelete.addActionListener(
			new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					delete();					
				}
			}			
		);
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
	}

	@Override
	public void initAdd() {
		this.add(LBstockBrokerage);
		this.add(LBcustomer);
		this.add(LBcode);		
		this.add(CBstockBrokerage);
		this.add(TFcode);
		this.add(BTdelete);
		this.add(BTsave);
	}
	
    private boolean getRegister() {
        if (CBstockBrokerage.getSelectedIndex() == -1) {
            Message.Warning("SELECIONE UMA CORRETORA!", false);
            return false;
        }
        if (TFcode.getText().equalsIgnoreCase("")) {
            Message.Warning("CÓDIGO INVÁLIDO!", false);
            return false;
        }
        StockBrokerageRegister selectedBrokerage = stockBrokerageRegisters.get(CBstockBrokerage.getSelectedIndex());
        register.setCustomerId(customerRegister.getId());
        register.setStockBrokerageId(selectedBrokerage.getId());
        register.setCode(TFcode.getText());
        return true;
    }

    private void setRegister(BrokerageCustomerRegister register) {
        this.register = register;        
        TFcode.setText(register.getCode());        
        Thread loadingThread = new Thread(() -> {        	
        	while(stockBrokerageRegisters==null) {
        		try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
			for (int i = 0; i < stockBrokerageRegisters.size(); i++) {
			    StockBrokerageRegister brokerage = stockBrokerageRegisters.get(i);
			    if (brokerage.getId() == register.getStockBrokerageId()) {
			        CBstockBrokerage.setSelectedIndex(i);
			        break;
			    }
			}		                
        });	        
        loadingThread.start(); 
    }	
	
    @Override
	public void closeScreen() {
		frame.fillTable();
		frame.setEnabled(true);		
		this.dispose();
	}
	
	private void save() {
        try {
            if (getRegister()) {
                LoadingDialog loadingDialog = new LoadingDialog(this, "SALVANDO");
                Thread loadingThread = new Thread(() -> {
                    loadingDialog.showLoading();
                    try {
                        if (new BrokerageCustomerConnect().post(register) > 0) {
                            loadingDialog.hideLoading();
                            Message.Success("CLIENTE NA CORRETORA CADASTRADO COM SUCESSO!");
                            closeScreen();
                        }
                    } catch (Exception e) {
                        Message.Error(this.getClass().getName(), "save", e);
                    }
                });
                loadingThread.start();
            }
        } catch (Exception e) {
            Message.Error(this.getClass().getName(), "save", e);
        }
    }

    private void delete() {
        try {
            if (!Message.Options("CONFIRMA A EXCLUSÃO?")) {
                return;
            }
            if (getRegister()) {
                LoadingDialog loadingDialog = new LoadingDialog(this, "EXCLUINDO");
                Thread loadingThread = new Thread(() -> {
                    loadingDialog.showLoading();
                    try {
                        if (new BrokerageCustomerConnect().delete(register)) {
                            loadingDialog.hideLoading();
                            Message.Success("CLIENTE NA CORRETORA EXCLUÍDO COM SUCESSO!");
                            closeScreen();
                        }
                    } catch (Exception e) {
                        Message.Error(this.getClass().getName(), "delete", e);
                    }
                });
                loadingThread.start();
            }
        } catch (Exception e) {
            Message.Error(this.getClass().getName(), "delete", e);
        }
    }

    private void update() {
        try {
            if (getRegister()) {
                LoadingDialog loadingDialog = new LoadingDialog(this, "ATUALIZANDO");
                Thread loadingThread = new Thread(() -> {
                    loadingDialog.showLoading();
                    try {
                        if (new BrokerageCustomerConnect().put(register)) {
                            loadingDialog.hideLoading();
                            Message.Success("CLIENTE NA CORRETORA ATUALIZADO COM SUCESSO!");
                            closeScreen();
                        }
                    } catch (Exception e) {
                        Message.Error(this.getClass().getName(), "update", e);
                    }
                });
                loadingThread.start();
            }
        } catch (Exception e) {
            Message.Error(this.getClass().getName(), "update", e);
        }
    }
       
}
