/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.model;

public class RangeValue {
    private String range;
    private double value;

    public RangeValue(String range, double value) {
        this.range = range;
        this.value = value;
    }

    public String getRange() {
        return range;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{range: \"" + range + "\", value: " + value + "}";
    }
}
