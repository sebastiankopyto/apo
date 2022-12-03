package app.gui.imgegui;

import app.gui.menu.MenuGUI;

import javax.swing.*;
import java.awt.*;

public class ImageGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JPanel toolsPanel;
    private JLabel imageLabel;
    public JMenuBar menuBar;

    public ImageGUI() {
        super();
        initWindow();
    }

    public ImageGUI(ImageIcon imageIcon) {
        this();

        imageLabel.setIcon(imageIcon);
    }

    private void initWindow() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // main panel
        Container mainPanel = this.getContentPane();

        imagePanel = new JPanel();
        imagePanel.setMaximumSize(new Dimension(800, 600));
        imagePanel.setPreferredSize(new Dimension(800, 600));
        imagePanel.setMinimumSize(new Dimension(800, 600));
        imagePanel.setAutoscrolls(true);
        imageLabel = new JLabel();

        imagePanel.add(imageLabel, BorderLayout.LINE_START);

        toolsPanel = new JPanel();
        toolsPanel.setMaximumSize(new Dimension(800, 600));
        toolsPanel.setPreferredSize(new Dimension(800, 600));
        toolsPanel.setMinimumSize(new Dimension(800, 600));
        toolsPanel.setAutoscrolls(true);

        mainPanel.add(imagePanel, BorderLayout.LINE_START);
        mainPanel.add(toolsPanel, BorderLayout.LINE_END);

        // menuBar
        menuBar = new MenuGUI(this, imageLabel, toolsPanel);
        this.setJMenuBar(menuBar);

        setSize(new Dimension(800, 800));
    }
}