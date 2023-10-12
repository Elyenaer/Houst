package components;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import setting.desing.Design;

public class CustomNumberField extends JTextField {
	private static final long serialVersionUID = 1L;

	public CustomNumberField() {
		init();
    }
	
	private void init() {
		setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
	}

    protected Document createDefaultModel() {
	      return new NumberDocument();
    }
    
    public int getInt() {
    	return Integer.parseInt(this.getText());
    }

    static class NumberDocument extends PlainDocument {
		private static final long serialVersionUID = 1L;

		public void insertString(int offs, String str, AttributeSet a) 
	          throws BadLocationException {

	          if (str == null) {
		      return;
	          }
	          for (int i = 0; i < str.length(); i++) {
	        	  if(Character.isDigit(str.charAt(i))== false){
	        		  return;
	        	  }
	          }
	          super.insertString(offs, new String(str), a);
	      }
    }
}