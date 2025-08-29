package com.mycompany.test.tracker.gui;

import com.mycompany.test.tracker.model.RangeValue;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class PricesRangeChart {
    private final ChartPanel chartPanel;

    private final DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

    public PricesRangeChart(List<RangeValue> data) {
        updateData(data);

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

    public void updateData(List<RangeValue> data) {
        dataset.clear();

        for (RangeValue item : data) {
            dataset.setValue(item.getRange(), item.getValue());
        }
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
}
