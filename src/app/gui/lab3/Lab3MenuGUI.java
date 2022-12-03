package app.gui.lab3;

import app.gui.menu.*;

import javax.swing.*;

public class Lab3MenuGUI extends JMenuBar {
    public Lab3MenuGUI(JLabel mLabel) {
        this.add(new Lab3SubMenuGUI(mLabel));
    }

    public Lab3MenuGUI(JLabel firstImg, JLabel secondImg, JLabel resultImg ) {
        this.add(new Lab3SubMenuGUI(firstImg, secondImg, resultImg));
    }
}
