package app.gui.lab3;

import javax.swing.*;
import java.awt.*;

public class Lab3GUI extends JFrame {
    private JLabel image1Label;
    private JLabel image2Label;
    private JLabel image3Label;

    public Lab3GUI() {
        super("Lab 3 - Obrazki");
        initWindow();
    }

    public Lab3GUI(ImageIcon image1Icon) {
        this();
        image1Label.setIcon(image1Icon);

        new Lab3ToolsWindowGUI(image1Label, image2Label, image3Label).setVisible(true);
    }
    public Lab3GUI(ImageIcon image1Icon, ImageIcon image2Icon) {
        this();

        image1Label.setIcon(image1Icon);
        image2Label.setIcon(image2Icon);
    }

    public Lab3GUI(ImageIcon image1Icon, ImageIcon image2Icon, ImageIcon image3Icon) {
        this();

        image1Label.setIcon(image1Icon);
        image2Label.setIcon(image2Icon);
        image3Label.setIcon(image3Icon);
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

        setSize(new Dimension(800, 800));
    }
}
