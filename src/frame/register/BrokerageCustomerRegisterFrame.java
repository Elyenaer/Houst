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
import model.connect.BrokerageCustomerConnect;
import model.connect.StockBrokerageConnect;
import model.register.BrokerageCustomerRegister;
import model.register.CustomerRegister;
import model.register.StockBrokerageRegister;
import setting.Design;
import support.LoadingDialog;
import support.Message;

public class BrokerageCustomerRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	
	private CustomerRegisterFrame frame;
	private CustomerRegister customerRegister;
	private ArrayList<StockBrokerageRegister> stockBrokerageRegisters;
	private BrokerageCustomerRegister register;
	private boolean isSaving = false;
	
	private CustomLabel LBstockBrokerage,LBcustomer,LBcode;
	private CustomComboBox CBstockBrokerage;
	private CustomTextField TFcode;	
	private CustomIconButton BTsave,BTdelete;
	
	public BrokerageCustomerRegisterFrame(CustomerRegisterFrame frame,CustomerRegister customerRegister,BrokerageCustomerRegister register) {
		this.frame = frame;
		this.customerRegister = customerRegister;
		this.register = register;
		setSize(400,240);
		setTitle("CADASTRO DE CLIENTE NA CORRETORA");
		setLocationRelativeTo(frame);
	}

	@Override
	public void init() {
		if(register!=null) {
			isSaving = false;
		}else {
			isSaving = true;
		}
		setRegister(register);
	}

	@Override
	public void initInitiation() {
		LBstockBrokerage = new CustomLabel("CORRETORA:");
		LBcustomer = new CustomLabel("060.236.879-06 - ELYENAER FARIAS DOS SANTOS");
		LBcode = new CustomLabel("CÓDIGO:");
		
		CBstockBrokerage = new CustomComboBox();
		TFcode = new CustomTextField();	
		
		BTdelete = new CustomIconButton(Design.delete(),32,32);
		if(!isSaving) {
			BTsave = new CustomIconButton(Design.save(),32,32);
		}else {
			BTsave = new CustomIconButton(Design.add(),32,32);
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
					if(isSaving) {
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
        for (int i = 0; i < stockBrokerageRegisters.size(); i++) {
            StockBrokerageRegister brokerage = stockBrokerageRegisters.get(i);
            if (brokerage.getId() == register.getStockBrokerageId()) {
                CBstockBrokerage.setSelectedIndex(i);
                break;
            }
        }
        TFcode.setText(register.getCode());
    }	
	
	private void clear() {
		
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
                            clear();
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
                            clear();
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
                            clear();
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
