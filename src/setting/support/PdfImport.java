package setting.support;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


public class PdfImport {
	  
    public static ArrayList<String> getTextPages(String path){
    	try {
    		ArrayList<String> text = new ArrayList<String>();
    		PdfReader pdfReader = new PdfReader(path);
            int totalPages = pdfReader.getNumberOfPages();
            for (int page = 1; page <= totalPages; page++) {	                
                text.add(PdfTextExtractor.getTextFromPage(pdfReader, page));
            }
            pdfReader.close();
    		return text;
    	}catch (IOException e) {
    		setting.function.Message.Error("custom","PdfImport","getTextPages",e.getMessage());
            return null;
        }    	
    }

}
