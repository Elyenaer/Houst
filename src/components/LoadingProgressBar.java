package components;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import setting.desing.Design;
import setting.desing.DesignIcon;

public class LoadingProgressBar extends JDialog {
    private static final long serialVersionUID = 1L;
    private CustomLabel label;
    private String title;
    private JProgressBar progress;

    public LoadingProgressBar(String title, int maxValue, JFrame frame) {
        init(title, maxValue, frame);
    }

    private void init(String title, int maxValue, JFrame frame) {
        this.setIconImage(DesignIcon.icon16x16());
        setSize(600, 150);
        setLocationRelativeTo(frame);
        getContentPane().setBackground(Design.componentsBackground2);
        this.title = title;

        setLayout(new BorderLayout(30, 30));

        JPanel innerPanel = new JPanel(new GridLayout(2, 1));
        innerPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        innerPanel.setBackground(Design.componentsBackground2);
        add(innerPanel, BorderLayout.CENTER);

        label = new CustomLabel(title);
        label.setHorizontalAlignment(JLabel.CENTER);
        innerPanel.add(label);

        progress = new JProgressBar();
        progress.setMaximum(maxValue);
        innerPanel.add(progress);
    }

    public void setValue(int value) {
        progress.setValue(value);
        setTitle();
    }

    public void setValueOne() {
        progress.setValue(progress.getValue() + 1);
        setTitle();
    }

    private void setTitle() {
        label.setText(title + " " + progress.getValue() + "/" + progress.getMaximum());
    }

    public void showDialog(boolean visible) {
        this.setVisible(visible);
        if (!visible) {
            dispose();
        }
    }
}
