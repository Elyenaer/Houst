package support;

import java.util.Optional;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import components.CustomMessage;
import setting.Design;
import setting.support.MessageType;

public class Message {
	/*
    public static void Error(final String nameClass, final String nameMethod, final String message) {
        final String nmPackage = nameClass.substring(0, nameClass.indexOf("."));
        final String nmClass = nameClass.substring(nmPackage.length() + 1, nameClass.length());
        final JOptionPane optionPane = new JOptionPane("PACKAGE: " + nmPackage + "\nCLASS: " + nmClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(message, 50), 0);
        final JDialog dialog = optionPane.createDialog("ERROR MESSAGE");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static void Error(final String nameClass, final String nameMethod, final Exception message) {
        String auxMessage = "";
        if (message.getMessage() == null) {
            auxMessage = message.toString();
        }
        else {
            auxMessage = message.getMessage();
        }
        final String nmPackage = nameClass.substring(0, nameClass.indexOf("."));
        final String nmClass = nameClass.substring(nmPackage.length() + 1, nameClass.length());
        final JOptionPane optionPane = new JOptionPane("PACKAGE: " + nmPackage + "\nCLASS: " + nmClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(auxMessage, 50), 0);
        final JDialog dialog = optionPane.createDialog("ERROR MESSAGE");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }*/
    
    public static void Error(final String namePackage, final String nameClass, final String nameMethod, final String javaMsg) {
        final JOptionPane optionPane = new JOptionPane("PACKAGE: " + namePackage + "\nCLASS: " + nameClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(javaMsg, 50), 0);
        final JDialog dialog = optionPane.createDialog("ERROR MESSAGE");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static void Information(final String message) {
        final JOptionPane optionPane = new JOptionPane(FunctionText.QuebrarLinha(message, 50), 1);
        final JDialog dialog = optionPane.createDialog("INFORMATION");
        dialog.setBackground(Design.mainBackground);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static void Information(final String message, final boolean quebraLinha) {
        final JOptionPane optionPane = new JOptionPane(message, 1);
        final JDialog dialog = optionPane.createDialog("INFORMATION");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    /*
    public static void Warning(final String message, final boolean quebraLinha) {
        final JOptionPane optionPane = new JOptionPane(message, 1);
        final JDialog dialog = optionPane.createDialog("ALERTA!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }*/
    
    public static void Success(final String message, final boolean quebraLinha) {
        final JOptionPane optionPane = new JOptionPane(message, 1);
        final JDialog dialog = optionPane.createDialog("SUCESSO!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static int Options(final Object[] options, final String message, final String title) {
        return JOptionPane.showOptionDialog(null, message, title, -1, 2, null, options, options[0]);
    }
    
    public static void Warning(int response,JFrame frame,final String message,final boolean quebraLinha) {
    	frame.setEnabled(false);    	
        MessageType messageType = MessageType.warning;
        CustomMessage customMessage = new CustomMessage(message, messageType);
        customMessage.setVisible(true);
        new Thread(() -> {
    		while(customMessage.getResponse()==0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		frame.setEnabled(true);   
    		customMessage.dispose();
    	}).start();  
    }    
    
    public static void Error(final String nameClass, final String nameMethod, final String message) {
        String nmPackage = nameClass.substring(0, nameClass.indexOf("."));
        String nmClass = nameClass.substring(nmPackage.length() + 1, nameClass.length());
        new CustomMessage("PACKAGE: " + nmPackage + "\nCLASS: " + nmClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(message, 50), MessageType.error);
    }

    public static void Error(final String nameClass, final String nameMethod, final Exception message) {
        String auxMessage = Optional.ofNullable(message.getMessage()).orElse(message.toString());
        String nmPackage = nameClass.substring(0, nameClass.indexOf("."));
        String nmClass = nameClass.substring(nmPackage.length() + 1, nameClass.length());
        new CustomMessage("PACKAGE: " + nmPackage + "\nCLASS: " + nmClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(auxMessage, 50), MessageType.error);
    }

    public static boolean Options(final String message) {
        Object[] options = { "CANCELAR", "CONFIRMAR" };
        int information = JOptionPane.showOptionDialog(null, message, "CONFIRMAR EXCLUS\u00c3O", -1, 2, null, options, options[0]);
        return information == 1;
    }

    public static boolean Options(final String message, final String title) {
        Object[] options = { "CANCELAR", "CONFIRMAR" };
        int information = JOptionPane.showOptionDialog(null, message, title, -1, 2, null, options, options[0]);
        return information == 1;
    }
    
   }