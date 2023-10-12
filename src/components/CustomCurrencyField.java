package components;

import java.math.BigDecimal;

import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import setting.desing.Design;
import support.FunctionBigDecimal;

public class CustomCurrencyField extends JFormattedTextField{
	private static final long serialVersionUID = 1L;

	public CustomCurrencyField() {
        init();
    }

    private void init() {
        setHorizontalAlignment(RIGHT);
        setBackground(Design.componentsBackground2);
		setForeground(Design.componentsForeground2);
    }
    
    protected Document createDefaultModel() {
	      return new CurrencyMask(9,2);
  }
    
    public BigDecimal getValue() {
    	return FunctionBigDecimal.stringToBigDecimal(this.getText());
    }
    
    static class CurrencyMask extends PlainDocument {
    	private static final long serialVersionUID = 1L;
    	private int iMaxLength;
        private int iQtdDec;
        private String sMil;
        private String sDec;
        
        public CurrencyMask(final int maxLen) {
            this(maxLen, 2, '.', ',');
        }
        
        public CurrencyMask(final int maxLen, final int qtdDec) {
            this(maxLen, qtdDec, '.', ',');
        }
        
        public CurrencyMask(final int maxLen, final int qtdDec, final char mil, final char dec) {
            this.iMaxLength = 0;
            if (maxLen > 0) {
                this.iMaxLength = maxLen;
            }
            this.iQtdDec = 0;
            if (qtdDec > 0) {
                this.iQtdDec = qtdDec;
            }
            this.sMil = Character.toString(mil);
            this.sDec = Character.toString(dec);
        }
        
        @Override
        public void insertString(final int offs, String str, final AttributeSet attrSet) throws BadLocationException {
            String texto = this.getText(0, this.getLength());
            String sinal = "";
            if (texto.indexOf("-") >= 0) {
                sinal = "-";
                texto = texto.replace((CharSequence)"-", (CharSequence)"");
            }
            while (texto.length() > 0 && (texto.charAt(0) == '0' || texto.charAt(0) == this.sDec.charAt(0))) {
                texto = texto.substring(1);
            }
            for (int i = 0; i < str.length(); ++i) {
                final char c = str.charAt(i);
                if (!Character.isDigit(c) && !this.sMil.equals(Character.toString(c)) && (!this.sDec.equals(Character.toString(c)) || this.iQtdDec <= 0) && (texto.length() != 0 || i != 0 || !"-".equals(Character.toString(c)))) {
                    return;
                }
            }
            texto = texto.replace((CharSequence)this.sMil, (CharSequence)"");
            if (texto.length() < this.iMaxLength || this.iMaxLength <= 0) {
                texto = texto.replace((CharSequence)this.sDec, (CharSequence)"");
                if (str.indexOf("-") >= 0) {
                    sinal = "-";
                    str = str.replace((CharSequence)"-", (CharSequence)"");
                }
                final StringBuffer s = new StringBuffer(texto + str.replace((CharSequence)this.sDec, (CharSequence)"").replace((CharSequence)this.sMil, (CharSequence)""));
                while (s.length() > 0 && s.charAt(0) == '0') {
                    s.deleteCharAt(0);
                }
                if (s.length() + ((this.iQtdDec != 0) ? 1 : 0) > this.iMaxLength && this.iMaxLength != 0) {
                    return;
                }
                for (int j = s.length(); j <= this.iQtdDec; ++j) {
                    s.insert(0, "0");
                }
                if (this.iQtdDec > 0) {
                    s.insert(s.length() - this.iQtdDec, this.sDec);
                }
                int iPos = this.iQtdDec;
                int nQtdDec = this.iQtdDec;
                if (nQtdDec > 0) {
                    ++iPos;
                    ++nQtdDec;
                }
                int iQtdMil = 0;
                while (iPos < s.length() - 1) {
                    if ((++iPos - nQtdDec) % 3 == 0 && iPos < s.length() - iQtdMil) {
                        s.insert(s.length() - iPos - iQtdMil, this.sMil);
                        ++iQtdMil;
                    }
                }
                if (sinal.equals("-")) {
                    s.insert(0, "-");
                }
                super.remove(0, this.getLength());
                super.insertString(0, s.toString(), attrSet);
            }
        }
        
        @Override
        public void remove(final int offset, final int length) throws BadLocationException {
            super.remove(offset, length);
            String texto = this.getText(0, this.getLength());
            texto = texto.replace((CharSequence)this.sMil, (CharSequence)"");
            texto = texto.replace((CharSequence)this.sDec, (CharSequence)"");
            try {
                final double dValue = Double.parseDouble(texto);
                if (dValue == 0.0) {
                    texto = "";
                }
            }
            catch (Exception ex) {}
            super.remove(0, this.getLength());
            this.insertString(0, texto, null);
        }
    }
    
}


