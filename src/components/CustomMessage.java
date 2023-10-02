package components;
import javax.swing.*;

import setting.desing.Design;
import setting.desing.DesignIcon;
import setting.support.MessageType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

public class CustomMessage extends JDialog {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private String message;
    private boolean response = false;
    private CompletableFuture<Boolean> completionFuture = new CompletableFuture<>();
    private Color background = Design.componentsBackground;
    private Color foreground = Design.componentsForeground;

    public CustomMessage(String message, MessageType type) {
        super();
        this.setIconImage(DesignIcon.icon16x16());
        this.type = type;
        this.message = message;
        setModal(true);
        init();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        getContentPane().setBackground(background);
    }

    public boolean process() {
        setTitle(type.toString());        
        JLabel iconLabel = new CustomLabel(message);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(iconLabel, BorderLayout.CENTER);
        if (type == MessageType.confirm) {
            this.setTitle("CONFIRMAÇÃO");
            iconLabel.setIcon(UIManager.getIcon("OptionPane.optionIcon"));
        } else if (type == MessageType.error) {
        	iconLabel.setText("");
            this.setTitle("ERROR");
            iconLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));            
            JTextArea textArea = new JTextArea(message);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textArea.setBackground(background);
            textArea.setForeground(foreground);
            textArea.setBorder(BorderFactory.createEmptyBorder());   
            JScrollPane scrollPane = new JScrollPane(textArea);            
            scrollPane.setPreferredSize(new Dimension(300, 150));
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            add(iconLabel, BorderLayout.NORTH);
        } else if (type == MessageType.warning) {
            this.setTitle("ALERTA");
            iconLabel.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        } else if (type == MessageType.information) {
            this.setTitle("INFORMAÇÃO");
            iconLabel.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        } else if (type == MessageType.successful) {
            this.setTitle("SUCESSO");
            iconLabel.setIcon(UIManager.getIcon("OptionPane.successfulIcon"));
        }
        
        pack();   
        
        if (type == MessageType.error) {
        	 Dimension preferredLabelSize = iconLabel.getPreferredSize();
             int dialogWidth = preferredLabelSize.width + 400; 
             int dialogHeight = preferredLabelSize.height + 250;
             setSize(dialogWidth, dialogHeight);
        }else {
        	 Dimension preferredLabelSize = iconLabel.getPreferredSize();
             int dialogWidth = preferredLabelSize.width + 150; 
             int dialogHeight = preferredLabelSize.height + 80;
             setSize(dialogWidth, dialogHeight);
        }
               
        setLocationRelativeTo(null);
        setVisible(true);
        return response;
    }

    private void init() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(background);
        if (type == MessageType.confirm) {
            JButton cancelButton = new CustomButton("CANCELAR");
            JButton confirmButton = new CustomButton("CONFIRMAR");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    respond(false);
                }
            });

            confirmButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    respond(true);
                }
            });
            buttonPanel.add(cancelButton);
            buttonPanel.add(confirmButton);
        } else {
            JButton okButton = new CustomButton("OK");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    respond(true);
                }
            });
            buttonPanel.add(okButton);
        }
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void respond(boolean r) {
        response = r;
        completionFuture.complete(response);
        dispose();
    }

    public boolean getResponse() {
        return response;
    }
}

