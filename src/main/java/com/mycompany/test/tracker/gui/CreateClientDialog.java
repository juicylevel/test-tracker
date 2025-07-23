/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.gui;

import com.mycompany.test.tracker.service.Autowire;
import com.mycompany.test.tracker.service.ClientService;

import javax.swing.*;
import java.awt.*;

public class CreateClientDialog extends JDialog {
  private final ClientForm clientForm = new ClientForm();
  private final ClientService clientService;

  public CreateClientDialog(JFrame owner) {
    this(owner, null);
  }

  public CreateClientDialog(JFrame owner, OnClientCreated onClientCreated) {
    super(owner, "Create new client", true);
    clientService = Autowire.autowire(ClientService.class);

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.BOTH;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(clientForm, gbc);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    JButton cancel = new JButton("Cancel");
    JButton submit = new JButton("Save");
    buttonPanel.add(cancel);
    buttonPanel.add(submit);

    cancel.addActionListener(ev -> dispose());

    submit.addActionListener(ev -> {
      ClientFormValues formValues = clientForm.getFormValues();
      clientService.saveClient(formValues);
      JOptionPane.showMessageDialog(this, "Клиент успешно сохранен");
      if (onClientCreated != null) {
        onClientCreated.accept(formValues.getName());
      }
      dispose();
    });

    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    add(buttonPanel, gbc);

    pack();
    setLocationRelativeTo(owner);
  }

  @FunctionalInterface
  public interface OnClientCreated {
    void accept(String clientName);
  }
}
