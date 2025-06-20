package com.mycompany.test.tracker.service;

import com.mycompany.test.tracker.gui.ClientFormValues;
import com.mycompany.test.tracker.model.Client;
import com.mycompany.test.tracker.model.DbState;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class ClientService implements Autowire.Bean {
  private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
  public static final String[] EMPTY_STRINGS = new String[] {};

  private final CsvDatabaseReader csvDatabaseReader;

  private final ConcurrentHashMap<String, Client> clientConcurrentHashMap;
  private final AtomicReference<DbState> state;

  public ClientService() {
    csvDatabaseReader = Autowire.autowire(CsvDatabaseReader.class);
    state = new AtomicReference<>(DbState.NEW);
    clientConcurrentHashMap = new ConcurrentHashMap<>();
  }

  public List<String> clientList(Function<Client, String> extractor) {
    if (state.get() == DbState.NEW) {
      try {
        Path clientPath = retrieveClientPath();
        csvDatabaseReader.mapAllLines(clientPath, Client.class)
            .forEach(client -> clientConcurrentHashMap.put(extractor.apply(client), client));
        state.set(DbState.ACTUAL);
      } catch (IOException | CsvException e) {
        throw new IllegalStateException("Can't read the client database file", e);
      }
    }
    return new ArrayList<>(clientConcurrentHashMap.keySet());
  }

  public void saveClient(ClientFormValues clientFormValues) {
    // what if we have this client already?
    clientConcurrentHashMap.put(clientFormValues.getName(), clientFormValues.toClient());
    state.set(DbState.UPDATED);
  }

  public void persist() {
    if (state.get() == DbState.UPDATED) {
      try {
        csvDatabaseReader.saveAll(retrieveClientPath(), Client.class, clientConcurrentHashMap.values().stream());
        state.set(DbState.ACTUAL);
      } catch (IOException e) {
        logger.error("Can't save data", e);
      }
    }
  }

  public List<String> clientOptionList(String currentClient) {
    if (currentClient == null) {
      return List.of();
    }
    Client client = clientConcurrentHashMap.get(currentClient);
    if (client == null) {
      return List.of();
    } else {
      return Optional.ofNullable(client.getNotes()).map(s -> s.split(",")).map(List::of).orElse(List.of());
    }
  }

  @NotNull
  private static Path retrieveClientPath() throws IOException {
    Path clientPath = Configuration.getClientPath();
    if (Files.isRegularFile(clientPath)) {
      return clientPath;
    } else if (Files.isDirectory(clientPath)) {
      throw new IllegalArgumentException("client.csv should be a file in a user directory %s, but this is directory".formatted(System.getProperty("user.home")));
    } else {
      Files.createFile(clientPath);
    }
    return clientPath;
  }
}
