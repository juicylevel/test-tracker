package com.mycompany.test.tracker.model;

import com.opencsv.bean.CsvBindByPosition;

public class Client implements CsvBean {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String tripTitle;
    @CsvBindByPosition(position = 2)
    private String notes;
    @CsvBindByPosition(position = 3)
    private String priceRange;
    @CsvBindByPosition(position = 4)
    private String wishes;

    public Client() {
    }

    public Client(
        String name,
        String tripTitle,
        String notes,
        String priceRange,
        String wishes
    ) {
        this.name = name;
        this.tripTitle = tripTitle;
        this.notes = notes;
        this.priceRange = priceRange;
        this.wishes = wishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return "Client[" +
            "name=" + name + ", " +
            "tripTitle=" + tripTitle + ", " +
            "notes=" + notes + ", " +
            "priceRange=" + priceRange + ", " +
            "wishes=" + wishes + ']';
    }
}
