package frame.brokerage.importing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.table.DefaultTableModel;

import components.CustomButton;
import components.CustomComboBox;
import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomTable;
import components.CustomTableRegister;
import components.LoadingDialog;
import model.register.connect.StockBrokerageConnect;
import model.register.register.StockBrokerageRegister;
import model.register.register.TitleRegister;
import model.view.connect.BrokerageReportViewBriefingConnect;
import model.view.register.BrokerageReportView;
import model.view.register.BrokerageReportViewBriefing;
import setting.desing.Design;
import setting.desing.DesignIcon;
import support.FunctionBigDecimal;
import support.Message;

public class BrokerageReportRegisterFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	protected BrokerageReportBriefing registers;
	private boolean newRegister = true;
			
	private CustomTable TBstock;	
	private CustomComboBox CBstockBrokerage;
	private CustomLabel LBstockBrokerage;
	private CustomIconButton BTopenTable,BTsave,BTclear,BTdelete;
	
	private TitlePanel PNtitlePanel;
	private BusinessBriefingPanel PNbusinessBriefingPanel;
	private ClearingPanel PNclearingPanel;	
	private BrokerageExpensesPanel PNbrokerageExpensesPanel;
	private StockPanel PNstockPanel;
	
	private ArrayList<StockBrokerageRegister> stockBrokerageRegisters;
	
	private ArrayList<TitleRegister> titles;
	
	public BrokerageReportRegisterFrame() {
		this.setTitle("CADASTRO DE NOTA DE CORRETAGEM");
		this.setSize(new Dimension(1070,730));
        this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		 getContentPane().setBackground(Design.mainBackground);
		init();
        windowsClosing();   
	}
	
	public void init() {
		titles = new ArrayList<TitleRegister>();
	}	
	
	@Override
	public void initInitiation() {		
		LBstockBrokerage = new CustomLabel("CORRETORA: ");
		CBstockBrokerage = new CustomComboBox();		
				
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
		
		BTsave = new CustomIconButton(DesignIcon.add(),32,32);
		BTclear = new CustomIconButton(DesignIcon.clear(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
		
		BTopenTable = new CustomIconButton(DesignIcon.open(),32,32);
		
		PNtitlePanel = new TitlePanel(500,200,10,true,this);
		PNbusinessBriefingPanel = new BusinessBriefingPanel(325,185,9,true);
		PNclearingPanel = new ClearingPanel(325,100,9,true);		
		PNstockPanel = new StockPanel(325,100,9,true);
		PNbrokerageExpensesPanel = new BrokerageExpensesPanel(325,185,9,true);
	}
	
	@Override
	public void initPosition() {
		LBstockBrokerage.setBounds(30,30,120,20);
		CBstockBrokerage.setBounds(105,30,200,20);
		
		BTopenTable.setBounds(310,25,32,32);
		
		BTsave.setBounds(1000,640,32,32);	
		BTclear.setBounds(960,640,32,32);
		BTdelete.setBounds(920,640,32,32);
						
		TBstock.setBounds(30,180,650,465);
		
		PNtitlePanel.setBounds(30,60,650,110);
		PNbusinessBriefingPanel.setLocation(705,30);
		PNclearingPanel.setLocation(705,220);
		PNstockPanel.setLocation(705,325);
		PNbrokerageExpensesPanel.setLocation(705,430);		
	}
	
	@Override
	public void initFormat() {
		TBstock.setColumnWidth(0,70);
		TBstock.setColumnWidth(1,20);
		TBstock.setColumnWidth(2,60);
		TBstock.setColumnWidth(3,200);
		TBstock.setColumnWidth(4,30);
		TBstock.setColumnWidth(5,70);
		TBstock.setColumnWidth(6,90);
		TBstock.setColumnWidth(7,20);	
				
		Thread thread = new Thread(() -> {
			try{
				stockBrokerageRegisters = new StockBrokerageConnect().get();
				CBstockBrokerage.addItem("");
				for(StockBrokerageRegister s: stockBrokerageRegisters) {
					CBstockBrokerage.addItem(s.getName());
				}
			}catch(Exception e) {
				
			}
		});
		thread.start();
	}
	
	@Override
	public void initEvent() {
		BTopenTable.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				openTableRegister();
			}
		});
		BTsave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newRegister) {
					save();
				}else {
					update();
				}
				
			}
		});
		BTclear.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});		
		BTdelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		CBstockBrokerage.addActionListener(
				new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CBstockBrokerage.getSelectedIndex()>0) {
						PNtitlePanel.setStockBrokerage(stockBrokerageRegisters.get(CBstockBrokerage.getSelectedIndex()-1));
					}
				}
			}
		);
		TBstock.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {                   
                	openTitleRegister(titles.get(TBstock.table.getSelectedRow()));                	
                }
            }
        });
	}
	
	@Override
	public void initAdd() {
		this.add(LBstockBrokerage);
		this.add(CBstockBrokerage);
		
		this.add(BTsave);
		this.add(BTclear);
		this.add(BTopenTable);
		this.add(BTdelete);
		
		this.add(TBstock);
		
		this.add(PNtitlePanel);
		this.add(PNbusinessBriefingPanel);
		this.add(PNclearingPanel);
		this.add(PNstockPanel);
		this.add(PNbrokerageExpensesPanel);
	}
//			
	private void openTableRegister() {
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("ID");
		titles.add("CORRETORA");
		titles.add("CLIENTE");		
		titles.add("NOTA");	
		titles.add("DATA");	
		CustomTableRegister table = new CustomTableRegister(this,"NOTAS DE CORRETAGEM",titles);
		
		LoadingDialog loadingDialog = new LoadingDialog(this,"BUSCANDO REGISTROS");
        Thread loadingThread = new Thread(() -> {	            
	        loadingDialog.showLoading();
	        try {
	        	ArrayList<BrokerageReportViewBriefing> registers = new BrokerageReportViewBriefingConnect().get();				
				ArrayList<Object[]> rows = new ArrayList<Object[]>();
		        for(BrokerageReportViewBriefing r: registers) {
					Object[] rowData = {
							r.getId(),
							r.getStockBrokerageName(),
							r.getCustomerName(),
							r.getInvoiceNumber(),
							r.getDate()
					};
					rows.add(rowData);				
				}
		        table.setRows(rows);	
		        
	            table.setVisible(true);		
	    		if(table.getSelected()!=null) {
	    			int id = Integer.parseInt(table.getSelected().toString());    			
	    			 Optional<BrokerageReportViewBriefing> foundCustomer = registers.stream()
	    			            .filter(customer -> customer.getId() == id)
	    			            .findFirst();
	    			setRegister(foundCustomer.get());
	    		}    	
	    		
	    		loadingDialog.hideLoading();	
	        }catch(Exception e) {
				Message.Error(this.getClass().getName(),"openTable", e);
			}            
        });	        
        loadingThread.start();
	}
	
	private void setRegister(BrokerageReportViewBriefing register) {
		Thread thread = new Thread(()-> {
			try {
				BrokerageReportView r = new BrokerageReportViewBriefingConnect().getFullView(register);
				setRegister(r);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		});
		thread.start();	
	}
	
	protected void setRegister(BrokerageReportView register) {
		PNtitlePanel.setRegister(register);
		PNbusinessBriefingPanel.setRegister(register.getBrokerageReportRegister());
		PNclearingPanel.setRegister(register.getBrokerageReportRegister());
		PNstockPanel.setRegister(register.getBrokerageReportRegister());
		PNbrokerageExpensesPanel.setRegister(register.getBrokerageReportRegister());
		fillTable(register.getTitles());
	}
		
	private void clear() {
		newRegister = true;
		PNbusinessBriefingPanel.clear();
		PNbrokerageExpensesPanel.clear();
		PNclearingPanel.clear();
		PNstockPanel.clear();
		PNtitlePanel.clear();
		CBstockBrokerage.setSelectedIndex(0);
		PNbusinessBriefingPanel.setVisible(false);
		PNbrokerageExpensesPanel.setVisible(false);
		PNclearingPanel.setVisible(false);
		PNstockPanel.setVisible(false);
		TBstock.setVisible(false);
	}
	
	private void save() {
		Thread thread = new Thread(()->{
			
		});
	}
	
	private void delete() {
		Thread thread = new Thread(()->{
			
		});
	}
	
	private void update() {
		Thread thread = new Thread(()->{
			
		});
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
	
	protected void openTitleRegister(TitleRegister t) {
		this.setEnabled(false);
		if(t==null) {
			new TitleRegisterFrame(this).setVisible(true);
		}else {
			new TitleRegisterFrame(this,t).setVisible(true);
		}		
	}
	
	protected void setTitle(TitleRegister newTitle) {
		titles.add(newTitle);
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
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
	        rows.add(rowData);
	    }	
		TBstock.removeRows();
		TBstock.setRows(rows);
	}
	
	protected void updateTitle(TitleRegister newTitle) {
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
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
	        rows.add(rowData);
	    }	
		TBstock.removeRows();
		TBstock.setRows(rows);
	}
	
	protected void deleteTitle(TitleRegister removeTitle) {
		titles.remove(removeTitle);
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
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
	        rows.add(rowData);
	    }	
		TBstock.removeRows();
		TBstock.setRows(rows);
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