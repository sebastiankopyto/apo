package app.gui.lab5;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TypWykrywaniaComboBox extends JComboBox {


    public TypWykrywaniaComboBox(AbstractAction abstractAction) {
        super();
        this.addActionListener(abstractAction);
        String[] comboBoxOptions = {"Sobel", "Prewitt"};
        this.setModel(new DefaultComboBoxModel(comboBoxOptions));
    }
}
