/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.gui;

import com.mycompany.test.tracker.service.Autowire;
import com.mycompany.test.tracker.service.TripReferenceService;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;

public class SearchTripDialog extends JDialog {
    private final TripReferenceService tripReferenceService;

    public SearchTripDialog(JFrame owner) {
        super(owner, "Generate", true);

        tripReferenceService = Autowire.autowire(TripReferenceService.class);

        var allTrips = tripReferenceService.loadAllTrips();

        TripOptionsForm tripOptionsForm = new TripOptionsForm(buildFormComboboxValues());
        TripSearchResult tripSearchResult = new TripSearchResult();

        // TODO: search trips by options
        //
        tripOptionsForm.addTripOptionsListener(options -> {
            var searchResults = tripReferenceService.searchByFilter(options);

            tripSearchResult.setOptions(searchResults, options);
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(tripOptionsForm, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(tripSearchResult, gbc);

        // Кнопка Close
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        // Панель для выравнивания кнопки справа
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(closeButton);

        // Нижняя панель с кнопкой
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        add(bottomPanel, gbc);

        pack();
        setLocationRelativeTo(owner);
    }

    private TripOptionsForm.FormComboboxValues buildFormComboboxValues() {
        var countries = new LinkedHashSet<String>();
        var priceRanges = new LinkedHashSet<String>();
        var weather = new LinkedHashSet<String>();

        tripReferenceService.loadAllTrips().forEach(tripReference -> {
            countries.add(tripReference.getName());
            priceRanges.add(tripReference.getPricerange());
            weather.add(tripReference.getWeather());
        });

        return new TripOptionsForm.FormComboboxValues(countries, weather, priceRanges);
    }
}
