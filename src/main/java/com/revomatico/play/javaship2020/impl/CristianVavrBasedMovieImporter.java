package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import io.vavr.collection.Iterator;

public class CristianVavrBasedMovieImporter implements MediaItemImporter {
  private static final String YEAR = "year";
  private static final String TITLE = "title";

  @Override
  public List<MediaItem> importMediaItems(String path) {
    try (FileReader filereader = new FileReader(path);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
          .withSkipLines(1)
          .build()) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy");
      int titleIndex = getIndex(path, TITLE);
      int yearIndex = getIndex(path, YEAR);
      return Iterator.ofAll(csvReader.readAll())
        .map(row -> rowToMovie(titleIndex, yearIndex, format, row))
        //.filter((Movie movie) -> movie.getReleaseDate().after(new Date(10)))
        //.take(4)
        //.groupBy(movie -> movie.getTitle().charAt(0))
        .toJavaList();
    } catch (Exception e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
  }

  private MediaItem rowToMovie(int titleIndex, int yearIndex, SimpleDateFormat format, String[] row) {
    Date productionDate = null;
    try {
      productionDate = format.parse(row[yearIndex]);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    return new MediaItem(row[titleIndex], productionDate);
  }

  public int getIndex(String path, String field) {
    int index = 0;

    try (FileReader reader = new FileReader(path);
        CSVReader csvReader = new CSVReader(reader)) {

      String[] nextData;

      while ((nextData = csvReader.readNext()) != null) {
        for (String cell : nextData) {
          if (cell.equalsIgnoreCase(field)) {
            index = Arrays.asList(nextData).indexOf(cell);
            break;
          }
        }
        break;
      }

    } catch (IOException | CsvValidationException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
    return index;
  }
}
