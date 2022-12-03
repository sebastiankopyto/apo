package app.gui.lab5;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WykrywanieKrawedziSobelGUI extends JFrame {
    private JLabel image1Label;
    private JLabel image2Label;
    private JSpinner spinner;
    private JComboBox comboBox;
    private JPanel options;

    public WykrywanieKrawedziSobelGUI() {
        super();
        initWindow();
    }

    public WykrywanieKrawedziSobelGUI(ImageIcon image1Icon) {
        this();

        image1Label.setIcon(image1Icon);
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
        options = new JPanel();

        comboBox = new TypWykrywaniaComboBox(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                System.out.println(comboBox.getSelectedItem());
            }
        });

        SpinnerModel model = new SpinnerNumberModel(50, 0, 200, 1);
        spinner = new JSpinner(model);

        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(spinner.getValue());
            }
        });

        options.add(comboBox);
        options.add(spinner);
        mainPanel.add(image1Label, BorderLayout.LINE_START);
        mainPanel.add(image2Label, BorderLayout.LINE_END);
        mainPanel.add(options, BorderLayout.PAGE_START);

        setSize(new Dimension(800, 800));
    }
}
