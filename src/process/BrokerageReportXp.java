package process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.register.register.BrokerageReportRegister;
import model.register.register.CustomerRegister;
import model.register.register.StockBrokerageRegister;
import model.register.register.TitleRegister;
import support.FunctionText;

public class BrokerageReportXp {

	public ArrayList<BrokerageReportRegister> get(ArrayList<String> text) {			
		ArrayList<BrokerageReportRegister> registers = new ArrayList<BrokerageReportRegister>();	
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
		brokerageRegister.setId(2);
		brokerageRegister.setName("XP INVESTIMENTOS");		
		register.setStockBrokerage(brokerageRegister);
		
		return register;
	}
	
 	private CustomerRegister getCustomer(String text) {
		try {
			CustomerRegister register = new CustomerRegister();		
			
	        register.setName(text.split("\n")[10]);
				        
	        String regexCode = "(\\d{6}-\\d{1})";
	        String code = FunctionText.extractInfo(text, regexCode);
	        register.setCode(code);

	        String regexCPF = "(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})";
	        String cpf = FunctionText.extractInfo(text, regexCPF);
	        register.setCpf(cpf.replace(".","").replace("-",""));
	        
	        /*
	        System.out.println("-----------Customer-------------");
	        System.out.println("Nome do Cliente: " + register.getName());
	        System.out.println("Código: ->" + register.getCode() + "<-");
	        System.out.println("CPF: ->" + register.getCpf() + "<-");*/
	        
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
	                System.out.println("Operation Type: " + register.getOperationType());   */     
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
			text = text.substring(text.indexOf("cios Resumo Financeiro"),text.indexOf("(*) Observa"));
			
			String[] lines = text.split(" ");
			
			/*
			System.out.println(text+"\n");
			for(int i=0;i<lines.length;i++) {
				System.out.println(i+"->"+lines[i]+"<-");
			}*/
			
			//debentures
			register.setDebentures(new BigDecimal(lines[3].replace(".","").replace(",",".")));
			
			//spotSales "venda a vista"
			register.setSpotSales(new BigDecimal(lines[7].replace(".","").replace(",",".")));
			
			//spotPurchases "compra a vista"
			register.setSpotPurchases(new BigDecimal(lines[16].replace(".","").replace(",",".")));
			
			//spotPurchases "opcao compra"
			register.setOptionsPurchases(new BigDecimal(lines[24].replace(".","").replace(",",".")));
			
			//spotSales "opcao venda"
			register.setOptionsSales(new BigDecimal(lines[32].replace(".","").replace(",",".")));
			
			//forward operation "operações a termo"
			register.setForwardOperation(new BigDecimal(lines[39].split("\n")[0].replace(".","").replace(",",".")));
			
			//value of public securities operation "Valor das oper. c/ títulos públ. (v. nom."
			register.setValueOfPublicSecuritiesOperation(new BigDecimal(lines[51].split("\n")[0].replace(".","").replace(",",".")));
			
			//operation value
			register.setOperationValue(new BigDecimal(lines[54].replace(".","").replace(",",".")));	
				
			//net value operation "Valor líquido das operações"
			if(lines[13].split("\n")[0].equalsIgnoreCase("C")) {
				register.setNetValueOperation(new BigDecimal(lines[12].replace(".","").replace(",",".")));
			}else {
				register.setNetValueOperation(new BigDecimal("-"+lines[12].replace(".","").replace(",",".")));
			}			
			
			//settlement fee "Taxa de liquidação"
			if(lines[21].split("\n")[0].equalsIgnoreCase("C")) {
				register.setSettlementFee(new BigDecimal(lines[20].replace(".","").replace(",",".")));
			}else {
				register.setSettlementFee(new BigDecimal("-"+lines[20].replace(".","").replace(",",".")));
			}
			
			//registration fee "taxa de registro"
			if(lines[29].split("\n")[0].equalsIgnoreCase("C")) {
				register.setRegistrationFee(new BigDecimal(lines[28].replace(".","").replace(",",".")));
			}else {
				register.setRegistrationFee(new BigDecimal("-"+lines[28].replace(".","").replace(",",".")));
			}			
			
			//total cbcl
			if(lines[36].split("\n")[0].equalsIgnoreCase("C")) {
				register.setTotalCBLC(new BigDecimal(lines[35].replace(".","").replace(",",".")));
			}else {
				register.setTotalCBLC(new BigDecimal("-"+lines[35].replace(".","").replace(",",".")));
			}
			
			//term Options Fee "taxa de termo / opcoes"
			if(lines[43].split("\n")[0].equalsIgnoreCase("C")) {
				register.setTermOptionsFee(new BigDecimal(lines[42].replace(".","").replace(",",".")));
			}else {
				register.setTermOptionsFee(new BigDecimal("-"+lines[42].replace(".","").replace(",",".")));
			}
			
			//A.N.A. FEE "taxa ANA"
			if(lines[58].split("\n")[0].equalsIgnoreCase("C")) {
				register.setAnaFee(new BigDecimal(lines[57].replace(".","").replace(",",".")));
			}else {
				register.setAnaFee(new BigDecimal("-"+lines[57].replace(".","").replace(",",".")));
			}
			
			//emoluments "emolumentos"
			if(lines[60].split("\n")[0].equalsIgnoreCase("C")) {
				register.setEmoluments(new BigDecimal(lines[59].replace(".","").replace(",",".")));
			}else {  
				register.setEmoluments(new BigDecimal("-"+lines[59].replace(".","").replace(",",".")));
			}
						
			//total bovespa
			if(lines[65].split("\n")[0].equalsIgnoreCase("C")) {
				register.setTotalBovespa(new BigDecimal(lines[64].replace(".","").replace(",",".")));
			}else {
				register.setTotalBovespa(new BigDecimal("-"+lines[64].replace(".","").replace(",",".")));
			}
												
			//clearing "taxa operacional"
			if(lines[72].split("\n")[0].equalsIgnoreCase("C")) {
				register.setClearing(new BigDecimal(lines[71].replace(".","").replace(",",".")));
			}else {
				register.setClearing(new BigDecimal("-"+lines[71].replace(".","").replace(",",".")));
			}
						
			//execution "execução"
			register.setExecution(new BigDecimal(lines[73].split("\n")[0].replace(".","").replace(",",".")));
												
			//in house execution "execução casa"/"taxa custodia"
			if(lines[86].split("\n")[0].equalsIgnoreCase("C")) {
				register.setInHouseExecution(new BigDecimal(lines[84].split("\n")[0].replace(".","").replace(",",".")));
			}else {
				register.setInHouseExecution(new BigDecimal("-"+lines[84].split("\n")[0].replace(".","").replace(",",".")));
			}
			
			//iss sao paulo "imposto"
			register.setIss(new BigDecimal("-"+lines[85].split("\n")[0].replace(".","").replace(",",".")));
									
			//irrf base
			register.setIrrfBase(new BigDecimal(lines[90].replace("R$","").replace(".","").replace(",",".")));
					
			//irrf
			register.setIrrf(new BigDecimal(lines[91].split("\n")[0].replace(".","").replace(",",".")));
						
			//iss pis cofins / "outro"
			if(lines[93].split("\n")[0].equalsIgnoreCase("C")) {
				register.setIssPisCofins(new BigDecimal(lines[92].replace(".","").replace(",",".")));
			}else {
				register.setIssPisCofins(new BigDecimal("-"+lines[92].replace(".","").replace(",",".")));
			}
						
			//total brokerage expenses "total corretagem despesas"
			register.setTotalBrokerageExpenses(new BigDecimal(lines[97].replace(".","").replace(",",".")));
						
			//net amount for date "liquido para data"
			register.setNetAmountForDate(lines[100]);
			
			//net amount for value "liquido para valor"
			register.setNetAmountFor(new BigDecimal(lines[101].replace(".","").replace(",",".")));
			
					
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
