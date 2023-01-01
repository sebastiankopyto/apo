package app.gui.lab6;

import app.core.AnalyzeBinaryObject;
import app.core.MorphologicalOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Lab6ToolsWindowGUI extends JFrame {

    private JLabel obrazekLabel;
    private JLabel wynikowaLabel;

    private JComboBox typDzialaniaCombo;
    private JComboBox typOperacjiCombo;
    private JComboBox typCechyCombo;
    private JButton zastosujButton;

    public Lab6ToolsWindowGUI(JLabel imageLabel, JLabel resultLabel) {
        super("Panel - Lab4");

        obrazekLabel = imageLabel;
        wynikowaLabel = resultLabel;

        GridLayout windowLayout = new GridLayout(0, 2, 10, 10);
        setLayout(windowLayout);
        setSize(new Dimension(300, 150));

        inicjujKomponenty();
        inicjujWidok();
    }

    private void inicjujKomponenty() {

        String[] typDzialaniaOptions = {"Morfologia matematyczna", "Wyznaczenie cech"};
        typDzialaniaCombo = new JComboBox<>(new DefaultComboBoxModel<>(typDzialaniaOptions));

        String[] typOperacjiOptions = {"erozja", "dylacja", "otwarcie", "zamknięcie"};
        typOperacjiCombo = new JComboBox<>(new DefaultComboBoxModel<>(typOperacjiOptions));

        String[] typCechyOptions = {"momenty"};
        typCechyCombo = new JComboBox<>(new DefaultComboBoxModel<>(typCechyOptions));

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
        add(new JLabel("Typ działania", SwingConstants.RIGHT));
        add(typDzialaniaCombo);

        add(new JLabel("Typ operacji morfologii", SwingConstants.RIGHT));
        add(typOperacjiCombo);

        add(new JLabel("Typ cechy", SwingConstants.RIGHT));
        add(typCechyCombo);

        add(zastosujButton);
        add(new JLabel("Zastosuj zmiany"));
    }

    private void zastosujListener() {
        String typDzialania = (String) typDzialaniaCombo.getSelectedItem();
        String typOperacji = (String) typOperacjiCombo.getSelectedItem();
        String typCechy = (String) typCechyCombo.getSelectedItem();

        MorphologicalOperations morphologicalOperations = new MorphologicalOperations((BufferedImage) ((ImageIcon) obrazekLabel.getIcon()).getImage());
        AnalyzeBinaryObject analyzeBinaryObject = new AnalyzeBinaryObject((BufferedImage) ((ImageIcon) obrazekLabel.getIcon()).getImage());

        switch(typDzialania) {
            case "Morfologia matematyczna": {
                switch (typOperacji) {
                    case "erozja": {
                        wynikowaLabel.setIcon(new ImageIcon(morphologicalOperations.erode()));
                        break;
                    }
                    case "dylacja": {
                        wynikowaLabel.setIcon(new ImageIcon(morphologicalOperations.dilation()));
                        break;
                    }
                    case "otwarcie": {
                        wynikowaLabel.setIcon(new ImageIcon(morphologicalOperations.open()));
                        break;
                    }
                    case "zamknięcie": {
                        wynikowaLabel.setIcon(new ImageIcon(morphologicalOperations.close()));
                        break;
                    }
                }
                break;
            }
            case "Wyznaczenie cech": {
                switch (typCechy) {
                    case "momenty": {
                        wynikowaLabel.setIcon(new ImageIcon(analyzeBinaryObject.getMoments()));
                        break;
                    }
                }
            }
        }
    }
}
