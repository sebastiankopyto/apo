package app.gui.menu;

import app.ImageEditEngine;
import app.gui.lab3.Lab3GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Lab3SubMenuGUI extends JMenu {
    public Lab3SubMenuGUI(JLabel mLabel) {
        super("Lab3");

        JMenuItem chooseSecondImage = new JMenuItem("Drugi obrazek...");
        chooseSecondImage.addActionListener(e -> {
            System.out.println("Wybierz drugi obrazek");

            ImageEditEngine.openFileChooseDialog();
            ImageIcon image = ImageEditEngine.openImage();
            if(image != null) {
                Lab3GUI okienko = new Lab3GUI((ImageIcon)mLabel.getIcon(), image);
                okienko.setVisible(true);
            }
        });

        this.add(chooseSecondImage);
    }

    public Lab3SubMenuGUI(JLabel image1, JLabel image2, JLabel result) {
        super("Lab3");
        JMenuItem dodajObrazZWysyceniem = new JMenuItem("Dodaj obrazki");
        dodajObrazZWysyceniem.addActionListener(e -> {
            ImageIcon ico1 = (ImageIcon)image1.getIcon();
            ImageIcon ico2 = (ImageIcon)image2.getIcon();

            Image img1 = ico1.getImage();
            Image img2 = ico2.getImage();

            int w = img1.getWidth(null);
            int h = img1.getHeight(null);

            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            Graphics g = combined.getGraphics();
            g.drawImage(img1, 0, 0, null);
            g.drawImage(img2, 0, 0, null);

            g.dispose();

            result.setIcon(new ImageIcon(combined));
        });

        this.add(dodajObrazZWysyceniem);

        JMenuItem dodajLiczbeCalkowita = new JMenuItem("Dodaj liczbę całkowitą");
        dodajLiczbeCalkowita.addActionListener(e -> {
            System.out.println("Dodaj liczbę całkowitą");
        });

        this.add(dodajLiczbeCalkowita);

        JMenuItem dzielPrzezLiczbeCalkowita = new JMenuItem("Dziel przez liczbę całkowitą");
        dzielPrzezLiczbeCalkowita.addActionListener(e -> {
            System.out.println("Dziel przez liczbę całkowitą");
        });

        this.add(dzielPrzezLiczbeCalkowita);

        JMenuItem mnozPrzezLiczbeCalkowita = new JMenuItem("Mnóż przez liczbę całkowitą");
        mnozPrzezLiczbeCalkowita.addActionListener(e -> {
            System.out.println("Mnóż przez liczbę całkowitą");
        });

        this.add(mnozPrzezLiczbeCalkowita);

        JMenuItem roznicaBezwzglednaObrazkow = new JMenuItem("Różnica bezwzględna obrazków");
        roznicaBezwzglednaObrazkow.addActionListener(e -> {
            System.out.println("Mnóż przez liczbę całkowitą");
        });

        this.add(roznicaBezwzglednaObrazkow);
    }
}
