/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test.tracker.gui;

import com.mycompany.test.tracker.model.Client;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 
 */
public class ClientFormValues {
    private final String name;
    private final String tripTitle;
    private final String requirements;
    private final List<String> options;
    private final String price;

    public ClientFormValues(String name, String tripTitle, String requirements,List<String> options, String price) {
        this.name = name;
        this.tripTitle = tripTitle;
        this.requirements = requirements;
        this.options = options;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public String getRequirements() {
        return requirements;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getPrice() {
        return price;
    }

    public Client toClient() {
        return new Client(this.getName(), this.tripTitle, CollectionUtils.emptyIfNull(this.options).stream().collect(
            Collectors.joining(",")), this.price, this.requirements);
    }

    @Override
    public String toString() {
        return "ClientFormValues{" +
                "clientName='" + name + '\'' +
                ", tripTitle='" + tripTitle + '\'' +
                ", requirements='" + requirements + '\'' +
                ", options=" + options +
                ", price='" + price + '\'' +
                '}';
    }
}
