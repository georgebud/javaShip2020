package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class CristianMovieImporter implements MediaItemImporter {
  private static final String YEAR = "year";
  private static final String TITLE = "title";

  @Override
  public List<MediaItem> importMediaItems(String path) {

    List<MediaItem> movieList = new ArrayList<>();

    try (FileReader filereader = new FileReader(path);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
          .withSkipLines(1)
          .build()) {

      SimpleDateFormat format = new SimpleDateFormat("yyyy");
      List<String[]> allData = csvReader.readAll();

      String title = null;
      Date productionDate = null;
      int titleIndex = getIndex(path, TITLE);
      int prodIndex = getIndex(path, YEAR);
      //v1: O(rows * cells * cells)
      //v2: O(rows * cells)
      //int computePi = computePi();//1s
      for (String[] row : allData) {//1M rows
        for (String cell : row) {//1M cells
          if (titleIndex == Arrays.asList(row).indexOf(cell)) {
            title = cell;
          }
          if (prodIndex == Arrays.asList(row).indexOf(cell)) {
            productionDate = format.parse(cell);
          }
        }
        movieList.add(new MediaItem(path, title, productionDate));
      }

    } catch (Exception e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
    return movieList;
  }

  public int getIndex(String path, String field) {
    int index = 0;

    try (FileReader reader = new FileReader(path);
        CSVReader csvReader = new CSVReader(reader)) {

      String[] nextData;

      while ((nextData = csvReader.readNext()) != null) { //1 row
        for (String cell : nextData) { //1Mcell
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
