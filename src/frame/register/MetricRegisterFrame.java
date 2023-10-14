package frame.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JFrame;

import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomRadioButton;
import components.CustomTableRegister;
import components.CustomTextArea;
import components.CustomTextField;
import components.CustomToggleButton;
import components.LoadingDialog;
import frame.stock.importing.StockDataImportFrame;
import model.register.connect.MetricConnect;
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
	private MetricRegister register;
	
	private StockDataImportFrame stockFrame;
	
	public MetricRegisterFrame(JFrame frame) {
		init(frame);
	}
	
	public MetricRegisterFrame(StockDataImportFrame frame) {
		this.stockFrame = frame;
		init(frame);
	}
	
	private void init(JFrame frame) {
		this.setTitle("CADASTRO DE MÉTRICAS");
		this.setSize(360,415);
		this.setLocationRelativeTo(frame);
		register = new MetricRegister();
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
		BTsave = new CustomIconButton(DesignIcon.add(),32,32);
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
		BTclear.setBounds(245,320,32,32);
		BTdelete.setBounds(205,320,32,32);
	}

	@Override
	public void initFormat() {
		RBtype.setTitle("TIPO");
		RBtype.addButton("TEXTO");
		RBtype.addButton("DECIMAL");
		
		BTdelete.setVisible(false);
		
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
		stateNewRegister = true;
		
		BTdelete.setVisible(false);
		BTsave.setScaleIcon(DesignIcon.add());
	}
	
	private boolean getRegister() {
		register.setName(TFname.getText());
		register.setDescription(TAdescription.getText());
		if(register.getName().equalsIgnoreCase("")) {
			Message.Warning("NOME INVÁLIDO!",true);
			return false;
		}
		register.setTitleImport(TFtitleImport.getText());
		if(register.getTitleImport().equalsIgnoreCase("")) {
			Message.Warning("TÍTULO IMPORT INVÁLIDO!",true);
			return false;
		}
		String type = RBtype.getSelected();
		if(type==null) {
			Message.Warning("SELECIONE UM TIPO DE VARIÁVEL",true);
			return false;			
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
		return true;
	}
	
	private void setRegister(MetricRegister register) {
		this.register = register;
		
		TFname.setText(register.getName());
		TFtitleImport.setText(register.getTitleImport());
		TAdescription.setText(register.getDescription());
		
		if(register.getStatus()=='a') {
			TBstatus.setSelected(true);
		}else {
			TBstatus.setSelected(false);
		}
		
		if(register.getType()=='t') {
			RBtype.setSelectedByText("TEXTO");
		}else if(register.getType()=='d') {
			RBtype.setSelectedByText("DECIMAL");
		}
		
		stateNewRegister = false;
		
		BTdelete.setVisible(true);
		BTsave.setScaleIcon(DesignIcon.save());
	}
	
	private void save() {
		try {			
			if(getRegister()) {				
				LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
		        Thread loadingThread = new Thread(() -> {	            
			        loadingDialog.showLoading();
			        this.setEnabled(false);
			        try {	
			        	new MetricConnect().post(register);	
			        	Message.Success("MÉTRICA SALVA COM SUCESSO!");
			        	clear();
			        }catch (Exception e) {
			        	Message.Error(this.getClass().getName(),"save", e);
					}	
			        this.setEnabled(true);
			        loadingDialog.hideLoading();
		        });	        
		        loadingThread.start(); 
			}		
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"save", e);
		}
	}
	
	private void delete() {
		try {				
			if(!Message.Options("CONFIRMA A EXCLUSÃO DA MÉTRICA " + register.getName() + "?")) {
				return;
			}					
			LoadingDialog loadingDialog = new LoadingDialog(this,"EXCLUINDO");
	        Thread loadingThread = new Thread(() -> {	            
		        loadingDialog.showLoading();
		        this.setEnabled(false);
		        try {			        	
					if(new MetricConnect().delete(register)) {							
						Message.Success("CLIENTE EXCLUÍDO COM SUCESSO!");
						clear();
					}						    
		        }catch (Exception e) {
		        	Message.Error(this.getClass().getName(),"delete", e);
				}	
		        this.setEnabled(true);
		        loadingDialog.hideLoading(); 
	        });	        
	        loadingThread.start();		
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"delete", e);
		}
	}
	
	private void update() {
		try {			
			if(getRegister()) {				
				LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
		        Thread loadingThread = new Thread(() -> {	            
			        loadingDialog.showLoading();
			        try {			        	
						if(new MetricConnect().put(register)) {
							loadingDialog.hideLoading(); 
							Message.Success("MÉTRICA ATUALIZADA COM SUCESSO!");
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
	
	private void openTable() {
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("ID");
		titles.add("NOME");
		titles.add("TÍTULO IMPORT");		
		titles.add("STATUS");	
		CustomTableRegister table = new CustomTableRegister(this,"MÉTRICAS",titles,500,400);
		
		LoadingDialog loadingDialog = new LoadingDialog(this,"BUSCANDO REGISTROS");
        Thread loadingThread = new Thread(() -> {	            
	        loadingDialog.showLoading();
	        try {
	        	ArrayList<MetricRegister> registers = new MetricConnect().get();				
				ArrayList<Object[]> rows = new ArrayList<Object[]>();
		        for(MetricRegister r: registers) {
					Object[] rowData = {
							r.getMetricId(),
							r.getName(),
							r.getTitleImport(),
							r.getStatus()=='a' ? "ATIVO" : "INATIVO"
					};
					rows.add(rowData);				
				}
		        table.setRows(rows);	
		        
		        loadingDialog.hideLoading();					
            
	            table.setVisible(true);		
	    		if(table.getSelected()!=null) {
	    			int id = Integer.parseInt(table.getSelected().toString());    			
	    			 Optional<MetricRegister> foundCustomer = registers.stream()
	    			            .filter(customer -> customer.getMetricId() == id)
	    			            .findFirst();
	    			setRegister(foundCustomer.get());
	    		}    		
	        }catch(Exception e) {
				Message.Error(this.getClass().getName(),"openTable", e);
			}            
        });	        
        loadingThread.start();
	}
	
	@Override
	public void closeScreen() {
		if(stockFrame!=null) {
			stockFrame.setEnabled(true);
			stockFrame.checkMetrics();		
		}
		
		dispose();
	}
}
