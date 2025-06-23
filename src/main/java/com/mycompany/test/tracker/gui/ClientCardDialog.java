/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.gui;

import com.mycompany.test.tracker.service.Autowire;
import com.mycompany.test.tracker.service.ClientService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ClientCardDialog extends JDialog {
    private final ClientService clientService;
    public ClientCardDialog (JFrame owner, String clientName) {
        super(owner, "Client card", true);
        clientService = Autowire.autowire(ClientService.class);

        ClientCard clientCard = new ClientCard();
        // TODO: set client data

        ClientFormValues client = clientService.getClient(clientName);
        clientCard.setClient(client);
        //
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(clientCard, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton closeButton = new JButton("Close");
        buttonPanel.add(closeButton);
        
        closeButton.addActionListener(ev -> {
            dispose();
        });
        
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
        
        pack();
        setLocationRelativeTo(owner);
    }
}
