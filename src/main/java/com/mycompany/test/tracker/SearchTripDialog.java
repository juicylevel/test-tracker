/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author yurirykov
 */
public class SearchTripDialog extends JDialog {
    public SearchTripDialog (JFrame owner) {
        super(owner, "Generate", true);
        
        TripOptionsForm tripOptionsForm = new TripOptionsForm();
        TripSearchResult tripSearchResult = new TripSearchResult();

        tripOptionsForm.addTripOptionsListener(options -> {
            // TODO: search trips by options
            tripSearchResult.setOptions(options);
            //
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
}
