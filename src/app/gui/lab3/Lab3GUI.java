package app.gui.lab3;

import app.gui.menu.MenuGUI;

import javax.swing.*;
import java.awt.*;

public class Lab3GUI extends JFrame {
    private JPanel mainPanel;
    private JPanel toolsPanel;
    private JLabel image1Label;
    private JLabel image2Label;
    private JLabel image3Label;
    public JMenuBar menuBar;

    public Lab3GUI() {
        super();
        initWindow();
    }

    public Lab3GUI(ImageIcon image1Icon, ImageIcon image2Icon) {
        this();

        image1Label.setIcon(image1Icon);
        image2Label.setIcon(image2Icon);
    }

    private void initWindow() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // main panel
        Container mainPanel = this.getContentPane();

        image1Label = new JLabel();
        image2Label = new JLabel();
        image3Label = new JLabel();

        mainPanel.add(image1Label, BorderLayout.LINE_START);
        mainPanel.add(image2Label, BorderLayout.LINE_END);
        mainPanel.add(image3Label, BorderLayout.PAGE_END);

        // menuBar
        menuBar = new Lab3MenuGUI(image1Label, image2Label, image3Label);
        this.setJMenuBar(menuBar);

        setSize(new Dimension(800, 800));
    }
}
