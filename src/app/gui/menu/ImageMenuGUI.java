package app.gui.menu;

import app.ImageEditEngine;
import app.gui.imgegui.ImageGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageMenuGUI extends JMenu {
    JLabel imageLabel;
    JFrame mainGui;

    public ImageMenuGUI(JFrame gui, JLabel imageLabel) {
        super("Obraz");
        this.imageLabel = imageLabel;
        this.mainGui = gui;

        JMenuItem openButton = new JMenuItem("Otwórz...");
        openButton.addActionListener(e -> {
            ImageEditEngine.openFileChooseDialog();
            ImageIcon image = ImageEditEngine.openImage();
            if(image != null) {
                ImageGUI abc = new ImageGUI(image);
                abc.setVisible(true);
            }

//            ImageEditEngine.openFileChooseDialog();
//            setImageInWindow();
        });

        this.add(openButton);

        JMenuItem saveButton = new JMenuItem("Zapisz");
//        saveButton.setEnabled(false);
        saveButton.addActionListener(e -> {
            BufferedImage bufferedImage = ImageEditEngine.originalImageBuffer;
            String fullFileName = ImageEditEngine.imageFile.getName();
            String fileExtension = fullFileName.split("\\.")[1];
            File outputFile = new File(fullFileName);
            try {
                ImageIO.write(bufferedImage, fileExtension, outputFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            setImageInWindow();
        });

        this.add(saveButton);

        JMenuItem saveAsButton = new JMenuItem("Zapisz jako...");
        saveAsButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Wybierz gdzie zapisać obrazek");
            fileChooser.setSelectedFile(new File("kopia_" + ImageEditEngine.imageFile.getName()));

            int userSelection = fileChooser.showSaveDialog(mainGui);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                BufferedImage bufferedImage = ImageEditEngine.originalImageBuffer;
                String fullFileName = fileChooser.getSelectedFile().getAbsolutePath();
                String fileName = fullFileName.split("\\.")[0];
                String fileExtension = fullFileName.split("\\.")[1];
                File outputFile = new File(fileName + "save." + fileExtension);
                try {
                    ImageIO.write(bufferedImage, fileExtension, outputFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.add(saveAsButton);
    }

    private void setImageInWindow() {
        ImageIcon imageIcon = ImageEditEngine.openImage();

        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
            imageLabel.setVisible(true);
            imageLabel.setSize(new Dimension(400, 400));
            mainGui.repaint();
            mainGui.pack();
        }
    }
}
