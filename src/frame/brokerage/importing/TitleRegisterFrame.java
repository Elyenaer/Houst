package frame.brokerage.importing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.CustomComboBox;
import components.CustomCurrencyField;
import components.CustomFrame;
import components.CustomIconButton;
import components.CustomLabel;
import components.CustomNumberField;
import components.CustomTextField;
import model.register.register.TitleRegister;
import setting.desing.DesignIcon;
import support.Message;

public class TitleRegisterFrame extends CustomFrame{
	private static final long serialVersionUID = 1L;
	private BrokerageReportRegisterFrame parentFrame;
	
	private CustomLabel LBq,LBnegotiation,LBnegotiationType,LBmarketType,LBdeadline,LBtitleName,LBquantity,LBprice,LBpriceTotal,LBoperatoinType;
	private CustomTextField TFq,TFtitleName,TFdeadline;
	private CustomComboBox CBnegotiation,CBnegotiationType,CBmarketType,CBoperatoinType;
	private CustomIconButton BTsave,BTdelete,BTclear;
	private CustomNumberField NFquantity;
	private CustomCurrencyField CFprice,CFpriceTotal;
	
	private TitleRegister register;
	private boolean newRegister = true;
	
	public TitleRegisterFrame(BrokerageReportRegisterFrame parentFrame,TitleRegister register) {
		this.newRegister = false;
		this.register = register;
		init(parentFrame);		
	}
	
	public TitleRegisterFrame(BrokerageReportRegisterFrame parentFrame) {
		register = new TitleRegister();		
		this.newRegister = true;
		init(parentFrame);
	}
	
	private void init(BrokerageReportRegisterFrame parentFrame){
		this.parentFrame = parentFrame;
		this.setTitle("NEGÓCIOS REALIZADOS");
		this.setSize(440,300);
		this.setLocationRelativeTo(parentFrame);
		if(newRegister) {
			BTdelete.setVisible(false);
			BTsave.setScaleIcon(DesignIcon.add());
		}else {
			setRegister(register);
		}
	}

	@Override
	public void initInitiation() {		
		LBq = new CustomLabel("Q:");
		TFq = new CustomTextField();
		
		LBnegotiation = new CustomLabel("NEGOCIAÇÃO:");
		CBnegotiation = new CustomComboBox();
		
		LBnegotiationType = new CustomLabel("C/V:");
		CBnegotiationType = new CustomComboBox();
		
		LBmarketType = new CustomLabel("TIPO DE MERCADO:");
		CBmarketType = new CustomComboBox();
		
		LBdeadline = new CustomLabel("PRAZO:");
		TFdeadline = new CustomTextField();
		
		LBtitleName = new CustomLabel("TÍTULO:");
		TFtitleName = new CustomTextField();
		
		LBquantity = new CustomLabel("QUANTIDADE:");
		NFquantity = new CustomNumberField();
		
		LBprice = new CustomLabel("PREÇO/AJUSTE:");
		CFprice = new CustomCurrencyField();
		
		LBpriceTotal = new CustomLabel("VALOR OPERAÇÃO:");
		CFpriceTotal = new CustomCurrencyField();
		
		LBoperatoinType = new CustomLabel("D/C:");
		CBoperatoinType = new CustomComboBox();
		
		BTsave = new CustomIconButton(DesignIcon.save(),32,32);
		BTdelete = new CustomIconButton(DesignIcon.delete(),32,32);
		BTclear = new CustomIconButton(DesignIcon.clear(),32,32);
	}

	@Override
	public void initPosition() {	
		LBq.setBounds(30,30,50,25);
		TFq.setBounds(45,30,50,25);
		
		LBnegotiation.setBounds(120,30,100,25);
		CBnegotiation.setBounds(195,30,105,25);
		
		LBnegotiationType.setBounds(325,30,50,25);
		CBnegotiationType.setBounds(348,30,50,25);
		
		LBmarketType.setBounds(30,60,120,25);
		CBmarketType.setBounds(133,60,120,25);
		
		LBdeadline.setBounds(285,60,50,25);
		TFdeadline.setBounds(326,60,70,25);
		
		LBtitleName.setBounds(30,90,100,25);
		TFtitleName.setBounds(75,90,320,25);
		
		LBquantity.setBounds(30,120,70,25);
		NFquantity.setBounds(105,120,80,25);
		
		LBprice.setBounds(230,120,120,25);
		CFprice.setBounds(316,120,80,25);
		
		LBpriceTotal.setBounds(30,150,120,25);
		CFpriceTotal.setBounds(135,150,100,25);
		
		LBoperatoinType.setBounds(325,150,50,25);
		CBoperatoinType.setBounds(348,150,50,25);
		
		BTsave.setBounds(365,195,32,32);
		BTdelete.setBounds(285,195,32,32);
		BTclear.setBounds(325,195,32,32);
	}

	@Override
	public void initFormat() {
		CBnegotiation.addItem("");
		CBnegotiation.addItem("1-BOVESPA");
		
		CBnegotiationType.addItem("");
		CBnegotiationType.addItem("C");
		CBnegotiationType.addItem("V");
		
		CBmarketType.addItem("");
		CBmarketType.addItem("VISTA");
		
		CBoperatoinType.addItem("");
		CBoperatoinType.addItem("D");	
		CBoperatoinType.addItem("V");	
	}

	@Override
	public void initEvent() {
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
		BTdelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		BTclear.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
	}

	@Override
	public void initAdd() {		
		this.add(LBq);
		this.add(TFq);
		
		this.add(LBnegotiation);
		this.add(CBnegotiation);
		
		this.add(LBnegotiationType);
		this.add(CBnegotiationType);
		
		this.add(LBmarketType);
		this.add(CBmarketType);
		
		this.add(LBdeadline);
		this.add(TFdeadline);
		
		this.add(LBtitleName);
		this.add(TFtitleName);
				
		this.add(LBquantity);
		this.add(NFquantity);
		
		this.add(LBprice);
		this.add(CFprice);
		
		this.add(LBpriceTotal);
		this.add(CFpriceTotal);
		
		this.add(LBoperatoinType);
		this.add(CBoperatoinType);
		
		this.add(BTsave);
		this.add(BTdelete);
		this.add(BTclear);
	}

	private void setRegister(TitleRegister t) {
	    TFq.setText(String.valueOf(t.getQ()));
	    CBnegotiation.setSelectedItem(t.getNegotiation());
	    CBnegotiationType.setSelectedItem(String.valueOf(t.getNegotiationType()));
	    CBmarketType.setSelectedItem(t.getMarketType());
	    TFdeadline.setText(t.getDeadline());
	    TFtitleName.setText(t.getTitleName());
	    NFquantity.setText(t.getQuantity()+"");
	    CFprice.setText(t.getPrice()+"");
	    CFpriceTotal.setText(t.getPriceTotal()+"");
	    CBoperatoinType.setSelectedItem(String.valueOf(t.getOperationType()));
	}
	
	private boolean getRegister() {		
		if(CBnegotiation.getSelectedIndex()<1) {
			Message.Warning("NEGOCIAÇÃO INVÁLIDA!",true);
			return false;
		}
		if(CBnegotiationType.getSelectedIndex()<1) {
			Message.Warning("TIPO DE NEGOCIAÇÃO INVÁLIDAO!",true);
			return false;		
		}
		if(CBmarketType.getSelectedIndex()<1) {
			Message.Warning("TIPO DE INVÁLIDO!",true);
			return false;
		}
		if(CBoperatoinType.getSelectedIndex()<1) {
			Message.Warning("TIPO DE OPERAÇÃO INVÁLIDO!",true);
			return false;
		}
		if(TFtitleName.getText().equalsIgnoreCase("")) {
			Message.Warning("ESPECIFICAÇÃO DO TÍTULO INVÁLIDO!",true);
			return false;
		}
		if(NFquantity.getText().equalsIgnoreCase("")) {
			Message.Warning("QUANTIDADE INVÁLIDA!",true);
			return false;
		}
		if(CFprice.getText().equalsIgnoreCase("")) {
			Message.Warning("PREÇO/AJUSTE INVÁLIDO!",true);
			return false;
		}
		if(CFpriceTotal.getText().equalsIgnoreCase("")) {
			Message.Warning("VALOR DA OPERAÇÃO/AJUSTE INVÁLIDO!",true);
			return false;
		}
		try {
			register.setQ(TFq.getText().charAt(0));
		}catch (Exception e) {};
		
		register.setNegotiation(CBnegotiation.getSelectedItem().toString());
		register.setNegotiationType(CBnegotiationType.getSelectedItem().toString().charAt(0));
		register.setMarketType(CBmarketType.getSelectedItem().toString());
		register.setDeadline(TFdeadline.getText());
		register.setTitleName(TFtitleName.getText());	
		register.setQuantity(NFquantity.getInt());
		register.setPrice(CFprice.getValue());
		register.setPriceTotal(CFpriceTotal.getValue());
		register.setOperationType(CBoperatoinType.getSelectedItem().toString().charAt(0));
		
		return true;
	}
		
	private void clear() {
		register = new TitleRegister();
		TFq.setText("");
		CBnegotiation.setSelectedIndex(0);
		CBnegotiationType.setSelectedIndex(0);
		CBmarketType.setSelectedIndex(0);
		TFdeadline.setText("");
		TFtitleName.setText("");
		NFquantity.setText("");
		CFprice.setText("");
		CFpriceTotal.setText("");
		CBoperatoinType.setSelectedIndex(0);
	}
	
	private void save() {
		if(getRegister()) {
			parentFrame.setTitle(register);
			clear();
		}
	}
	
	private void update() {
		if(getRegister()) {
			parentFrame.updateTitle(register);
			closeScreen();
		}
	}
	
	private void delete() {
		parentFrame.deleteTitle(register);
		closeScreen();
	}
	
	@Override
	public void closeScreen() {
		parentFrame.setEnabled(true);
		this.dispose();
	}
	
}
