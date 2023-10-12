package components;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;

import setting.desing.Design;

public class CustomComboBox extends JComboBox<String> {
    private static final long serialVersionUID = 1L;

    public CustomComboBox() {
        setBackground(Design.mainBackground);
        setRenderer(new CustomComboBoxRenderer());
    }

    private class CustomComboBoxRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            setBackground(Design.componentsBackground2);
            setForeground(Design.componentsForeground2);

            return this;
        }
    }
}