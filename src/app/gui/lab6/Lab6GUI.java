package app.gui.lab6;

import app.gui.lab4.Lab4ToolsWindowGUI;

import javax.swing.*;
import java.awt.*;

public class Lab6GUI extends JFrame {

    private JLabel imageLabel;
    private JLabel resultLabel;

    public Lab6GUI(ImageIcon image1Icon) {
        super("Lab6 - obrazki");

        initWindow();
        imageLabel.setIcon(image1Icon);

        new Lab6ToolsWindowGUI(imageLabel, resultLabel).setVisible(true);
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

        imageLabel = new JLabel();
        resultLabel = new JLabel();

        mainPanel.add(imageLabel, BorderLayout.LINE_START);
        mainPanel.add(resultLabel, BorderLayout.PAGE_END);

        setSize(new Dimension(800, 800));
    }

}
