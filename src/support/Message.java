package support;

import java.util.Optional;

import components.CustomMessage;
import setting.support.MessageType;

public class Message {
	        
    public static boolean Information(final String message) {
    	CustomMessage customMessage = new CustomMessage(message, MessageType.information);
    	return customMessage.process();
    }
    
    public static void Success(final String message) {
    	CustomMessage customMessage = new CustomMessage(message, MessageType.successful);
    	customMessage.process();
    }
    
    public static boolean Options(String message) {
        CustomMessage customMessage = new CustomMessage(message, MessageType.confirm);
    	return customMessage.process();
    }
    
    public static void Warning(final String message,final boolean quebraLinha) {
    	CustomMessage customMessage = new CustomMessage(message, MessageType.warning);
    	customMessage.process();
    }    
    
    public static void Error(final String nameClass, final String nameMethod, final String message) {
        String nmPackage = nameClass.substring(0, nameClass.indexOf("."));
        String nmClass = nameClass.substring(nmPackage.length() + 1, nameClass.length());
        CustomMessage customMessage = new CustomMessage("PACKAGE: " + nmPackage + "\nCLASS: " + nmClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(message, 50), MessageType.error);
        customMessage.process();
    }

    public static void Error(final String nameClass, final String nameMethod, final Exception message) {
        String auxMessage = Optional.ofNullable(message.getMessage()).orElse(message.toString());
        String nmPackage = nameClass.substring(0, nameClass.indexOf("."));
        String nmClass = nameClass.substring(nmPackage.length() + 1, nameClass.length());
        CustomMessage customMessage = new CustomMessage("PACKAGE: " + nmPackage + "\nCLASS: " + nmClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(auxMessage, 50), MessageType.error);
        customMessage.process();
    }
    
    public static void Error(final String namePackage, final String nameClass, final String nameMethod, final String javaMsg) {
        CustomMessage customMessage = new CustomMessage("PACKAGE: " + namePackage + "\nCLASS: " + nameClass + "\nMETHOD: " + nameMethod + "\n\nMESSAGE: " + FunctionText.QuebrarLinha(javaMsg, 50),MessageType.error);
    	customMessage.process();
    }

   }