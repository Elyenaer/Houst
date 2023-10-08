package frame.brokerage.importing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import components.CustomButton;
import components.CustomFrame;
import components.CustomTable;
import components.LoadingDialog;
import components.LoadingProgressBar;
import model.register.connect.BrokerageReportConnect;
import model.register.connect.TitleConnect;
import model.register.register.TitleRegister;
import model.view.register.BrokerageReportView;
import process.brokerageReport.BrokerageReportProcess;
import setting.desing.Design;
import setting.desing.DesignIcon;
import support.FunctionBigDecimal;
import support.Message;

public class BrokerageReportImportFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	protected ArrayList<BrokerageReportBriefing> registers;
	
	private ArrayList<helpCheck> checkInvoice; //help remove duplicate invoice
	private ArrayList<String> removeInvoice; //show invoices removed
	
	private JPanel PNbrokerage;
	private JScrollPane SPbrokerage;
	
	private CustomTable TBstock;	
	
	private JButton BTimport,BTsave,BTdeleteDuplicate;
	
	private TitlePanel PNtitlePanel;
	private BusinessBriefingPanel PNbusinessBriefingPanel;
	private ClearingPanel PNclearingPanel;	
	private BrokerageExpensesPanel PNbrokerageExpensesPanel;
	private StockPanel PNstockPanel;
	
	public BrokerageReportImportFrame() {
		this.setTitle("NOTA DE CORRETAGEM IMPORT");
		this.setSize(new Dimension(1280,730));
        this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		 getContentPane().setBackground(Design.mainBackground);
		init();
        windowsClosing();   
	}
	
	public void init() {
		registers = new ArrayList<BrokerageReportBriefing>();
		checkInvoice = new ArrayList<helpCheck>();		
	}	
	
	@Override
	public void initInitiation() {
		PNbrokerage = new JPanel();
		SPbrokerage = new JScrollPane();
		SPbrokerage.add(PNbrokerage);
		
		ArrayList<String> t = new ArrayList<String>();
		t.add("NEGOCIAÇÃO");
		t.add("C/V");
		t.add("TIPO MERC.");
		t.add("ESPECIFICAÇÃO DO TÍTULO");
		t.add("QD");
		t.add("PREÇO");
		t.add("OPERAÇÃO");
		t.add("D/C");
		TBstock = new CustomTable(t);
		
		BTimport = new CustomButton("IMPORT");
		BTsave = new CustomButton("SALVAR");
		BTdeleteDuplicate = new CustomButton("EXCLUIR DUPLICADOS!");
		
		PNtitlePanel = new TitlePanel(500,200,10);
		PNbusinessBriefingPanel = new BusinessBriefingPanel(325,185,9);
		PNclearingPanel = new ClearingPanel(325,100,9);		
		PNstockPanel = new StockPanel(325,100,9);
		PNbrokerageExpensesPanel = new BrokerageExpensesPanel(325,185,9);
	}
	
	@Override
	public void initPosition() {
		BTimport.setBounds(30,30,200,25);
		BTsave.setBounds(1140,650,100,25);	
		BTdeleteDuplicate.setBounds(930,650,200,25);
				
		PNbrokerage.setLayout(null);
		SPbrokerage.setViewportView(PNbrokerage);
		SPbrokerage.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		SPbrokerage.setBounds(30,70,200,600);
		
		TBstock.setBounds(255,150,650,465);
		
		PNtitlePanel.setBounds(255,30,650,110);
		PNbusinessBriefingPanel.setLocation(915,30);
		PNclearingPanel.setLocation(915,220);
		PNstockPanel.setLocation(915,325);
		PNbrokerageExpensesPanel.setLocation(915,430);		
	}
	
	@Override
	public void initFormat() {
		PNbrokerage.setBackground(Design.mainBackground);	
		TBstock.setColumnWidth(0,80);
		TBstock.setColumnWidth(1,30);
		TBstock.setColumnWidth(2,80);
		TBstock.setColumnWidth(4,40);
		TBstock.setColumnWidth(5,80);
		TBstock.setColumnWidth(6,90);
		TBstock.setColumnWidth(7,30);	
		BTdeleteDuplicate.setIcon(DesignIcon.error16x16());
		BTdeleteDuplicate.setVisible(false);
	}
	
	@Override
	public void initEvent() {
		BTimport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				importBrokerage();
			}
		});
		BTsave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		BTdeleteDuplicate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteDuplicate();
			}
		});
	}
	
	@Override
	public void initAdd() {
		this.add(BTimport);
		this.add(BTsave);
		this.add(BTdeleteDuplicate);
		
		this.add(SPbrokerage);
		this.add(TBstock);
		
		this.add(PNtitlePanel);
		this.add(PNbusinessBriefingPanel);
		this.add(PNclearingPanel);
		this.add(PNstockPanel);
		this.add(PNbrokerageExpensesPanel);
	}
	
	public void setBtDeleteDuplicatesVisible() {
		BTdeleteDuplicate.setVisible(true);
	}
	
	private void deleteDuplicate() {
		for(BrokerageReportBriefing b: registers) {
			b.deleteInvoice();
		}
		BTdeleteDuplicate.setVisible(false);
	}
	
	private void importBrokerage() {
		try {		
			removeInvoice = new ArrayList<String>();
			LoadingDialog loadingDialog = new LoadingDialog(this);
	        Thread loadingThread = new Thread(() -> {	            
	            JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setMultiSelectionEnabled(true);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PDF", "pdf");
		        fileChooser.setFileFilter(filter);
		        int result = fileChooser.showOpenDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		        	loadingDialog.showLoading();
		            File[] selectedFiles = fileChooser.getSelectedFiles();	            
		            BrokerageReportProcess process = new BrokerageReportProcess();	            
		            for (File file : selectedFiles) {
		            	ArrayList<BrokerageReportView> r = process.get(file.getAbsolutePath());		            	
		            	if(r!=null) {
		            		for(BrokerageReportView t: r) {	
			            		String remove = "";
			            		for(helpCheck h: checkInvoice) {
			            			if(h.invoiceNumber.equalsIgnoreCase(t.getBrokerageReportRegister().getInvoiceNumber()) && h.stockBrokerage == t.getStockBrokerageRegister().getId()) {
			            		 		remove = h.invoiceNumber;
			            				break;
			            			}
			            		}
			            		if(remove=="") {
			            			registers.add(new BrokerageReportBriefing(t,this));
			            			
			            			helpCheck helpCheck = new helpCheck();
			            			helpCheck.invoiceNumber = t.getBrokerageReportRegister().getInvoiceNumber();
			            			helpCheck.stockBrokerage = t.getStockBrokerageRegister().getId();
			            			
			            			checkInvoice.add(helpCheck);
			            		}else {
			            			removeInvoice.add(remove);
			            		}		            		
			            	}
		            	}		            	
		            }
		        }
		        setPanels();
	            loadingDialog.hideLoading();
	            if(removeInvoice.size()>0) {
	            	String msg = "NOTAS DUPLICADAS:";
	            	for(String s: removeInvoice) {
	            		msg = msg + " " + s;
	            	}
	            	Message.Information(msg);
	            }
	        });	        
	        loadingThread.start();    
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"importBrokerage",e);
		}
	}
	
	private void setPanels() {
		try {	
			int width = 175;
	        int height = 100;
	        int totalHeight = height * registers.size() + registers.size() * 10;	
	        PNbrokerage.removeAll();
	        PNbrokerage.setPreferredSize(new Dimension(width, totalHeight));		        
	        PNbrokerage.revalidate();
	        PNbrokerage.repaint();	        
	        for(int i = 0; i < registers.size(); i++) {
	            registers.get(i).setBounds(registers.size()>5 ? 0 : 10,5 + i * height + i * 10,width,height);
	            PNbrokerage.add(registers.get(i));
	            registers.get(i).revalidate();
	            registers.get(i).repaint();
	        }
	        if(registers.size()>0) {
	        	registers.get(0).setSelected();
	        }	        
	    } catch (Exception e) {
	        support.Message.Error(this.getClass().getName(), "setPanels", e);
	    }
	}
	
	public void checkPanels() {
		for(BrokerageReportBriefing b: registers) {
			b.checking();
		}
	}
	
	protected void setRegister(BrokerageReportView register) {
		PNtitlePanel.setRegister(register);
		PNbusinessBriefingPanel.setRegister(register.getBrokerageReportRegister());
		PNclearingPanel.setRegister(register.getBrokerageReportRegister());
		PNstockPanel.setRegister(register.getBrokerageReportRegister());
		PNbrokerageExpensesPanel.setRegister(register.getBrokerageReportRegister());
		fillTable(register.getTitles());
	}
	
	protected void delete(BrokerageReportBriefing register) {
		registers.remove(register);
		setPanels();
	}
	
	private void save() {
		ArrayList<BrokerageReportView> brokerageReportRegisters = new ArrayList<BrokerageReportView>();
		for(BrokerageReportBriefing b: registers) {
			if(b.getRegister()==null) {
				Message.Warning("RESOLVA AS PENDÊNCIAS ANTES DE SALVAR",true);
				return;
			}else {
				brokerageReportRegisters.add(b.getRegister());
			}			
		}
		
		if(brokerageReportRegisters.size()<1) {
			Message.Warning("SEM NOTAS PARA SALVAR",true);
			return;
		}
		
		LoadingProgressBar slider = new LoadingProgressBar("SALVANDO NOTAS",brokerageReportRegisters.size(),this);
		
		Thread loadingThread = new Thread(() -> {	
			slider.showDialog(true);
			try {
				BrokerageReportConnect connect = new BrokerageReportConnect();
				TitleConnect titleConnect = new TitleConnect();
				for(int i=0;i<brokerageReportRegisters.size();i++) {
					slider.setValueOne();
					int id = connect.post(brokerageReportRegisters.get(i).getBrokerageReportRegister());
					for(TitleRegister t: brokerageReportRegisters.get(i).getTitles()) {
						t.setBrokerageReportId(id);
						titleConnect.post(t);
					}
				}
				slider.showDialog(false);
			} catch (IOException e) {
				e.printStackTrace();
			}			
        });	        
        loadingThread.start();
	}
	
	private void fillTable(ArrayList<TitleRegister> titles) {
		DefaultTableModel dtm = (DefaultTableModel) TBstock.table.getModel();
		dtm.setRowCount(0);
		for (TitleRegister title : titles) {
	        Object[] rowData = {
	            title.getNegotiation(),
	            title.getNegotiationType(),
	            title.getMarketType(),
	            title.getTitleName(),
	            title.getQuantity(),
	            FunctionBigDecimal.bigDecimalToCurrencyBR(title.getPrice()),
	            FunctionBigDecimal.bigDecimalToCurrencyBR(title.getPriceTotal()),
	            title.getOperationType()
	        };
	        dtm.addRow(rowData);
	    }	
	}
	
	private void windowsClosing() {
		this.addWindowListener((WindowListener) new WindowListener() {			
			public void windowOpened(WindowEvent e) {}			
			@Override
			public void windowIconified(WindowEvent e) {}			
			@Override
			public void windowDeiconified(WindowEvent e) {}			
			@Override
			public void windowDeactivated(WindowEvent e) {}			
			@Override
			public void windowClosing(WindowEvent e) {
				closeScreen();
			}			
			@Override
			public void windowClosed(WindowEvent e) {}			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}
	
}

class helpCheck{
	public int stockBrokerage;
	public String invoiceNumber;
}
