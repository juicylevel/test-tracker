package com.mycompany.test.tracker.model;

import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

public class Trip implements CsvBean {
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String weather;
    @CsvBindByPosition(position = 2)
    private String city;
    @CsvBindByPosition(position = 3)
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isInPriceRange(String priceRange) {
        var currentPriceRange = PriceRange.parse(this.pricerange);

        return PriceRange.parse(priceRange).isIncludes(currentPriceRange);
    }

    public static final class TripBuilder {
        private String name;
        private String weather;
        private String pricerange;
        private String city;

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

        public TripBuilder city(String city) {
            this.city = city;
            return this;
        }

        public Trip build() {
            Trip trip = new Trip();
            trip.setName(name);
            trip.setWeather(weather);
            trip.setPricerange(pricerange);
            trip.setCity(city);
            return trip;
        }
    }

    public record PriceRange(BigDecimal from, BigDecimal to) {
        public static PriceRange parse(String str) {
            var parts = str.split("-");

            if (parts.length != 2)
                throw new IllegalArgumentException("Unprocessable price range raw value: " + str);

            var from = new BigDecimal(parts[0]);
            var to = new BigDecimal(parts[1]);

            // case when rance in inverted order, for example instead 10-100, we got 100-10
            if (from.compareTo(to) > 0) {
                var tmp = from;

                from = to;
                to = tmp;
            }

            return new PriceRange(from, to);
        }

        public boolean isIncludes(PriceRange another) {
            return from.compareTo(another.from) <= 0
                && to.compareTo(another.to) >= 0;
        }
    }
}
