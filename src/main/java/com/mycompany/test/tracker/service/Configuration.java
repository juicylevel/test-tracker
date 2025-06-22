package com.mycompany.test.tracker.service;

import com.mycompany.test.tracker.model.DbState;
import com.mycompany.test.tracker.model.Trip;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class Configuration {
  public static final String CLIENT_FILE_NAME = "Clients.csv";
  public static final String TRIPS_FILE_NAME = "TripReferences.csv";
  public static final String USER_HOME = "user.home";

  private static final String[][] TPIRS = new String[][] {
{"Америка","Жарко","0-10"},
{"Америка","Жарко","10-100"},
{"Америка","Жарко","100-1000"},
{"Америка","Холодно","0-10"},
{"Америка","Холодно","10-100"},
{"Америка","Холодно","100-1000"},
{"Америка","Душно","0-10"},
{"Америка","Душно","10-100"},
{"Америка","Душно","100-1000"},
{"Европа","Жарко","0-10"},
{"Европа","Жарко","10-100"},
{"Европа","Жарко","100-1000"},
{"Европа","Холодно","0-10"},
{"Европа","Холодно","10-100"},
{"Европа","Холодно","100-1000"},
{"Европа","Душно","0-10"},
{"Европа","Душно","10-100"},
{"Европа","Душно","100-1000"},
{"Азия","Жарко","0-10"},
{"Азия","Жарко","10-100"},
{"Азия","Жарко","100-1000"},
{"Азия","Холодно","0-10"},
{"Азия","Холодно","10-100"},
{"Азия","Холодно","100-1000"},
{"Азия","Душно","0-10"},
{"Азия","Душно","10-100"},
{"Азия","Душно","100-1000"}};

  private Configuration() {
  }

  public static Path getClientPath() {
    return Path.of(System.getProperty(USER_HOME), CLIENT_FILE_NAME);
  }

  public static Path getTripsPath() {
    return Path.of(System.getProperty(USER_HOME), TRIPS_FILE_NAME);
  }


  @NotNull
  public static CvsDatabasePath getOrInitDefaultPath(Path dbPath) throws IOException {
    if (dbPath == null) {
      throw new IllegalArgumentException("Path can't be empty");
    } else if (Files.isRegularFile(dbPath)) {
      return new CvsDatabasePath(DbState.ACTUAL, dbPath);
    } else if (Files.isDirectory(dbPath)) {
      throw new IllegalArgumentException("%s should be a file in a user directory %s, but this is directory".formatted(dbPath.toAbsolutePath(),
          System.getProperty(USER_HOME)));
    } else {
      return new CvsDatabasePath(DbState.NEW, Files.createFile(dbPath));
    }
  }

  public static Stream<Trip> retrieveDefaultTripConfiguration() {
    return Arrays.stream(TPIRS).map(array -> Trip.builder().name(array[0]).weather(array[1]).pricerange(array[2]).build());
  }

  public record CvsDatabasePath(
      DbState state,
      Path path
  ) {}
}
