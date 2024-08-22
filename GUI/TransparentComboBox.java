package cmp.GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class TransparentComboBox<E> extends JComboBox<E> {

    public TransparentComboBox(E[] items) {
        super(items);
        customizeUI();
    }

    public TransparentComboBox() {
        super();
        customizeUI();
    }

    private void customizeUI() {
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new JButton();
                // Make the button transparent
                arrowButton.setOpaque(false);
                arrowButton.setContentAreaFilled(false);
                arrowButton.setBorderPainted(false);
                return arrowButton;
            }
        });
    }
}
