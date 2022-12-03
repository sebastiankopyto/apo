package app.gui.menu;

import app.gui.lab3.Lab3GUI;
import app.gui.lab5.Lab5GUI;
import app.gui.lab5.WykrywanieKrawedziSobelGUI;
import app.utils.ImageUtils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Lab5SubMenuGUI extends JMenu {
    public Lab5SubMenuGUI(JLabel mLabel) {
        super("Lab5");

        JMenuItem detekcja = new JMenu("Detekcja");
        this.add(detekcja);

        JMenuItem detekcjaSobela = new JMenuItem("Detekcja krawędzi - Sobel");
        detekcjaSobela.addActionListener(e -> {
            System.out.println("Detekcja Sobela");

            ImageIcon icon = (ImageIcon) mLabel.getIcon();
            Image image = icon.getImage();

            BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

            byte[] pixels = ((DataBufferByte) bimage.getRaster().getDataBuffer()).getData();

            ImageIcon newIcon = new ImageIcon(bimage);

            // sprawdzam czy wygenerowałem 2 obrazki
            new Lab5GUI(icon, newIcon).setVisible(true);

            Mat imageMat = new Mat(bimage.getWidth(), bimage.getHeight(), CvType.CV_8UC3);
            imageMat.put(0, 0, pixels);

            Mat result = new Mat();

//            Applying sobel derivative with values x:0 y:1
//            Imgproc.Sobel(imageMat, result, -1, 0, 1);
//            Applying sobel derivative with values x:1 y:1
            Imgproc.Sobel(imageMat, result, -1, 1, 1);

            BufferedImage resultOfSobel = ImageUtils.convertMatToBufferedImage(result);

            new Lab5GUI(icon, new ImageIcon(resultOfSobel)).setVisible(true);

//            HighGui.imshow("Sobel - x:0 & y:1 ", result);
//            HighGui.waitKey(0);
//            Applying sobel derivative with values x:1 y:0
//            Imgproc.Sobel(imageMat, result, -1, 1, 0);
//            HighGui.imshow("Sobel - x:1 & y:0 ", result);
//            HighGui.waitKey();
//            Applying sobel derivative with values x:1 y:1
//            Imgproc.Sobel(imageMat, result, -1, 1, 1);
//            HighGui.imshow("Sobel - x:1 & y:1 ", result);
//            HighGui.waitKey();
        });

        detekcja.add(detekcjaSobela);

        JMenuItem detekcjaCannyego = new JMenuItem("Detekcja krawędzi - Canny");
        detekcjaCannyego.addActionListener(e -> {
            System.out.println("Detekcja Cannyego");

            ImageIcon icon = (ImageIcon) mLabel.getIcon();
            Image image = icon.getImage();

            BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

            byte[] pixels = ((DataBufferByte) bimage.getRaster().getDataBuffer()).getData();

            ImageIcon newIcon = new ImageIcon(bimage);

            // sprawdzam czy wygenerowałem 2 obrazki
            new Lab5GUI(icon, newIcon).setVisible(true);

            Mat imageMat = new Mat(bimage.getWidth(), bimage.getHeight(), CvType.CV_8UC3);
            imageMat.put(0, 0, pixels);

            Mat result = new Mat();

            Imgproc.Canny(imageMat, result, 300, 600, 5, true);

            BufferedImage resultOfSobel = ImageUtils.convertMatToBufferedImage(result);

            new Lab5GUI(icon, new ImageIcon(resultOfSobel)).setVisible(true);
        });

        detekcja.add(detekcjaCannyego);

        JMenuItem segmentacjaInteraktywna = new JMenuItem("Segmentacja interaktywna");
        segmentacjaInteraktywna.addActionListener(e -> {
            System.out.println("Segmentacja interatkywna");

            ImageIcon icon = (ImageIcon) mLabel.getIcon();
            Image image = icon.getImage();

            BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

            byte[] pixels = ((DataBufferByte) bimage.getRaster().getDataBuffer()).getData();

            ImageIcon newIcon = new ImageIcon(bimage);

            // sprawdzam czy wygenerowałem 2 obrazki
            new Lab5GUI(icon, newIcon).setVisible(true);

            Mat imageMat = new Mat(bimage.getWidth(), bimage.getHeight(), CvType.CV_8UC3);
            imageMat.put(0, 0, pixels);

            Mat result = new Mat();

            Imgproc.Canny(imageMat, result, 300, 600, 5, true);

            BufferedImage resultOfSobel = ImageUtils.convertMatToBufferedImage(result);

            new Lab5GUI(icon, new ImageIcon(resultOfSobel)).setVisible(true);
        });

        this.add(segmentacjaInteraktywna);

        JMenuItem test = new JMenuItem("test");
        test.addActionListener(e -> {
            new WykrywanieKrawedziSobelGUI((ImageIcon)mLabel.getIcon()).setVisible(true);
        });

        this.add(test);
    }


}
