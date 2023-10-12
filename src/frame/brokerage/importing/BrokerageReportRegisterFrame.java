package frame.brokerage.importing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import components.CustomButton;
import components.CustomComboBox;
import components.CustomFrame;
import components.CustomLabel;
import components.CustomTable;
import model.register.connect.StockBrokerageConnect;
import model.register.register.StockBrokerageRegister;
import model.register.register.TitleRegister;
import model.view.register.BrokerageReportView;
import setting.desing.Design;
import setting.desing.DesignIcon;
import support.FunctionBigDecimal;

public class BrokerageReportRegisterFrame extends CustomFrame {
	private static final long serialVersionUID = 1L;
	protected BrokerageReportBriefing registers;
			
	private CustomTable TBstock;	
	private CustomComboBox CBstockBrokerage;
	private CustomButton BTsave,BTdeleteDuplicate;
	private CustomLabel LBstockBrokerage;
	
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
		
		BTsave = new CustomButton("SALVAR");
		BTdeleteDuplicate = new CustomButton("EXCLUIR DUPLICADOS!");
		
		PNtitlePanel = new TitlePanel(500,200,10,true,this);
		PNbusinessBriefingPanel = new BusinessBriefingPanel(325,185,9);
		PNclearingPanel = new ClearingPanel(325,100,9);		
		PNstockPanel = new StockPanel(325,100,9);
		PNbrokerageExpensesPanel = new BrokerageExpensesPanel(325,185,9);
	}
	
	@Override
	public void initPosition() {
		LBstockBrokerage.setBounds(30,30,120,20);
		CBstockBrokerage.setBounds(105,30,200,20);
		
		BTsave.setBounds(1140,650,100,25);	
		BTdeleteDuplicate.setBounds(930,650,200,25);
						
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
		BTdeleteDuplicate.setIcon(DesignIcon.error16x16());
		BTdeleteDuplicate.setVisible(false);		
		
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
		BTsave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
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
		this.add(BTdeleteDuplicate);
		
		this.add(TBstock);
		
		this.add(PNtitlePanel);
		this.add(PNbusinessBriefingPanel);
		this.add(PNclearingPanel);
		this.add(PNstockPanel);
		this.add(PNbrokerageExpensesPanel);
	}
			
	protected void setRegister(BrokerageReportView register) {
		PNtitlePanel.setRegister(register);
		PNbusinessBriefingPanel.setRegister(register.getBrokerageReportRegister());
		PNclearingPanel.setRegister(register.getBrokerageReportRegister());
		PNstockPanel.setRegister(register.getBrokerageReportRegister());
		PNbrokerageExpensesPanel.setRegister(register.getBrokerageReportRegister());
		fillTable(register.getTitles());
	}
		
	private void save() {
		
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