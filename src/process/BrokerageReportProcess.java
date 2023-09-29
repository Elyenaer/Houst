package process;

import java.util.ArrayList;

import model.register.BrokerageReportRegister;
import support.PdfImport;

public class BrokerageReportProcess {
	
	public ArrayList<BrokerageReportRegister> get(String path) {
		ArrayList<String> text = PdfImport.getTextPages(path);		
		if(text.get(0).toUpperCase().indexOf("XP INVESTIMENTOS")>0) {
			return new BrokerageReportXp().get(text);
		}else if(text.get(0).toUpperCase().indexOf("GENIAL INVESTIMENTOS")>0) {
			return new BrokerageReportGenial().get(text);
		}
		return null;
	}
}
