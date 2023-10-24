package frame.stock.importing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import components.CustomButton;
import components.CustomDateField;
import components.CustomFrame;
import components.CustomLabel;
import components.CustomTable;
import components.LoadingDialog;
import components.LoadingProgressBar;
import frame.register.MetricRegisterFrame;
import model.register.connect.EntryMetricConnect;
import model.register.connect.MetricConnect;
import model.register.connect.StockConnect;
import model.register.register.MetricRegister;
import model.register.register.StockRegister;
import model.register.register.EntryMetricDecimalRegister;
import model.register.register.EntryMetricRegister;
import model.register.register.EntryMetricTextRegister;
import process.stockData.StockDataProcess;
import setting.desing.Design;
import setting.function.FunctionBigDecimal;
import setting.function.Message;

public class StockDataImportFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Object[]> imports;
	private ArrayList<MetricRegister> metrics;
	private ArrayList<StockRegister> stocks;
	private ArrayList<EntryMetricRegister> entries;
	private ArrayList<helpRegister> helpStock,helpMetric;
	
	private CustomTable TBimport;
	private CustomButton BTimport,BTsave,BTregisterMetric;
	private CustomLabel LBdate;
	private CustomDateField DFdate;
	
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
		BTsave = new CustomButton("ENVIAR DADOS");
		BTregisterMetric = new CustomButton("CADASTRAR MÉTRICAS");
		
		LBdate = new CustomLabel("DATA DOS DADOS:");
		DFdate = new CustomDateField();
	}

	@Override
	public void initPosition() {
		BTimport.setBounds(30,30,100,25);
		
		BTsave.setBounds(1010,610,150,25);
		BTregisterMetric.setBounds(800,610,200,25);
		
		LBdate.setBounds(985,33,100,25);
		DFdate.setBounds(1085,30,75,25);
	}

	@Override
	public void initFormat() {
		DFdate.setCurrentDate();
		
		BTregisterMetric.setVisible(false);
		BTsave.setVisible(false);
	}

	@Override
	public void initEvent() {
		BTimport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				importOpen();			
			}
		});
		BTsave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		BTregisterMetric.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				openMetricRegister();	
			}
		});
	}

	@Override
	public void initAdd() {
		this.add(BTimport);
		this.add(TBimport);		
		this.add(BTsave);
		this.add(BTregisterMetric);		
		this.add(LBdate);
		this.add(DFdate);
	}
	
	private void importOpen() {
		try {			
			LoadingDialog loadingDialog = new LoadingDialog(this,"CARREGANDO");
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
		        BTsave.setVisible(true);		        
	            loadingDialog.hideLoading();
	        });	        
	        loadingThread.start();    
		}catch(Exception e) {
			setting.function.Message.Error(this.getClass().getName(),"importBrokerage",e);
		}
	}
	
	private void importTable() {
        try {	
        	TBimport.setTitle(imports.get(0));
        	for(int i=0;i<imports.get(0).length;i++) {
        		TBimport.setColumnWidth(i,70);
        	}   
        	TBimport.setVisible(true);
        	TBimport.removeRows();
        	TBimport.setRowsRemoveFirst(imports);
        	TBimport.setBounds(30,80,1130,500);
        	checkStockRegister();	
        	checkMetrics();
        }catch (Exception e) {
        	Message.Error(this.getClass().getName(),"importTable", e);
		}	
	}
	
	//check if stock is registered in database
	public void checkStockRegister(){
		try {
			stocks = new StockConnect().getActive();
			helpStock = new ArrayList<helpRegister>();
			ArrayList<Integer> isOk = new ArrayList<Integer>();
			Color foreground = Color.green;
			for(StockRegister s: stocks) {
				for(int i=1;i<imports.size();i++) {
					if(s.getSymbol().equalsIgnoreCase(imports.get(i)[0].toString())) {
						helpStock.add(new helpRegister(s.getStockId(),i));
						isOk.add(i-1);//(-1) its to organize table position, because title is removed
						break;
					}
				}
			}
			
			if(isOk.size()<imports.size()-1) {
				BTregisterMetric.setVisible(true);
			}
			
			TBimport.setCellRenderer(isOk,0,null,foreground,true);
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"checkStockRegister", e);
		}		
	}
	
	//check if metric is registered in database
	public void checkMetrics() {
		try {
			metrics = new MetricConnect().getActive();
			helpMetric = new ArrayList<helpRegister>();
			ArrayList<Integer> isOk = new ArrayList<Integer>();
			Color background = Design.componentsBackgroundOk;	
			Color foreground = Design.componentsForegroundOk;
			for(MetricRegister m: metrics) {
				for(int i=0;i<imports.get(0).length;i++) {
					if(m.getTitleImport().equalsIgnoreCase(imports.get(0)[i].toString())) {
						helpMetric.add(new helpRegister(m.getMetricId(), i,m.getType()));
						isOk.add(i);
						break;
					}
				}
			}
			TBimport.setTitleRenderer(isOk, background,foreground,true);
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"checkMetrics", e);
		}
	}
		
	private boolean getRegister() {
		try {
			LocalDate date = DFdate.getDate();
			if(date==null) {
				Message.Warning("DATA INVÁLIDA!",true);
				return false;
			}	
			entries = new ArrayList<EntryMetricRegister>();			
			for(helpRegister hStocks: helpStock) {
				for(helpRegister hMetric: helpMetric) {
					if(hMetric.type=='d') {
						EntryMetricDecimalRegister register = new EntryMetricDecimalRegister();
						register.setDate(date);
						register.setMetricId(hMetric.registerId);
						register.setStockId(hStocks.registerId);						
						register.setValue(FunctionBigDecimal.stringToBigDecimal(imports.get(hStocks.position)[hMetric.position].toString()));	
						entries.add(register);
					}else if(hMetric.type=='t') {
						EntryMetricTextRegister register = new EntryMetricTextRegister();
						register.setDate(date);
						register.setMetricId(hMetric.registerId);
						register.setStockId(hStocks.registerId);
						register.setValue(imports.get(hStocks.position)[hMetric.position].toString());
						entries.add(register);
					}
				}
			}
			return true;
		}catch(Exception e) {
			Message.Error(this.getClass().getName(),"getRegister", e);
			return false;
		}
	}

	private void save() {		
		LoadingDialog loadingDialog = new LoadingDialog(this,"EXTRAINDO DADOS");			
        Thread loadingThread = new Thread(() -> {
        	try {
        		if(!getRegister()) {
					return;
				}     
	            loadingDialog.hideLoading();
	            
	            LoadingProgressBar loadingProgressBar = new LoadingProgressBar("ENVIANDO DADOS",entries.size(),this);
	            loadingProgressBar.showDialog(true);
	            EntryMetricConnect connect = new EntryMetricConnect();
	            for(int i=0;i<entries.size();i++) {
	            	loadingProgressBar.setValue(i);
	            	connect.post(entries.get(i));
	            }
	            loadingProgressBar.showDialog(false);
	            clear();
	            Message.Success("DADOS ENVIADOS COM SUCESSO!");
        	}catch (Exception e) {
    			Message.Error(this.getClass().getName(),"save", e);
    		}
        });	        
        loadingThread.start();  
	}

	private void clear() {
		BTregisterMetric.setVisible(false);
		BTsave.setVisible(false);
		DFdate.setCurrentDate();
		
		TBimport.removeRows();
		TBimport.setVisible(false);
	}
	
	private void openMetricRegister() {
		this.setEnabled(false);
		new MetricRegisterFrame(this).setVisible(true);
	}
}

class helpRegister{
	int registerId;
	int position;
	char type;
	
	public helpRegister(int stockId,int position) {
		this.registerId = stockId;
		this.position = position;
	}
	
	public helpRegister(int stockId,int position,char type) {
		this.registerId = stockId;
		this.position = position;
		this.type = type;
	}
}
