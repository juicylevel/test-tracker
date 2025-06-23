package com.mycompany.test.tracker.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PieChart extends JPanel {

    public PieChart() {
        this.setPreferredSize(new java.awt.Dimension(300, 300));
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Категория A", 45);
        dataset.setValue("Категория B", 30);
        dataset.setValue("Категория C", 25);

        JFreeChart chart = ChartFactory.createPieChart(
                "Пример круговой диаграммы",
                dataset,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        setBorder(new LineBorder(Color.GRAY, 2)); 
    }
}
