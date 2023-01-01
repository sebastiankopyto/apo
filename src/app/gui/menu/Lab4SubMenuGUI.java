package app.gui.menu;

import app.gui.lab3.Lab3GUI;
import app.gui.lab4.Lab4GUI;

import javax.swing.*;

public class Lab4SubMenuGUI extends JMenu {
    public Lab4SubMenuGUI(JLabel mLabel) {
        super("Lab4");

        JMenuItem chooseSecondImage = new JMenuItem("Otwórz panel narzędzi");
        chooseSecondImage.addActionListener(e -> {
            Lab4GUI okienko = new Lab4GUI((ImageIcon) mLabel.getIcon());
            okienko.setVisible(true);
        });

        this.add(chooseSecondImage);
    }
}
