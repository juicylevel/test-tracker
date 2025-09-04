package com.mycompany.test.tracker.service;

import com.mycompany.test.tracker.model.DbState;
import com.mycompany.test.tracker.model.Trip;
import com.mycompany.test.tracker.model.TripOptions;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class TripReferenceService implements Autowire.Bean {
  private final CsvDatabaseReader csvDatabaseReader;

  private final ConcurrentHashMap<String, Trip> tripConcurrentHashMap;
  private final AtomicReference<DbState> state;


  public TripReferenceService() {
    csvDatabaseReader = Autowire.autowire(CsvDatabaseReader.class);
    tripConcurrentHashMap = new ConcurrentHashMap<>();
    state = new AtomicReference<>(DbState.INIT);
  }

  private List<String> tripList(Function<Trip, String> extractor) {
    if (state.get() == DbState.INIT) {
      try {
        Configuration.CvsDatabasePath tripPath = retrieveTripPath();
        if (tripPath.state() == DbState.NEW) {
          csvDatabaseReader.saveAll(tripPath.path(), Trip.class, Configuration.retrieveDefaultTripConfiguration());
        }
        csvDatabaseReader.mapAllLines(tripPath.path(), Trip.class)
            .forEach(trip -> tripConcurrentHashMap.put(extractor.apply(trip), trip));
        state.set(DbState.ACTUAL);
      } catch (IOException | CsvException e) {
        throw new IllegalStateException("Can't read the trip database file", e);
      }
    }
    return new ArrayList<>(tripConcurrentHashMap.keySet());
  }

  public Collection<Trip> loadAllTrips() {
    // stupid side effect.
    // refactor #tripList method
    tripList(trip ->
        trip.getName() + "_" + trip.getCity() + "_" + trip.getWeather() + "_" + trip.getPricerange()
    );

    return tripConcurrentHashMap.values();
  }

  public Collection<String> loadAllUniqueTripNames() {
    return loadAllTrips()
        .stream()
        .map(Trip::getName)
        .distinct()
        .toList();
  }

  public Collection<Trip> searchByFilter(TripOptions options) {
    return loadAllTrips()
        .stream()
        .filter(trip -> {
          var satisfied = true;

          var expectedContinent = options.getContinent();
          var weather = options.getTemperature();
          var priceRange = options.getPriceRange();

          if (expectedContinent != null)
            satisfied &= expectedContinent.contains(trip.getName());

          if (weather != null)
            satisfied &= weather.equals(trip.getWeather());

          if (priceRange != null)
            satisfied &= trip.isInPriceRange(priceRange);

          return satisfied;
        })
        .toList();
  }

  @NotNull
  private static Configuration.CvsDatabasePath retrieveTripPath() throws IOException {
    return Configuration.getOrInitDefaultPath(Configuration.getTripsPath());
  }
}
