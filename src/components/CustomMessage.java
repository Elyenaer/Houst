package components;

import javax.swing.*;

import setting.Design;
import setting.support.MessageType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMessage extends CustomFrame {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private String message;
    private int response = 0;

    public CustomMessage(String message,MessageType type) {
        super();
        this.type = type;
        this.message = message;
        init();
    }
    
    public CustomMessage(int response,String message,MessageType type) {
        super();
        this.response = response;
        this.type = type;
        this.message = message;
        init();
    }
    
    @Override
    public void init() {
        setSize(300, 150);
        this.setLayout(new BorderLayout());

        JLabel iconLabel = new JLabel();        
        if(type==MessageType.confirm) {        	
        	this.setTitle("CONFIRMAÇÃO");
        	iconLabel.setIcon(UIManager.getIcon("OptionPane.optionIcon")); 
        }else if(type==MessageType.error) {
        	this.setTitle("ERROR");
        	iconLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        }else if(type==MessageType.warning) {
        	this.setTitle("ALERTA");
        	iconLabel.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        }else if(type==MessageType.information) {
        	this.setTitle("INFORMAÇÃO");
        	iconLabel.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        }else if(type==MessageType.successful) {
        	this.setTitle("SUCESSO");
        	iconLabel.setIcon(UIManager.getIcon("OptionPane.successfulIcon"));
        }
        
        JLabel messageLabel = new CustomLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(iconLabel, BorderLayout.NORTH);
        add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Design.mainBackground);
        buttonPanel.setLayout(new FlowLayout());
        
        if (type == MessageType.confirm) {
            JButton cancelButton = new CustomButton("CANCELAR");
            JButton confirmButton = new CustomButton("CONFIRMAR");

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	respond(1);
                }
            });

            confirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	respond(2);
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(confirmButton);

            add(buttonPanel, BorderLayout.SOUTH);
        } else {
        	CustomButton okButton = new CustomButton("OK");            
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    respond(2);
                }
            });
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(okButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    @Override
    public void initInitiation() {
       
    }

    @Override
    public void initPosition() {
       
    }

    @Override
    public void initFormat() {
       
    }

    @Override
    public void initEvent() {
        
    }

    @Override
    public void initAdd() {
       
    }

    private void respond(int r) {
    	response = r;
    }    
    
    public int getResponse() {
        return response;
    }

}