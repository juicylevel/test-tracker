/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ui.RectangleEdge;

import java.awt.*;
import java.util.List;

import com.mycompany.test.tracker.model.ClientTrip;

public class ClientTripsChart {
    private final ChartPanel chartPanel;

    public ClientTripsChart(List<ClientTrip> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ClientTrip trip : data) {
            dataset.addValue(trip.getCount(), "", trip.getContinent());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                null,               
                null, // "Континент",        
                null, // "Количество",       
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                false, // legend               
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                Color[] colors = {
                    new Color(79, 129, 189),   // blue
                    new Color(192, 80, 77),    // red
                    new Color(155, 187, 89),   // green
                    new Color(128, 100, 162)   // purple
                };
                return colors[column % colors.length];
            }
        };

        renderer.setBarPainter(new StandardBarPainter()); 
        renderer.setDrawBarOutline(false);               
        plot.setRenderer(renderer);                       

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                org.jfree.chart.axis.CategoryLabelPositions.UP_45
        );

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
}
