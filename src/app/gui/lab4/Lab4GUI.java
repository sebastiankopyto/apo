package app.gui.lab4;

import javax.swing.*;
import java.awt.*;

public class Lab4GUI extends JFrame {

    private JLabel imageLabel;
    private JLabel resultLabel;

    public Lab4GUI(ImageIcon image1Icon) {
        super("Lab4 - obrazki");

        initWindow();
        imageLabel.setIcon(image1Icon);

        new Lab4ToolsWindowGUI(imageLabel, resultLabel).setVisible(true);
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
