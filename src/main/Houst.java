package main;

import java.io.IOException;

import frame.stock.importing.StockDataImportFrame;

public class Houst {
	
	public static void main(String[] args) throws IOException {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            new StockDataImportFrame().setVisible(true);
            //new MetricRegisterFrame().setVisible(true);
            //new BrokerageReportImportFrame().setVisible(true);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Houst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Houst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Houst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Houst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
