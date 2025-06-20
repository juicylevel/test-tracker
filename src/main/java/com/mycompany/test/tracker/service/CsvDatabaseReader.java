package com.mycompany.test.tracker.service;

import com.mycompany.test.tracker.model.CsvBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CsvDatabaseReader implements Autowire.Bean {

  public List<String[]> readAllLines(Path filePath) throws IOException, CsvException {
    try (Reader reader = Files.newBufferedReader(filePath)) {
      try (CSVReader csvReader = new CSVReader(reader)) {
        return csvReader.readAll();
      }
    }
  }

  @NotNull
  public <T extends CsvBean> List<T> mapAllLines(Path path, Class<T> clazz) throws IOException, CsvException {
    try (Reader reader = Files.newBufferedReader(path)) {
      CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
          .withType(clazz)
          .withSeparator(';')
          .build();

      return cb.parse();
    }
  }

  public <T extends CsvBean> void saveAll(Path path, Class<T> clazz, Stream<T> values) throws IOException {
    try (Writer writer  = new FileWriter(path.toString())) {

      StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
          .withQuotechar('\'')
          .withSeparator(';')
          .build();

        sbc.write(values);
    } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
      throw new RuntimeException(e);
    }
  }
}
