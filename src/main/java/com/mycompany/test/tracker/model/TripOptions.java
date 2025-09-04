/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.model;

public class TripOptions {
    private final String priceRange;
    private final String continent;
    private final String temperature;

    public TripOptions(String priceRange, String continent, String temperature) {
        this.priceRange = priceRange;
        this.continent = continent;
        this.temperature = temperature;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getContinent() {
        return continent;
    }

    public String getTemperature() {
        return temperature;
    }


    @Override
    public String toString() {
        return "TripOptions{" +
                "priceRange='" + priceRange + '\'' +
                ", continent='" + continent + '\'' +
                ", requirements='" + temperature + '\'' +
                '}';
    }
}
