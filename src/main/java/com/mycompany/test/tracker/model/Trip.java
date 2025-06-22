package com.mycompany.test.tracker.model;

import com.opencsv.bean.CsvBindByPosition;

public class Trip implements CsvBean {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String weather;
    @CsvBindByPosition(position = 2)
    private String pricerange;

    public static TripBuilder builder() {
        return new TripBuilder();
    }

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

    public static final class TripBuilder {
        private String name;
        private String weather;
        private String pricerange;

        private TripBuilder() {
        }

        public TripBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TripBuilder weather(String weather) {
            this.weather = weather;
            return this;
        }

        public TripBuilder pricerange(String pricerange) {
            this.pricerange = pricerange;
            return this;
        }

        public Trip build() {
            Trip trip = new Trip();
            trip.setName(name);
            trip.setWeather(weather);
            trip.setPricerange(pricerange);
            return trip;
        }
    }
}
