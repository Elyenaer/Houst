package components;

import java.util.ArrayList;

import javax.swing.JScrollPane;

public class CustomTable extends JScrollPane{
	private static final long serialVersionUID = 1L;
	ArrayList<Title> titles;

    public CustomTable() {
        init();
    }

    private void init() {
    	titles = new ArrayList<Title>();
    }
    
    public void addTitle(String title,int widthColumn) {
    	titles.add(new Title(title,widthColumn));
    }
    
    public void addValue(int column,int row,Object value) {
    	
    }	
}

class Title{
	String title;
	int widthColumn;
	
	Title(String title,int widthColumn){
		this.title = title;
		this.widthColumn = widthColumn;
	}
}
