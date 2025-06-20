package com.mycompany.test.tracker.service;

import java.nio.file.Path;

public final class Configuration {
  public static final String CLIENT_FILE_NAME = "Clients.csv";
  public static final String TRIPS_FILE_NAME = "TripReferences.csv";

  private Configuration() {
  }

  public static Path getClientPath() {
    return Path.of(System.getProperty("user.home"), CLIENT_FILE_NAME);
  }

  public static Path getTripsPath() {
    return Path.of(System.getProperty("user.home"), TRIPS_FILE_NAME);
  }
}
