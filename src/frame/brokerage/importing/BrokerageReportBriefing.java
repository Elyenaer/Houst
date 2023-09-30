package frame.brokerage.importing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import components.CustomIconButton;
import components.CustomIconLabel;
import components.CustomLabel;
import model.register.register.BrokerageReportRegister;
import setting.Design;
import support.Message;

public class BrokerageReportBriefing extends JPanel {
	private static final long serialVersionUID = 1L;
	BrokerageReportRegister register;
	private boolean selected = false;
	
	private Color background = Design.componentsBackground;
	private int cornerRadius = 20;
	
	JLabel LBcustomerName,LBinvoiceNumber,LBdate,LBstockBrokerage;
	JButton BTdelete,BTopen;
	
	private final BrokerageReportImportFrame brokerageReportImportFrame;

	public BrokerageReportBriefing(BrokerageReportRegister register,BrokerageReportImportFrame brokerageReportImportFrame){
		this.register = register;
		this.brokerageReportImportFrame = brokerageReportImportFrame;
		initComponents();	
		setLayout(null);
		this.setBackground(Design.componentsBackground);	
        setOpaque(false);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(background);
        
        int borderWidth = 1;
        if(selected) {
        	borderWidth = 3;
        }        
        Stroke borderStroke = new BasicStroke(borderWidth);
        g2d.setStroke(borderStroke);
        
        g2d.fillRoundRect(0,0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2d.setColor(Design.componentsForeground);
        g2d.drawRoundRect(0,0,getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2d.dispose();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }
	
	private void initComponents() {
		initInitiation();
		initPosition();
		initFormat();
		initEvent();	
		initAdd();
	}
	
	private void initInitiation() {
		LBcustomerName = new CustomLabel(register.getCustomer().getName(),8,true);
		LBinvoiceNumber = new CustomLabel(register.getInvoiceNumber(),10,true);
		LBdate = new CustomLabel(register.getTradingDate(),10,true);
		LBstockBrokerage = new CustomIconLabel(Design.stockBrokerageIcon(register.getStockBrokerage()),80,30);
		
		BTdelete = new CustomIconButton(Design.delete(),30,30);
		BTopen = new CustomIconButton(Design.report(),30,30);
	}
	
	private void initPosition() {
		LBstockBrokerage.setBounds(10,10,80,30);
		LBcustomerName.setBounds(10,40,150,20);
		LBinvoiceNumber.setBounds(90,15,70,20);
		LBdate.setBounds(10,75,100,20);
		BTdelete.setBounds(105,65,30,30);
		BTopen.setBounds(135,65,30,30);
	}
	
	private void initFormat() {
		LBinvoiceNumber.setHorizontalAlignment(JLabel.RIGHT);
	}
	
	private void initEvent() {
		BTopen.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelected();
			}
		});
		BTdelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();;
			}
		});
	}
	
	private void initAdd() {
		this.add(LBstockBrokerage);
		this.add(LBcustomerName);
		this.add(LBinvoiceNumber);
		this.add(LBdate);
		this.add(BTdelete);
		this.add(BTopen);		
	}
	
	protected void setSelected() {
		brokerageReportImportFrame.setRegister(register);
		for(BrokerageReportBriefing r: brokerageReportImportFrame.registers) {
			r.setSelected(false);
		}
		this.setSelected(true);
	}
	
	public void setSelected(boolean selected) {
		if(selected) {
			if(this.selected) {
				return;
			}else {
				background = Design.componentsBackground2;
				paintComponent(getGraphics());				
			}	
		}else {
			if(!this.selected) {
				return;
			}else {
				background = Design.componentsBackground;
				paintComponent(getGraphics());
			}			
		}
		this.repaint();
		this.revalidate();
		this.selected = selected;		
	}
	
	public void delete() {
		if(Message.Options("DESEJA REALMENTE EXCLUIR A Nª " + register.getInvoiceNumber() + "?")) {
			brokerageReportImportFrame.delete(this);
		}		
	}

}
