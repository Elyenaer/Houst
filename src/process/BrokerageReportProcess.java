package process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.BrokerageReportRegister;
import model.CustomerRegister;
import model.StockBrokerageRegister;
import model.TitleRegister;
import support.FunctionText;
import support.PdfImport;

public class BrokerageReportProcess {
	
	public ArrayList<BrokerageReportRegister> get(String path) {
		
		//path = "C:/Users/Home/Documents/Houst/s2.pdf";
		
		ArrayList<BrokerageReportRegister> registers = new ArrayList<BrokerageReportRegister>();
		
		ArrayList<String> text = PdfImport.getTextPages(path);
					
		for(String t: text) {
			registers.add(getBrokerageReport(t));
		}
		
		return registers;
	}
	
	private BrokerageReportRegister getBrokerageReport(String text) {		
		BrokerageReportRegister register = getBusinessBriefing(text);
			
		register.setCustomer(getCustomer(text));
		register.setStocks(getTitles(text));
		
		
		//we need change for database
		StockBrokerageRegister brokerageRegister = new StockBrokerageRegister();
		brokerageRegister.setId(1);
		brokerageRegister.setName("GENIAL INVESTIMENTOS");		
		register.setStockBrokerage(brokerageRegister);
		
		
		
		return register;
	}
	
 	private CustomerRegister getCustomer(String text) {
		try {
			
			CustomerRegister register = new CustomerRegister();		
			
			String regexName = "\\d{6}-\\d\\s([A-ZÁÉÍÓÚÇÃ][A-ZÁÉÍÓÚÇÃ a-záéíóúçã]+\\s[A-ZÁÉÍÓÚÇÃ][A-ZÁÉÍÓÚÇÃ a-záéíóúçã]+)\\s\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
	        String name = FunctionText.extractInfo(text, regexName);
	        register.setName(name);
	        
	        String regexCode = "(\\d{6}-\\d{1})";
	        String code = FunctionText.extractInfo(text, regexCode);
	        register.setCode(code);

	        String regexCPF = "(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})";
	        String cpf = FunctionText.extractInfo(text, regexCPF);
	        register.setCpf(cpf.replace(".","").replace("-",""));
	        
	        /*
	        System.out.println("-----------Customer-------------");
	        System.out.println("Nome do Cliente: " + register.getName());
	        System.out.println("Código: " + register.getCode());
	        System.out.println("CPF: " + register.getCpf());*/
	        
			return register;
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"getCustomer",e);
			return null;
		}
	}
	
	private ArrayList<TitleRegister> getTitles(String text) {	
		try {
			String regex = "Ajuste D/C(.*?)Resumo dos Neg";
	        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
	        Matcher matcher = pattern.matcher(text);	        
	        ArrayList<TitleRegister> registers = new ArrayList<TitleRegister>();

	        if (matcher.find()) {
	            String result = matcher.group(1).trim();            
	            String[] lines = result.split("\\n");
	            for (String line : lines) {
	            	TitleRegister register = new TitleRegister();
	            	
	                String[] parts = line.split(" ");
	                
	                //negotiation "1-BOVESPA"
	                register.setNegotiation(parts[0]);
	                
	                //negotiation type C/V
	                register.setNegotiationType(parts[1].charAt(0));
	                
	                //market type "FRACIONÁRIO","VISTA"
	                register.setMarketType(parts[2]);
	                	                
	                //quantity
	                for(int i=3;i<parts.length;i++) {
	                	try {
	                		register.setQuantity(Integer.parseInt(parts[i]));
	                		break;
	                	}catch(Exception e) {}
	                }
	                 
	                //title name "MAGAZ LUIZA ON"
	                String[] p = line.split(" "+register.getMarketType()+" | "+register.getQuantity()+" ");
	                register.setTitleName(p[1]);
	                
	                String[] p2 = p[2].split(" ");
	                
	                //price
	                register.setPrice(new BigDecimal(p2[0].replace(".","").replace(",",".")));
	                
	                //price total
	                register.setPriceTotal(new BigDecimal(p2[1].replace(".","").replace(",",".")));
	                
	                //operation type
	                register.setOperationType(p2[2].charAt(0));
	                
	                registers.add(register);
	                
	                /*
	                System.out.println("-----------Titles-------------");
	                System.out.println("Negotiation: " + register.getNegotiation());
	                System.out.println("Negotiation Type: " + register.getNegotiationType());
	                System.out.println("Market Type: " + register.getMarketType());
	                System.out.println("Quantity: " + register.getQuantity());
	                System.out.println("Title Name: " + register.getTitleName());
	                System.out.println("Price: " + register.getPrice());
	                System.out.println("Price Total: " + register.getPriceTotal());
	                System.out.println("Operation Type: " + register.getOperationType());    */         
	            }	            
	        }
	        return registers;
		}catch(Exception e) {
			support.Message.Error(this.getClass().getName(),"getTitles",e);
			return null;
		}
		
	}
	
	private BrokerageReportRegister getBusinessBriefing(String text) {
		try {
			BrokerageReportRegister register = new BrokerageReportRegister();
			
			//invoice number "nr da nota"
			register.setInvoiceNumber(text.split("\n")[2].split(" ")[0]);
			
			//trading data "data pregao"
			register.setTradingDate(text.split("\n")[2].split(" ")[2]);
			
			//get part of text with data
			text = text.substring(text.indexOf("Resumo dos Negócios Resumo Financeiro D/C"),text.indexOf("(*) - Observ"));
			
			String[] lines = text.split(" ");
			
			/*
			System.out.println(text+"\n");
			for(int i=0;i<lines.length;i++) {
				System.out.println(i+"->"+lines[i]+"<-");
			}*/
			
			//debentures
			register.setDebentures(new BigDecimal(lines[6].replace(".","").replace(",",".")));
			
			//spotSales "venda a vista"
			register.setSpotSales(new BigDecimal(lines[10].replace(".","").replace(",",".")));
			
			//spotPurchases "compra a vista"
			register.setSpotPurchases(new BigDecimal(lines[19].replace(".","").replace(",",".")));
			
			//spotPurchases "opcao compra"
			register.setOptionsPurchases(new BigDecimal(lines[27].replace(".","").replace(",",".")));
			
			//spotSales "opcao venda"
			register.setOptionsSales(new BigDecimal(lines[35].replace(".","").replace(",",".")));
			
			//forward operation "operações a termo"
			register.setForwardOperation(new BigDecimal(lines[42].replace(".","").replace(",",".")));
			
			//value of public securities operation "Valor das oper. c/ títulos públ. (v. nom."
			register.setValueOfPublicSecuritiesOperation(new BigDecimal(lines[51].replace(".","").replace(",",".")));
			
			//operation value
			register.setOperationValue(new BigDecimal(lines[60].replace("A.N.A.\n","").replace(".","").replace(",",".")));	
			
			//net value operation "Valor líquido das operações"
			if(lines[16].replace("Compras\n","").equalsIgnoreCase("C")) {
				register.setNetValueOperation(new BigDecimal(lines[15].replace(".","").replace(",",".")));
			}else {
				register.setNetValueOperation(new BigDecimal("-"+lines[15].replace(".","").replace(",",".")));
			}			
			
			//settlement fee "Taxa de liquidação"
			if(lines[24].substring(0,1).equalsIgnoreCase("C")) {
				register.setSettlementFee(new BigDecimal(lines[23].replace(".","").replace(",",".")));
			}else {
				register.setSettlementFee(new BigDecimal("-"+lines[23].replace(".","").replace(",",".")));
			}
			
			//registration fee "taxa de registro"
			if(lines[32].substring(0,1).equalsIgnoreCase("C")) {
				register.setRegistrationFee(new BigDecimal(lines[31].replace(".","").replace(",",".")));
			}else {
				register.setRegistrationFee(new BigDecimal("-"+lines[31].replace(".","").replace(",",".")));
			}			
			
			//total cbcl
			if(lines[39].substring(0,1).equalsIgnoreCase("C")) {
				register.setTotalCBLC(new BigDecimal(lines[38].replace(".","").replace(",",".")));
			}else {
				register.setTotalCBLC(new BigDecimal("-"+lines[38].replace(".","").replace(",",".")));
			}
			
			//term Options Fee "taxa de termo / opcoes"
			if(lines[56].substring(0,1).equalsIgnoreCase("C")) {
				register.setTermOptionsFee(new BigDecimal(lines[55].replace(".","").replace(",",".")));
			}else {
				register.setTermOptionsFee(new BigDecimal("-"+lines[55].replace(".","").replace(",",".")));
			}
			
			//A.N.A. FEE "taxa ANA"
			if(lines[62].substring(0,1).equalsIgnoreCase("C")) {
				register.setAnaFee(new BigDecimal(lines[61].replace(".","").replace(",",".")));
			}else {
				register.setAnaFee(new BigDecimal("-"+lines[61].replace(".","").replace(",",".")));
			}
			
			//emoluments "emolumentos"
			if(lines[64].substring(0,1).equalsIgnoreCase("C")) {
				register.setEmoluments(new BigDecimal(lines[63].replace(".","").replace(",",".")));
			}else {  
				register.setEmoluments(new BigDecimal("-"+lines[63].replace(".","").replace(",",".")));
			}
						
			//total bovespa
			if(lines[69].substring(0,1).equalsIgnoreCase("C")) {
				register.setTotalBovespa(new BigDecimal(lines[68].replace(".","").replace(",",".")));
			}else {
				register.setTotalBovespa(new BigDecimal("-"+lines[68].replace(".","").replace(",",".")));
			}
									
			//clearing
			if(lines[82].substring(0,1).equalsIgnoreCase("C")) {
				register.setClearing(new BigDecimal(lines[81].substring(13,lines[81].length()).replace(".","").replace(",",".")));
			}else {
				register.setClearing(new BigDecimal("-"+lines[81].substring(13,lines[81].length()).replace(".","").replace(",",".")));
			}
						
			//execution "execução"
			if(lines[83].substring(0,1).equalsIgnoreCase("C")) {
				register.setClearing(new BigDecimal(lines[82].substring(20,lines[82].length()).replace(".","").replace(",",".")));
			}else {
				register.setClearing(new BigDecimal("-"+lines[82].substring(20,lines[82].length()).replace(".","").replace(",",".")));
			}
						
			//in house execution "execução casa"
			if(lines[85].substring(0,1).equalsIgnoreCase("C")) {
				register.setInHouseExecution(new BigDecimal(lines[84].substring(5,lines[84].length()).replace(".","").replace(",",".")));
			}else {
				register.setInHouseExecution(new BigDecimal("-"+lines[84].substring(5,lines[84].length()).replace(".","").replace(",",".")));
			}
			
			//iss sao paulo
			register.setIss(new BigDecimal("-"+lines[90].split("\n")[0].replace(".","").replace(",",".")));
						
			//there is a brokerage with "I.R.R.F s/ operações, base R$ 2.603,70 0,13" and without
			//try first with
			try {
				//irrf base
				register.setIrrfBase(new BigDecimal(lines[95].replace(".","").replace(",",".")));
						
				//irrf
				register.setIrrf(new BigDecimal(lines[96].replace(".","").replace(",",".")));
							
				//iss pis cofins
				if(lines[100].substring(0,1).equalsIgnoreCase("C")) {
					register.setIssPisCofins(new BigDecimal(lines[99].replace(".","").replace(",",".")));
				}else {
					register.setIssPisCofins(new BigDecimal("-"+lines[99].replace(".","").replace(",",".")));
				}
							
				//total brokerage expenses "total corretagem despesas"
				register.setTotalBrokerageExpenses(new BigDecimal(lines[100].split("\n")[1].replace(".","").replace(",",".")));
							
				//net amount for date "liquido para data"
				register.setNetAmountForDate(lines[103].split("\n")[1]);
				
				//net amount for value "liquido para valor"
				register.setNetAmountFor(new BigDecimal(lines[104].replace(".","").replace(",",".")));
			}catch(Exception e) {
				//try without
						
				//irrf base
				register.setIrrfBase(new BigDecimal("0"));
					
				//irrf
				register.setIrrf(new BigDecimal("0"));
									
				//iss pis cofins
				if(lines[93].substring(0,1).equalsIgnoreCase("C")) {
					register.setIssPisCofins(new BigDecimal(lines[92].replace(".","").replace(",",".")));
				}else {
					register.setIssPisCofins(new BigDecimal("-"+lines[92].replace(".","").replace(",",".")));
				}
				
				//total brokerage expenses "total corretagem despesas"
				register.setTotalBrokerageExpenses(new BigDecimal(lines[93].split("\n")[1].replace(".","").replace(",",".")));
						
				//net amount for date "liquido para data"
				register.setNetAmountForDate(lines[96].split("\n")[1]);
				
				//net amount for value "liquido para valor"
				register.setNetAmountFor(new BigDecimal(lines[97].replace(".","").replace(",",".")));
			}
					
			/*
			System.out.println("-----------Data-------------");
			System.out.println("Debentures: " + register.getDebentures());
			System.out.println("Spot Sales: " + register.getSpotSales());
			System.out.println("Spot Purchases: " + register.getSpotPurchases());
			System.out.println("Options Purchases: " + register.getOptionsPurchases());
			System.out.println("Options Sales: " + register.getOptionsSales());
			System.out.println("Forward Operation: " + register.getForwardOperation());
			System.out.println("Value of Public Securities Operation: " + register.getValueOfPublicSecuritiesOperation());
			System.out.println("Operation Value: " + register.getOperationValue());
			System.out.println("Net Value Operation: " + register.getNetValueOperation());
			System.out.println("Settlement Fee: " + register.getSettlementFee());
			System.out.println("Registration Fee: " + register.getRegistrationFee());
			System.out.println("Total CBLC: " + register.getTotalCBLC());
			System.out.println("Term Options Fee: " + register.getTermOptionsFee());
			System.out.println("ANA Fee: " + register.getAnaFee());
			System.out.println("Emoluments: " + register.getEmoluments());
			System.out.println("Total Bovespa: " + register.getTotalBovespa());
			System.out.println("Clearing: " + register.getClearing());
			System.out.println("In-House Execution: " + register.getInHouseExecution());
			System.out.println("ISS São Paulo: " + register.getIss());
			System.out.println("IRRF Base: " + register.getIrrfBase());
			System.out.println("IRRF: " + register.getIrrf());
			System.out.println("ISS PIS COFINS: " + register.getIssPisCofins());
			System.out.println("Total Brokerage Expenses: " + register.getTotalBrokerageExpenses());
			System.out.println("Net Amount For Date: " + register.getNetAmountForDate());
			System.out.println("Net Amount For Value: " + register.getNetAmountFor());*/
				
			return register;
		}catch (Exception e) {
			support.Message.Error(this.getClass().getName(),"getCustomer",e);
			return null;
		}	
	}
}
