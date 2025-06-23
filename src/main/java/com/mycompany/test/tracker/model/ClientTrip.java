/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.model;

public class ClientTrip {
    private final int count;
    private final String continent;

    public ClientTrip(String continent, int count) {
        this.continent = continent;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getContinent() {
        return continent;
    }
}
