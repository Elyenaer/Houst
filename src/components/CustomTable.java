package components;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import setting.desing.Design;

public class CustomTable extends JScrollPane{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> titles;
	public JTable table;
	
    public CustomTable(ArrayList<String> titles) {    	
    	this.titles = titles;
        init();
    }
    
    public CustomTable(Object[] titles) {    	
    	convertTitle(titles);
        init();
    }
    
    public CustomTable() {    	
    	this.titles = new ArrayList<String>();
        init();
    }
    
    private void convertTitle(Object[] titles) {
    	this.titles = new ArrayList<String>();
    	for(Object o: titles) {
    		this.titles.add(o.toString());
    	}
    }
    
    private void init() {        	
    	this.setOpaque(false);
    	this.setBorder(new EmptyBorder(0, 0, 0, 0));
    	this.setBackground(new Color(0,0,0,0));
    	this.setFont(Design.getFont(15,true));
    	this.setBounds(3, 101, 707, 297);
    	 
    	table = new JTable();
    	table.setBorder(new EmptyBorder(0, 0, 0, 0));
    	table.setOpaque(false);
    	     	
    	String t[] = new String[titles.size()];
    	boolean b[] = new boolean[titles.size()];
    	for(int i=0;i<titles.size();i++) {
    		t[i] = titles.get(i);
    		b[i] = false;
    	}    	
    	table.setModel(new DefaultTableModel(new Object[0][],t) {
            private static final long serialVersionUID = 1L;
			boolean[] canEdit = b;            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });    	    	
    	
    	DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
    	headerRenderer.setFont(Design.getFont(10,true));
        headerRenderer.setBackground(Design.componentsBackground2);
        headerRenderer.setForeground(Design.componentsForeground2);        
        table.getTableHeader().setDefaultRenderer(headerRenderer);
                
    	table.setBackground(new Color(0,0,0,0));
    	table.setForeground(Design.componentsForeground);
    	table.setFont(Design.getFont(10,false));
    	table.setFillsViewportHeight(false); 	
    	
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    	
    	this.setViewportView(table);
    	this.getViewport().setOpaque(false);
    	this.getViewport().setBackground(new Color(0,0,0,0));
    	this.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
    	 
    	table.setGridColor(Design.componentsBackground2);
    	table.setShowGrid(true);
    }
    
    public void setTitle(Object[] titles) {
    	convertTitle(titles);
        init();
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
    
    public void setColumnWidth(int column,int width) {
    	table.getColumnModel().getColumn(column).setPreferredWidth(width);
    }
    
    public void removeRows() {
    	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
    }
    
	public void setRows(ArrayList<Object[]> rows) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		for (Object[] row: rows) {
	        dtm.addRow(row);
		}
	}
	
	//remove first to avoid set title
	public void setRowsRemoveFirst(ArrayList<Object[]> rows) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		for (int i=1;i<rows.size();i++) {
	        dtm.addRow(rows.get(i));
		}
	}
		
	public void setCellRenderer(ArrayList<Integer> rows,int column,Color background,Color foreground,boolean isBold) {
		CustomTableCellRenderer cellRenderer = new CustomTableCellRenderer(rows,column,background,foreground,isBold);
		table.getColumnModel().getColumn(column).setCellRenderer(cellRenderer);
		table.revalidate();
		table.repaint();
	}
	
	public void setTitleRenderer(ArrayList<Integer> columns,Color background,Color foreground,boolean isBold) {
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();		
    	headerRenderer.setFont(Design.getFont(10,isBold));
        headerRenderer.setBackground(background);
        headerRenderer.setForeground(foreground);  
        
        for(Integer c: columns) {
        	table.getColumnModel().getColumn(c).setHeaderRenderer(headerRenderer);
        }
        
        revalidate();
        repaint();	
	}
    
}
