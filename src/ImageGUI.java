import javax.swing.*;
import java.awt.*;

public class ImageGUI extends JFrame {
    private JPanel mainPanel;
    public ImageIcon img;
    private JLabel mLabel;
    private JLabel mLabel2;
    public JMenuBar menuBar;

    public ImageGUI() {
        super();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // main panel
        mainPanel = new JPanel(new BorderLayout());
        mLabel = new JLabel();
        mLabel2 = new JLabel();
        mainPanel.add(mLabel, BorderLayout.LINE_START);
        mainPanel.add(mLabel2, BorderLayout.LINE_END);

        menuBar = new JMenuBar();
        JMenuItem menu1 = new JMenuItem("Otwórz obrazek");
        menu1.addActionListener(e -> {
            ImageEditEngine.fileDialog();
            setImageInWindow();
        });

        menuBar.add(menu1);

        mainPanel.add(menuBar, BorderLayout.PAGE_START);

        add(mainPanel);

        setSize(new Dimension(800, 600));

        mLabel2.setIcon(img);
        mLabel2.setVisible(true);
        this.repaint();
        pack();
    }

    private void setImageInWindow() {
        img = ImageEditEngine.openImage();
        mLabel.setIcon(img);
        mLabel.setVisible(true);
        this.repaint();
        pack();
    }
}