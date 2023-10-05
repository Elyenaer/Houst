package components;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import setting.desing.Design;

public class CustomRadioButton extends CustomPanel {
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroup;
	private ArrayList<JRadioButton> buttons;
	private JPanel panel;

    public CustomRadioButton(int width,int heigth) {
    	super(width,heigth);
    	init();
    }
    
    private void init() {    	
        buttonGroup = new ButtonGroup();
        buttons = new ArrayList<JRadioButton>();
        this.setLayout(null);
        panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Design.mainBackground);
        panel.setLayout(new GridLayout(0, 1));
        panel.setBounds(15,20,getWidth()-30,getHeight()-30);
        this.add(panel);
    }
    
    public void addButton(String button) {
    	JRadioButton option = new JRadioButton(button);
    	option.setBackground(Design.mainBackground);
    	option.setForeground(Design.mainForeground);
    	option.setFont(Design.getFont(10,true));
    	buttonGroup.add(option);
    	buttons.add(option);
    	panel.add(option);
    }
    
    public void clear() {
        buttonGroup.clearSelection();
    }
    
    public String getSelected() {
    	for(JRadioButton r: buttons) {
    		if(r.isSelected()) {
    			return r.getText();
    		}
    	}
    	return null;
    }
    
    public void setSelectedByText(String selected) {
    	for(JRadioButton r: buttons) {
    		if(r.getText().equalsIgnoreCase(selected)) {
    			r.setSelected(true);
    		}
    	}
    }
}
