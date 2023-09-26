package frame.brokerage.importing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.CustomButton;
import components.CustomTable;
import model.BrokerageReportRegister;
import process.BrokerageReportProcess;
import setting.Design;

public class BrokerageReportImportFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<BrokerageReportBriefing> registers;
	
	private JPanel PNbrokerage;
	private JScrollPane SPbrokerage;
	
	private CustomTable TBstock;	
	
	private JButton BTimport,BTsave;
	
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
        initComponents();
        windowsClosing();   
	}
	
	private void init() {
		registers = new ArrayList<BrokerageReportBriefing>();
	}
	
	private void initComponents() {
		initInitiation();
		initPosition();
		initFormat();
		initEvent();	
		initAdd();    	
	}
	
	private void initInitiation() {
		PNbrokerage = new JPanel();
		SPbrokerage = new JScrollPane();
		SPbrokerage.add(PNbrokerage);
		
		TBstock = new CustomTable();
		
		BTimport = new CustomButton("IMPORT");
		BTsave = new CustomButton("SALVAR");
		
		PNtitlePanel = new TitlePanel(500,200,10);
		PNbusinessBriefingPanel = new BusinessBriefingPanel(325,185,9);
		PNclearingPanel = new ClearingPanel(325,100,9);		
		PNstockPanel = new StockPanel(325,100,9);
		PNbrokerageExpensesPanel = new BrokerageExpensesPanel(325,185,9);
	}
	
	private void initPosition() {
		BTimport.setBounds(30,30,200,25);
		BTsave.setBounds(1140,650,100,25);	
				
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
	
	private void initFormat() {
		PNbrokerage.setBackground(Design.mainBackground);
	}
	
	private void initEvent() {
		BTimport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				importBrokerage();
			}
		});
	}
	
	private void initAdd() {
		this.add(BTimport);
		this.add(BTsave);
		
		this.add(SPbrokerage);
		this.add(TBstock);
		
		this.add(PNtitlePanel);
		this.add(PNbusinessBriefingPanel);
		this.add(PNclearingPanel);
		this.add(PNstockPanel);
		this.add(PNbrokerageExpensesPanel);
	}
	
	private void importBrokerage() {
		try {			
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setMultiSelectionEnabled(true);
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PDF", "pdf");
	        fileChooser.setFileFilter(filter);
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File[] selectedFiles = fileChooser.getSelectedFiles();	            
	            BrokerageReportProcess process = new BrokerageReportProcess();	            
	            for (File file : selectedFiles) {
	            	ArrayList<BrokerageReportRegister> r = process.get(file.getAbsolutePath());
	            	for(BrokerageReportRegister t: r) {
	            		registers.add(new BrokerageReportBriefing(t,this));
	            	}
	            }
	        }
	        setPanels();
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"importBrokerage",e);
		}
	}
	
	private void setPanels() {
		try {	
			int width = 175;
	        int height = 100;
	        int totalHeight = height * registers.size() + registers.size() * 10;	        
	        PNbrokerage.setPreferredSize(new Dimension(width, totalHeight));		        
	        PNbrokerage.revalidate();
	        PNbrokerage.repaint();
	        
	        for (int i = 0; i < registers.size(); i++) {
	            registers.get(i).setBounds(registers.size()>5 ? 0 : 10,5 + i * height + i * 10,width,height);
	            PNbrokerage.add(registers.get(i));
	        }
	    } catch (Exception e) {
	        support.Message.Error(this.getClass().getName(), "setPanels", e);
	    }
	}
	
	protected void setRegister(BrokerageReportRegister register) {
		PNtitlePanel.setRegister(register);
		PNbusinessBriefingPanel.setRegister(register);
		PNclearingPanel.setRegister(register);
		PNstockPanel.setRegister(register);
		PNbrokerageExpensesPanel.setRegister(register);
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
	
	private void closeScreen() {
		this.dispose();
	}
}
