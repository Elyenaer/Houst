package support;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionText {
	
	public static String extractInfo(String text, String regex) {
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(text);
	    if (matcher.find()) {
	        if (matcher.groupCount() > 0) {
	            return matcher.group(1);
	        }
	    }
	    return "Não encontrado";
	}
		
    public static String QuebrarLinha(final String texto, final int maximo) {
        try {
            if (texto == null) {
                return "Null";
            }
            final ArrayList<String> array = new ArrayList<String>();
            int inicioLinha = 0;
            int fimLinha = maximo - 1;
            while (texto.length() > fimLinha) {
                if (texto.charAt(fimLinha) == ' ') {
                    array.add(texto.substring(inicioLinha, fimLinha));
                    inicioLinha = fimLinha + 1;
                    fimLinha += maximo;
                }
                else {
                    ++fimLinha;
                }
            }
            array.add(texto.substring(inicioLinha, texto.length()));
            String resultado = "";
            for (int i = 0; i < array.size(); ++i) {
                resultado = resultado + "\n" + array.get(i);
            }
            return resultado;
        }
        catch (Exception e) {
            return "Texto Inválido!";
        }
    }
    
    public static int QuebrarLinhaQuantidade(final String texto, final int maximo) {
        try {
            if (texto == null) {
                return 0;
            }
            final ArrayList<String> array = new ArrayList<String>();
            int inicioLinha = 0;
            int fimLinha = maximo - 1;
            while (texto.length() > fimLinha) {
                if (texto.charAt(fimLinha) == ' ') {
                    array.add(texto.substring(inicioLinha, fimLinha));
                    inicioLinha = fimLinha + 1;
                    fimLinha += maximo;
                }
                else {
                    ++fimLinha;
                }
            }
            array.add(texto.substring(inicioLinha, texto.length()));
            return array.size();
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static String QuebrarLinhaHtml(final String texto, final int maximo) {
        try {
            if (texto == null) {
                return "Null";
            }
            return "<html>" + QuebrarLinha(texto, maximo).replaceAll("\n", "<br>") + "<html>";
        }
        catch (Exception e) {
            return "Texto Inválido!";
        }
    }
    
    public static String Limitar(final String texto, final int maximoCaracteres) {
        if (texto.length() < maximoCaracteres) {
            return texto;
        }
        return texto.substring(0, maximoCaracteres - 1);
    }

    public static int getStringWidth(String title,Font font){
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();
        g.dispose();
        return fontMetrics.stringWidth(title);        
	}
}