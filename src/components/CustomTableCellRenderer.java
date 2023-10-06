package components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> targetRow;
    private int targetColumn;
    private Color backgroundColor;
    private Color foregroundColor;
    private boolean isBold;

    public CustomTableCellRenderer(ArrayList<Integer> targetRow, int targetColumn, Color backgroundColor, Color foregroundColor, boolean isBold) {
        this.targetRow = targetRow;
        this.targetColumn = targetColumn;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.isBold = isBold;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == targetColumn && targetRow.contains(row)) {
            rendererComponent.setBackground(backgroundColor);
            if (foregroundColor != null) {
                rendererComponent.setForeground(foregroundColor);
            }
            if (isBold) {
                Font font = rendererComponent.getFont();
                rendererComponent.setFont(font.deriveFont(Font.BOLD));
            } else {
                Font font = rendererComponent.getFont();
                rendererComponent.setFont(font.deriveFont(Font.PLAIN));
            }
        } else {
            rendererComponent.setBackground(table.getBackground());
            rendererComponent.setForeground(table.getForeground());
        }

        return rendererComponent;
    }
}