package com.mycompany.test.tracker.service;

import com.mycompany.test.tracker.gui.ClientFormValues;
import com.mycompany.test.tracker.model.Client;
import com.mycompany.test.tracker.model.DbState;
import com.opencsv.exceptions.CsvException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class ClientService implements Autowire.Bean {
  private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
  public static final String[] EMPTY_STRINGS = new String[] {};
  private static final ClientFormValues EMPTY_CLIENT = new ClientFormValues("", "", "", List.of(), "");

  private final CsvDatabaseReader csvDatabaseReader;

  private final ConcurrentHashMap<String, Client> clientConcurrentHashMap;
  private final AtomicReference<DbState> state;

  public ClientService() {
    csvDatabaseReader = Autowire.autowire(CsvDatabaseReader.class);
    state = new AtomicReference<>(DbState.INIT);
    clientConcurrentHashMap = new ConcurrentHashMap<>();
  }

  public List<String> clientList(Function<Client, String> extractor) {
    if (state.get() == DbState.INIT) {
      try {
        Configuration.CvsDatabasePath clientPath = retrieveClientPath();
        csvDatabaseReader.mapAllLines(clientPath.path(), Client.class)
            .forEach(client -> clientConcurrentHashMap.put(extractor.apply(client), client));
        state.set(clientPath.state());
      } catch (IOException | CsvException e) {
        throw new IllegalStateException("Can't read the client database file", e);
      }
    }
    return new ArrayList<>(clientConcurrentHashMap.keySet());
  }

  public List<Client> getAllClients() {
    if (state.get() == DbState.INIT) {
      clientList(Client::getName);
    }
    return new ArrayList<>(clientConcurrentHashMap.values());
  }

  public void saveClient(ClientFormValues clientFormValues) {
    // what if we have this client already?
    clientConcurrentHashMap.put(clientFormValues.getName(), clientFormValues.toClient());
    state.set(DbState.UPDATED);
  }

  public void persist() {
    if (state.get() == DbState.UPDATED) {
      try {
        csvDatabaseReader.saveAll(retrieveClientPath().path(), Client.class, clientConcurrentHashMap.values().stream());
        state.set(DbState.ACTUAL);
      } catch (IOException e) {
        logger.error("Can't save data", e);
      }
    }
  }

  public ClientFormValues getClient(String clientName) {
    if (clientName == null) { return EMPTY_CLIENT; }
    Client client = clientConcurrentHashMap.get(clientName);
    return Optional.ofNullable(client).map(this::toClient).orElse(EMPTY_CLIENT);
  }

  private ClientFormValues toClient(Client client) {
    return new ClientFormValues(client.getName(), client.getTripTitle(), client.getNotes(),
        Optional.ofNullable(client.getWishes()).map(wishes -> List.of(wishes.split(","))).orElse(List.of()), client.getPriceRange());
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
  private static Configuration.CvsDatabasePath retrieveClientPath() throws IOException {
    return Configuration.getOrInitDefaultPath(Configuration.getClientPath());
  }
}
