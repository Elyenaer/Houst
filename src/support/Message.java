package support;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Message {
	
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
    }
    
    public static void Error(final String namePackage, final String nameClass, final String nameMethod, final String javaMsg) {
        final JOptionPane optionPane = new JOptionPane("PACKAGE: " + namePackage + "\nCLASS: " + nameClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(javaMsg, 50), 0);
        final JDialog dialog = optionPane.createDialog("ERROR MESSAGE");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static void Information(final String message) {
        final JOptionPane optionPane = new JOptionPane(FunctionText.QuebrarLinha(message, 50), 1);
        final JDialog dialog = optionPane.createDialog("INFORMATION");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static void Information(final String message, final boolean quebraLinha) {
        final JOptionPane optionPane = new JOptionPane(message, 1);
        final JDialog dialog = optionPane.createDialog("INFORMATION");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public static int Options(final Object[] options, final String message, final String title) {
        return JOptionPane.showOptionDialog(null, message, title, -1, 2, null, options, options[0]);
    }
    
    public static boolean Options(final String message) {
        final Object[] options = { "CANCELAR", "CONFIRMAR" };
        final int information = Options(options, message, "CONFIRMAR EXCLUS\u00c3O");
        return information == 1;
    }
    
    public static boolean Options(final String message, final String title) {
        final Object[] options = { "CANCELAR", "CONFIRMAR" };
        final int information = Options(options, message, title);
        return information == 1;
    }
    }