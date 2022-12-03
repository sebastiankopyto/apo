package app.gui.menu;

import javax.swing.*;

public class MenuGUI extends JMenuBar {
    JLabel mainScreenLabel;
    JFrame mainGui;

    JPanel toolsPanel;

    public MenuGUI(JFrame gui, JLabel mLabel, JPanel toolsPanel ) {
        this.mainScreenLabel = mLabel;
        this.mainGui = gui;
        this.toolsPanel = toolsPanel;

        this.add(new ImageMenuGUI(gui, mLabel));
        this.add(new Lab1SubMenuGUI(gui, mLabel, toolsPanel));
        this.add(new Lab2SubMenuGUI());
        this.add(new Lab3SubMenuGUI(mLabel));
        this.add(new Lab4SubMenuGUI());
        this.add(new Lab5SubMenuGUI(mLabel));
    }
}
