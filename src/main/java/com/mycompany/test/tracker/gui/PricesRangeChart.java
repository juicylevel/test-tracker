package com.mycompany.test.tracker.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.List;
import java.text.DecimalFormat;

import com.mycompany.test.tracker.model.RangeValue;

public class PricesRangeChart {
    private final ChartPanel chartPanel;

    public PricesRangeChart(List<RangeValue> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (RangeValue item : data) {
            dataset.setValue(item.getRange(), item.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                null, // title
                dataset,
                true, // include legend
                true,
                false
        );
        
        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.BOTTOM);

        PiePlot plot = (PiePlot) chart.getPlot();

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{2}",                      
                new DecimalFormat("0"),     
                new DecimalFormat("0.0%") 
        ));

        plot.setSimpleLabels(true);
        plot.setLabelBackgroundPaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelOutlinePaint(null);
        plot.setLabelFont(plot.getLabelFont().deriveFont(14f));
        plot.setLabelPaint(Color.black); 

        // Создаем панель для диаграммы и добавляем в окно
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(270, 280));
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
}
