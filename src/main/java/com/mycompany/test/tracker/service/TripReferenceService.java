package com.mycompany.test.tracker.service;

import com.mycompany.test.tracker.model.DbState;
import com.mycompany.test.tracker.model.Trip;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    state = new AtomicReference<>(DbState.NEW);
  }

  public List<String> tripList(Function<Trip, String> extractor) {
    if (state.get() == DbState.NEW) {
      try {
        Path clientPath = retrieveTripPath();
        csvDatabaseReader.mapAllLines(clientPath, Trip.class)
            .forEach(trip -> tripConcurrentHashMap.put(extractor.apply(trip), trip));
        state.set(DbState.ACTUAL);
      } catch (IOException | CsvException e) {
        throw new IllegalStateException("Can't read the trip database file", e);
      }
    }
    return new ArrayList<>(tripConcurrentHashMap.keySet());
  }

  @NotNull
  private static Path retrieveTripPath() throws IOException {
    Path tripPath = Configuration.getTripsPath();
    if (Files.isRegularFile(tripPath)) {
      return tripPath;
    } else if (Files.isDirectory(tripPath)) {
      throw new IllegalArgumentException("trip.csv should be a file in a user directory %s, but this is directory".formatted(System.getProperty("user.home")));
    } else {
      Files.createFile(tripPath);
    }
    return tripPath;
  }
}
