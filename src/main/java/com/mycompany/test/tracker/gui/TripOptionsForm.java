/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package com.mycompany.test.tracker.gui;

import java.util.ArrayList;
import java.util.List;

public class TripOptionsForm extends javax.swing.JPanel {
    
    private final List<ITripOptionsListener> listeners = new ArrayList<>();

    /** Creates new form TripOptionsForm */
    public TripOptionsForm() {
        initComponents();
        
        priceRangeComboBox.addActionListener(e -> {
            handleChangeOptions();
        });
        
        continentComboBox.addActionListener(e -> {
            handleChangeOptions();
        });
        
        temperatureComboBox.addActionListener(e -> {
            handleChangeOptions();
        });
    }
    
    public void addTripOptionsListener(ITripOptionsListener listener) {
        listeners.add(listener);
    }
    
    public void removeTripOptionsListener(ITripOptionsListener listener) {
        listeners.remove(listener);
    }
    
    public TripOptions getFormValues() {
        Object selectedPriceRange = priceRangeComboBox.getSelectedItem();
        Object selectedContinent = continentComboBox.getSelectedItem();
        Object selectedTemperature = temperatureComboBox.getSelectedItem();
        
        String priceRange = getComboboxValue(selectedPriceRange);
        String continent = getComboboxValue(selectedContinent);
        String temperature = getComboboxValue(selectedTemperature);

        return new TripOptions(priceRange, continent, temperature);
    }
    
    private void handleChangeOptions() {
        TripOptions options = getFormValues();
        fireTripOptionsSubmitted(options);
    }
    
    private String getComboboxValue(Object selected) {
        return selected != null ? selected.toString() : null;
    }
    
    private void fireTripOptionsSubmitted(TripOptions values) {
        for (ITripOptionsListener listener : listeners) {
            listener.onTripOptionsSubmitted(values);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        priceRangeComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        continentComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        temperatureComboBox = new javax.swing.JComboBox<>();

        jLabel1.setLabelFor(priceRangeComboBox);
        jLabel1.setText("Enter optimal price range");

        priceRangeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0-10000", "10000-15000", "15000-20000", "20000-25000" }));
        priceRangeComboBox.setSelectedIndex(-1);
        priceRangeComboBox.setName("priceRange"); // NOI18N

        jLabel2.setText("Enter continent in wich client is interesed in");

        continentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "America", "Europe", "Asia" }));
        continentComboBox.setSelectedIndex(-1);
        continentComboBox.setToolTipText("");
        continentComboBox.setName("continent"); // NOI18N
        continentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continentComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Choose weather country should be ");

        temperatureComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "warm", "cold", "average" }));
        temperatureComboBox.setSelectedIndex(-1);
        temperatureComboBox.setName("temperature"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(temperatureComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(continentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceRangeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceRangeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(temperatureComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void continentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continentComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_continentComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> continentComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> priceRangeComboBox;
    private javax.swing.JComboBox<String> temperatureComboBox;
    // End of variables declaration//GEN-END:variables

}
