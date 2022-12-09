package app.gui.lab3;

import app.ImageEditEngine;
import app.core.BinaryOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Lab3OptionsWindowGUI extends JFrame {

    private JLabel pierwszaLabel;
    private JLabel drugaLabel;
    private JLabel wynikowaLabel;

    private JButton wybierzObrazekButton;
    private JSpinner wartoscLiczbowa;
    private JComboBox typOperacjiCombo;
    private JComboBox operacjaObrazekCombo;
    private JComboBox operacjaLiczbaCombo;
    private JComboBox operacjaLogicznaCombo;
    private JCheckBox wysycenieCheckbox;
    private JButton zastosujButton;


    public Lab3OptionsWindowGUI(JLabel firstLabel, JLabel secondLabel, JLabel resultLabel) {
        super("Panel - Lab3");

        pierwszaLabel = firstLabel;
        drugaLabel = secondLabel;
        wynikowaLabel = resultLabel;

        GridLayout windowLayout = new GridLayout(0, 2, 10, 10);
        setLayout(windowLayout);
        setSize(new Dimension(300, 300));

        inicjujKomponenty();
        inicjujWidok();
    }

    private void inicjujKomponenty() {
        wybierzObrazekButton = new JButton("Wybierz...");
        wybierzObrazekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybierzObrazekListener();
            }
        });


        String[] typOperacjiOptions = {"Obrazek", "Liczba", "Logiczna"};
        typOperacjiCombo = new JComboBox<>(new DefaultComboBoxModel<>(typOperacjiOptions));

        SpinnerModel model = new SpinnerNumberModel(50, 0, 255, 1);
        wartoscLiczbowa = new JSpinner(model);

        String[] operacjaObrazekOptions = {"Dodaj", "Binaryzacja"};
        operacjaObrazekCombo = new JComboBox<>(new DefaultComboBoxModel<>(operacjaObrazekOptions));

        String[] operacjaLiczbaOptions = {"Dodaj", "Mnóż", "Dziel"};
        operacjaLiczbaCombo = new JComboBox<>(new DefaultComboBoxModel<>(operacjaLiczbaOptions));

        String[] operacjaLogicznaOptions = {"NOT", "AND", "OR", "XOR"};
        operacjaLogicznaCombo = new JComboBox<>(new DefaultComboBoxModel<>(operacjaLogicznaOptions));

        wysycenieCheckbox = new JCheckBox("Wysycenie");

        zastosujButton = new JButton("Zastosuj");
        zastosujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zastosujListener();
            }
        });
    }

    ;

    private void inicjujWidok() {
        add(new JLabel("Obrazek", SwingConstants.RIGHT));
        add(wybierzObrazekButton);

        add(new JLabel("Typ operacji", SwingConstants.RIGHT));
        add(typOperacjiCombo);

        add(new JLabel("Wartosc", SwingConstants.RIGHT));
        add(wartoscLiczbowa);

        add(new JLabel("Operacja - obrazek", SwingConstants.RIGHT));
        add(operacjaObrazekCombo);

        add(new JLabel("Operacja - liczba", SwingConstants.RIGHT));
        add(operacjaLiczbaCombo);

        add(new JLabel("Operacja - logiczna", SwingConstants.RIGHT));
        add(operacjaLogicznaCombo);

        add(new JLabel("Czy wysycenie?", SwingConstants.RIGHT));
        add(wysycenieCheckbox);

        add(zastosujButton);
        add(new JLabel(""));
    }

    private void wybierzObrazekListener() {
        ImageEditEngine.openFileChooseDialog();
        ImageIcon image = ImageEditEngine.openImage();
        if (image != null) {
            drugaLabel.setIcon(image);
        }
    }

    private void zastosujListener() {
        System.out.println("Button press");

        String opcja = (String) typOperacjiCombo.getSelectedItem();

        switch (opcja) {
            case "Obrazek": {
                String operacjaObrazkowa = (String) operacjaObrazekCombo.getSelectedItem();
                int wybranaLiczba = (int) wartoscLiczbowa.getValue();
                boolean wysycenie = wysycenieCheckbox.isSelected();

                switch (operacjaObrazkowa) {
                    case "Dodaj": {
                        ImageIcon ico1 = (ImageIcon) pierwszaLabel.getIcon();
                        ImageIcon ico2 = (ImageIcon) drugaLabel.getIcon();

                        Image img1 = ico1.getImage();
                        Image img2 = ico2.getImage();

                        BinaryOperations bo = new BinaryOperations((BufferedImage) img1, (BufferedImage) img2);
                        BufferedImage result = bo.addImages(wysycenie);

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }
                    case "Binaryzacja": {
                        ImageIcon ico1 = (ImageIcon) pierwszaLabel.getIcon();
                        Image img1 = ico1.getImage();

                        BinaryOperations bo = new BinaryOperations((BufferedImage) img1);
                        BufferedImage result = bo.binary(wybranaLiczba);
                        wynikowaLabel.setIcon(new ImageIcon(result));

                    }
                }
                break;
            }
            case "Liczba": {
                String operacjaLiczbowa = (String) operacjaLiczbaCombo.getSelectedItem();
                int wybranaLiczba = (int) wartoscLiczbowa.getValue();
                boolean wysycenie = wysycenieCheckbox.isSelected();

                ImageIcon ico1 = (ImageIcon) pierwszaLabel.getIcon();

                Image img1 = ico1.getImage();

                BinaryOperations bo = new BinaryOperations((BufferedImage) img1);

                switch(operacjaLiczbowa) {
                    case "Dodaj": {
                        BufferedImage result = bo.numberOperation("add", wybranaLiczba, wysycenie);

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }

                    case "Mnóż": {
                        BufferedImage result = bo.numberOperation("multiply", wybranaLiczba, wysycenie);

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }

                    case "Dziel": {
                        BufferedImage result = bo.numberOperation("divide", wybranaLiczba, wysycenie);

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }
                }
                break;
            }
            case "Logiczna": {
                String operacjaLogiczna = (String) operacjaLogicznaCombo.getSelectedItem();

                ImageIcon ico1 = (ImageIcon) pierwszaLabel.getIcon();

                Image img1 = ico1.getImage();


//                String[] operacjaLogicznaOptions = {"NOT", "AND", "OR", "XOR"};

                switch(operacjaLogiczna) {
                    case "NOT": {
                        BinaryOperations bo = new BinaryOperations((BufferedImage) img1);
                        BufferedImage result = bo.not();

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }
                    case "AND": {
                        ImageIcon ico2 = (ImageIcon) drugaLabel.getIcon();
                        Image img2 = ico2.getImage();
                        BinaryOperations bo = new BinaryOperations((BufferedImage) img1, (BufferedImage) img2);
                        BufferedImage result = bo.logicalOperations("and");

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }
                    case "OR": {
                        ImageIcon ico2 = (ImageIcon) drugaLabel.getIcon();
                        Image img2 = ico2.getImage();
                        BinaryOperations bo = new BinaryOperations((BufferedImage) img1, (BufferedImage) img2);
                        BufferedImage result = bo.logicalOperations("or");

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }
                    case "XOS": {
                        ImageIcon ico2 = (ImageIcon) drugaLabel.getIcon();
                        Image img2 = ico2.getImage();
                        BinaryOperations bo = new BinaryOperations((BufferedImage) img1, (BufferedImage) img2);
                        BufferedImage result = bo.logicalOperations("xor");

                        wynikowaLabel.setIcon(new ImageIcon(result));
                        break;
                    }
                }

                break;
            }
        }
    }
}
