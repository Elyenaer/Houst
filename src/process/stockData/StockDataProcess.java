package process.stockData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import support.Message;

public class StockDataProcess {
	
	
	public ArrayList<Object[]> get(String path){		
		ArrayList<Object[]> registers = new ArrayList<Object[]>();	
		try {
			try (BufferedReader brdrd = new BufferedReader(new FileReader(path))) {
						
				String line;
				while ((line = brdrd.readLine()) != null) {
					registers.add(line.split(";"));
				}
			}
		}catch (Exception e) {
			Message.Error(this.getClass().getName(),"get", e);
		}
		return registers;
	}

}
