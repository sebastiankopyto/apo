package app.gui.lab5;

import javax.swing.*;
import java.awt.*;

public class Lab5GUI extends JFrame {
    private JLabel image1Label;
    private JLabel image2Label;
    private JSpinner spinner;

    public Lab5GUI() {
        super();
        initWindow();
    }

    public Lab5GUI(ImageIcon image1Icon, ImageIcon image2Icon) {
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

        SpinnerModel model = new SpinnerNumberModel(50, 0, 200, 1);
        spinner = new JSpinner(model);

        mainPanel.add(image1Label, BorderLayout.LINE_START);
        mainPanel.add(image2Label, BorderLayout.LINE_END);
        mainPanel.add(spinner, BorderLayout.PAGE_START);

        setSize(new Dimension(800, 800));
    }
}
