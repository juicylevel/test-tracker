package com.mycompany.test.tracker.model;

import com.opencsv.bean.CsvBindByPosition;

public class Trip implements CsvBean {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String weather;
    @CsvBindByPosition(position = 2)
    private String pricerange;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPricerange() {
        return pricerange;
    }

    public void setPricerange(String pricerange) {
        this.pricerange = pricerange;
    }
}
