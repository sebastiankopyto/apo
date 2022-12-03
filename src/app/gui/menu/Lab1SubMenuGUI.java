package app.gui.menu;

import app.ImageEditEngine;
import app.core.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.util.Arrays;

public class Lab1SubMenuGUI extends JMenu {

    JLabel mainScreenLabel;
    JFrame mainGui;
    JPanel toolsPanel;

    public Lab1SubMenuGUI(JFrame gui, JLabel mLabel, JPanel toolsPanel) {
        super("Lab1");
        this.mainScreenLabel = mLabel;
        this.mainGui = gui;
        this.toolsPanel = toolsPanel;

        JMenuItem histogramMonoButton = new JMenuItem("Histogram monochromatyczny");
        histogramMonoButton.addActionListener(e -> {
            Histogram histogram = new Histogram(ImageEditEngine.originalImageBuffer);

            HistogramDataset histogramDataset = new HistogramDataset();

            double[] data = Arrays.stream(histogram.mono).asDoubleStream().toArray();

            histogramDataset.addSeries("Barwa", data, 255);

            JFreeChart histogramChart = ChartFactory.createHistogram("Histogram monochromatyczny", "Barwa", null, histogramDataset, PlotOrientation.VERTICAL, true, false, false);
            ChartPanel chartPanel = new ChartPanel(histogramChart);

            this.toolsPanel.removeAll();
            this.toolsPanel.add(chartPanel);
            this.mainGui.repaint();
            this.mainGui.pack();
        });

        this.add(histogramMonoButton);

        JMenuItem histogramRGBButton = new JMenuItem("Histogram RGB");
        histogramRGBButton.addActionListener(e -> {
            Histogram histogram = new Histogram(ImageEditEngine.originalImageBuffer);

            HistogramDataset histogramDataset = new HistogramDataset();

            double[] dataR = Arrays.stream(histogram.rgb[0]).asDoubleStream().toArray();
            double[] dataG = Arrays.stream(histogram.rgb[1]).asDoubleStream().toArray();
            double[] dataB = Arrays.stream(histogram.rgb[2]).asDoubleStream().toArray();

            histogramDataset.addSeries("Red", dataR, 255);
            histogramDataset.addSeries("Green", dataG, 255);
            histogramDataset.addSeries("Blue", dataB, 255);

            JFreeChart histogramChart = ChartFactory.createHistogram("Histogram RGB", "Barwa", null, histogramDataset, PlotOrientation.VERTICAL, true, false, false);
            ChartPanel chartPanel = new ChartPanel(histogramChart);

            this.toolsPanel.removeAll();
            this.toolsPanel.add(chartPanel);
            this.mainGui.repaint();
            this.mainGui.pack();
        });

        this.add(histogramRGBButton);
    }
}
