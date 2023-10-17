package process.brokerageReport;

import java.util.ArrayList;

import model.view.register.BrokerageReportView;
import setting.support.PdfImport;
import support.Message;

public class BrokerageReportProcess {
	
	public ArrayList<BrokerageReportView> get(String path) {
		ArrayList<String> text = PdfImport.getTextPages(path);		
		if(text.get(0).toUpperCase().indexOf("XP INVESTIMENTOS")>0) {
			return new BrokerageReportXp().get(text);
		}else if(text.get(0).toUpperCase().indexOf("GENIAL INVESTIMENTOS")>0 || text.get(0).toUpperCase().indexOf("GENIAL CCTVM")>0) {
			return new BrokerageReportGenial().get(text);
		}	
		
		Message.Warning("CORRETORA NÃO ENCONTRADA!",false);
		
		return null;
	}
}
