package app.gui.menu;

import app.core.BinaryOperations;
import app.gui.lab3.Lab3GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Lab3SubMenuGUI extends JMenu {
    public Lab3SubMenuGUI(JLabel mLabel) {
        super("Lab3");

        JMenuItem chooseSecondImage = new JMenuItem("Otwórz panel narzędzi");
        chooseSecondImage.addActionListener(e -> {
            Lab3GUI okienko = new Lab3GUI((ImageIcon) mLabel.getIcon());
            okienko.setVisible(true);
        });

        this.add(chooseSecondImage);
    }

    public Lab3SubMenuGUI(JLabel image1, JLabel image2, JLabel result) {
        super("Lab3");
        JMenuItem dodajObrazZWysyceniem = new JMenuItem("Dodaj obrazki z wysyceniem");
        dodajObrazZWysyceniem.addActionListener(e -> {
            ImageIcon ico1 = (ImageIcon) image1.getIcon();
            ImageIcon ico2 = (ImageIcon) image2.getIcon();

            Image img1 = ico1.getImage();
            Image img2 = ico2.getImage();

            BinaryOperations binaryOperations = new BinaryOperations((BufferedImage) img1, (BufferedImage) img2);

            result.setIcon(new ImageIcon(binaryOperations.addImages(true)));
        });

        this.add(dodajObrazZWysyceniem);

        JMenuItem dodajObrazBezWysycenia = new JMenuItem("Dodaj obrazki bez wysycenia");
        dodajObrazBezWysycenia.addActionListener(e -> {
            ImageIcon ico1 = (ImageIcon) image1.getIcon();
            ImageIcon ico2 = (ImageIcon) image2.getIcon();

            Image img1 = ico1.getImage();
            Image img2 = ico2.getImage();

            BinaryOperations binaryOperations = new BinaryOperations((BufferedImage) img1, (BufferedImage) img2);

            result.setIcon(new ImageIcon(binaryOperations.addImages(false)));
        });

        this.add(dodajObrazBezWysycenia);

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
