package app.gui.lab4;

import app.core.ImageBlurring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Lab4ToolsWindowGUI extends JFrame {

    private JLabel obrazekLabel;
    private JLabel wynikowaLabel;

    private JComboBox typOperacjiCombo;
    private JComboBox operacjaWygladzanieCombo;
    private JComboBox operacjaWyostrzanieCombo;
    private JComboBox operacjaDetekcjaKrawedziCombo;
    private JComboBox wygladzanieMarginesowCombo;
    private JButton zastosujButton;

    public Lab4ToolsWindowGUI(JLabel imageLabel, JLabel resultLabel) {
        super("Panel - Lab4");

        obrazekLabel = imageLabel;
        wynikowaLabel = resultLabel;

        GridLayout windowLayout = new GridLayout(0, 2, 10, 10);
        setLayout(windowLayout);
        setSize(new Dimension(300, 300));

        inicjujKomponenty();
        inicjujWidok();

    }

    private void inicjujKomponenty() {

        String[] typOperacjiOptions = {"Wygładzanie", "Wyostrzanie liniowe", "Kierunkowa detekcja krawędzi"};
        typOperacjiCombo = new JComboBox<>(new DefaultComboBoxModel<>(typOperacjiOptions));

        String[] operacjaWygladzanieOptions = {"Uśrednienie", "Uśrednienie z wagami", "Filtr Gaussowski"};
        operacjaWygladzanieCombo = new JComboBox<>(new DefaultComboBoxModel<>(operacjaWygladzanieOptions));

        String[] operacjaWyostrzanieOptions = {"Wyostrzanie 1", "Wyostrzanie 2", "Wyostrzanie 3"};
        operacjaWyostrzanieCombo = new JComboBox<>(new DefaultComboBoxModel<>(operacjaWyostrzanieOptions));

        String[] operacjaDetekcjaKrawedziOptions = {"Detekcja 1", "Detekcja 2", "Detekcja 3"};
        operacjaDetekcjaKrawedziCombo = new JComboBox<>(new DefaultComboBoxModel<>(operacjaDetekcjaKrawedziOptions));

        String[] metodyWygladzaniaMarginesowOptions = {"BORDER_CONSTANT", "N", "BORDER_REFLECT", "BORDER_WRAP"};
        wygladzanieMarginesowCombo = new JComboBox<>(new DefaultComboBoxModel<>(metodyWygladzaniaMarginesowOptions));

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
        add(new JLabel("Typ operacji", SwingConstants.RIGHT));
        add(typOperacjiCombo);

        add(new JLabel("Operacja - wygładzanie", SwingConstants.RIGHT));
        add(operacjaWygladzanieCombo);

        add(new JLabel("Operacja - wyostrzanie", SwingConstants.RIGHT));
        add(operacjaWyostrzanieCombo);

        add(new JLabel("Operacja - detekcja krawędzi", SwingConstants.RIGHT));
        add(operacjaDetekcjaKrawedziCombo);

        add(new JLabel("Metoda wygładzania marginesów", SwingConstants.RIGHT));
        add(wygladzanieMarginesowCombo);

        add(zastosujButton);
        add(new JLabel("Zastosuj zmiany"));
    }

    private void zastosujListener() {
        String typOperacji = (String) typOperacjiCombo.getSelectedItem();
        String operacjaWygladzania = (String) operacjaWygladzanieCombo.getSelectedItem();
        String operacjaWyostrzania = (String) operacjaWyostrzanieCombo.getSelectedItem();
        String operacjaDetekcjaKrawedzi = (String) operacjaDetekcjaKrawedziCombo.getSelectedItem();
        String metodaWygladzaniaMarginesow = (String) wygladzanieMarginesowCombo.getSelectedItem();

        switch (typOperacji) {
            case "Wygładzanie": {

                switch (operacjaWygladzania) {
                    case "Uśrednienie": {
                        ImageBlurring imageBlurring = new ImageBlurring((BufferedImage) ((ImageIcon) obrazekLabel.getIcon()).getImage());
//                        BufferedImage newImage = imageBlurring.blur();
                        BufferedImage newImage = imageBlurring.testSource();

                        wynikowaLabel.setIcon(new ImageIcon(newImage));
                        break;
                    }
                    case "Uśrednienie z wagami": {
                        ImageBlurring imageBlurring = new ImageBlurring((BufferedImage) ((ImageIcon) obrazekLabel.getIcon()).getImage());
                        BufferedImage newImage = imageBlurring.blurWithWeight();

                        wynikowaLabel.setIcon(new ImageIcon(newImage));
                        break;
                    }
                    case "Filtr Gaussowski": {
                        ImageBlurring imageBlurring = new ImageBlurring((BufferedImage) ((ImageIcon) obrazekLabel.getIcon()).getImage());
                        BufferedImage newImage = imageBlurring.gaussianBlur();

                        wynikowaLabel.setIcon(new ImageIcon(newImage));
                        break;
                    }
                }

                break;
            }
            case "Wyostrzanie liniowe": {

                switch (operacjaWyostrzania) {
                    case "Wyostrzanie 1": {
                        break;
                    }
                    case "Wyostrzanie 2": {
                        break;
                    }
                    case "Wyostrzanie 3": {
                        break;
                    }
                }

                break;
            }
            case "Kierunkowa detekcja krawędzi": {

                break;
            }
        }
    }
}
