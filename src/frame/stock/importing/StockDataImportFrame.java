package frame.stock.importing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.CustomButton;
import components.CustomFrame;
import components.CustomTable;
import components.LoadingDialog;
import model.register.connect.StockConnect;
import model.register.register.StockRegister;
import process.stockData.StockDataProcess;
import support.Message;

public class StockDataImportFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	private ArrayList<Object[]> imports;
	
	private CustomTable TBimport;
	private CustomButton BTimport;
	
	public StockDataImportFrame() {
		super();
		init();
	}
	
	private void init() {		
		setTitle("MÉTRICAS IMPORT");
		setSize(1200,700);
		setLocationRelativeTo(null);
	}

	@Override
	public void initInitiation() {
		TBimport = new CustomTable();
		BTimport = new CustomButton("IMPORT");
	}

	@Override
	public void initPosition() {
		BTimport.setBounds(30,30,100,25);
	}

	@Override
	public void initFormat() {
		
	}

	@Override
	public void initEvent() {
		BTimport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				importOpen();			
			}
		});
	}

	@Override
	public void initAdd() {
		this.add(BTimport);
		this.add(TBimport);
	}
	
	private void importOpen() {
		try {
			LoadingDialog loadingDialog = new LoadingDialog(this);
	        Thread loadingThread = new Thread(() -> {	            
	            JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setMultiSelectionEnabled(false);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos CSV", "csv");
		        fileChooser.setFileFilter(filter);
		        int result = fileChooser.showOpenDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		        	loadingDialog.showLoading();
		            File selectedFiles = fileChooser.getSelectedFile();                  
		            imports = new StockDataProcess().get(selectedFiles.getAbsolutePath());
		        }
		        importTable();
	            loadingDialog.hideLoading();
	        });	        
	        loadingThread.start();    
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"importBrokerage",e);
		}
	}
	
	private void importTable() {
		LoadingDialog loadingDialog = new LoadingDialog(this,"SALVANDO");
        Thread loadingThread = new Thread(() -> {	            
	        loadingDialog.showLoading();
	        this.setEnabled(false);
	        try {	
	        	TBimport.setTitle(imports.get(0));
	        	for(int i=0;i<imports.get(0).length;i++) {
	        		TBimport.setColumnWidth(i,70);
	        	}
	        	TBimport.setBounds(30,80,1130,500);
	        	TBimport.setRowsRemoveFirst(imports);
	        	checkStockRegister();	        	
	        }catch (Exception e) {
	        	Message.Error(this.getClass().getName(),"importTable", e);
			}	
	        this.setEnabled(true);
	        loadingDialog.hideLoading();
        });	        
        loadingThread.start(); 
	}
	
	//check if stock is registered in database
	private void checkStockRegister(){
		try {
			ArrayList<StockRegister> stocks = new StockConnect().getActive();
			ArrayList<Integer> isOk = new ArrayList<Integer>();
			Color foreground = Color.green;
			for(StockRegister s: stocks) {
				for(int i=1;i<imports.size();i++) {
					if(s.getSymbol().equalsIgnoreCase(imports.get(i)[0].toString())) {
						isOk.add(i);
						break;
					}
				}
			}
			TBimport.setCellRenderer(isOk,0,null,foreground,true);
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"checkStockRegister", e);
		}		
	}
	
}
