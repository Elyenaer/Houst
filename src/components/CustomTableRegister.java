package components;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import setting.Design;

public class CustomTableRegister extends JDialog{
	private static final long serialVersionUID = 1L;
	private CustomTable table;
	private Object selected;
	
	public CustomTableRegister(JFrame parent,String title, ArrayList<String> titles) {
		table = new CustomTable(titles);
		setTitle(title);
		setSize(500, 400);
        setLocationRelativeTo(parent); 
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  
        setModal(true);
        add(table);        
        table.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                   selected = table.table.getValueAt(table.table.getSelectedRow(),0);
                   dispose();
                }
            }
        });
	}
	
	@Override
    public void paint(Graphics g) {
        super.paint(g);
        getContentPane().setBackground(Design.componentsBackground);
    }
		
	public void setColumnWidth(int column,int width) {
		table.setColumnWidth(column, width);
	}
	
	public Object getSelected() {
		return selected;
	}
	
	public void setRows(ArrayList<Object[]> rows) {
		DefaultTableModel dtm = (DefaultTableModel) table.table.getModel();
		dtm.setRowCount(0);
		for (Object[] row: rows) {
	        dtm.addRow(row);
		}
	}
	
}
