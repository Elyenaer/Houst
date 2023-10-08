package frame.main;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import components.CustomFrame;
import components.CustomMenu;
import components.CustomMenuBar;
import components.CustomMenuItem;
import frame.brokerage.importing.BrokerageReportImportFrame;
import frame.register.MetricRegisterFrame;
import frame.stock.importing.StockDataImportFrame;
import setting.desing.Design;
import setting.desing.DesignIcon;

public class InitialScreenFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	
	private CustomMenuBar menuBar;
	private CustomMenu menuImport,menuRegister;
	private CustomMenuItem MIbrokerageImport,MIstockImport,MIcustomer,MImetric;
	
	public InitialScreenFrame() {
		super();
		init();
	}
		
	private void init() {
		this.setTitle("HOUST");
		this.setResizable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setContentPane(new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                
                g2d.setColor(Design.mainBackground);
                g2d.fillRect(-100,-100, getWidth()+200, getHeight()+200);
                
                float alpha = 0.05f;
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g2d.setComposite(alphaComposite);
                
                g2d.drawImage(DesignIcon.backgroundImage(), 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        });
	}

	@Override
	public void initInitiation() {
		menuBar = new CustomMenuBar();
		
		menuImport = new CustomMenu("IMPORT");
		MIbrokerageImport = new CustomMenuItem("NOTAS DE CORRETAGEM");
		MIstockImport = new CustomMenuItem("MÉTRICAS");
		
		menuRegister = new CustomMenu("CADASTRO");
		MIcustomer = new CustomMenuItem("CLIENTES",100,25);
		MImetric =  new CustomMenuItem("MÉTRICAS",100,25);
	}

	@Override
	public void initPosition() {
		
	}

	@Override
	public void initFormat() {
		MIcustomer.setEnabled(false);
	}
	
	@Override
	public void initEvent() {
		MIcustomer.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new CustomerRegisterFrame(null,null,0, null).setVisible(true);
			}
		});
		MImetric.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MetricRegisterFrame(null).setVisible(true);
			}
		});
		MIbrokerageImport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new BrokerageReportImportFrame().setVisible(true);
			}
		});
		MIstockImport.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new StockDataImportFrame().setVisible(true);
			}
		});
	}

	@Override
	public void initAdd() {
		this.setJMenuBar(menuBar);
		
		menuBar.add(menuRegister);
		menuRegister.add(MIcustomer);
		menuRegister.add(MImetric);
		
		menuBar.add(menuImport);
		menuImport.add(MIbrokerageImport);
		menuImport.add(MIstockImport);
	}
	
	@Override
	public void closeScreen() {
		System.exit(0);
	}

}
