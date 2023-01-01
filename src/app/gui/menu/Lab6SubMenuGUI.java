package app.gui.menu;

import app.gui.lab3.Lab3GUI;
import app.gui.lab5.Lab5GUI;
import app.gui.lab5.WykrywanieKrawedziSobelGUI;
import app.gui.lab6.Lab6GUI;
import app.utils.ImageUtils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Lab6SubMenuGUI extends JMenu {
    public Lab6SubMenuGUI(JLabel mLabel) {
        super("Lab6");

        JMenuItem openToolsMenu = new JMenuItem("Otwórz panel narzędzi");
        openToolsMenu.addActionListener(e -> {
            Lab6GUI okienko = new Lab6GUI((ImageIcon) mLabel.getIcon());
            okienko.setVisible(true);
        });

        this.add(openToolsMenu);
    }


}
