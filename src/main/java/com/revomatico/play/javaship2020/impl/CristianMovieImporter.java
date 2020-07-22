package com.revomatico.play.javaship2020.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import com.revomatico.play.javaship2020.PopcornApp;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CristianMovieImporter implements MovieImporter {
  private static final String YEAR = "year";
  private static final String TITLE = "title";


  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> movieList = new ArrayList<>();

    try (FileReader filereader = new FileReader(path);
    	      CSVReader csvReader = new CSVReaderBuilder(filereader)
    	    	        .withSkipLines(1)
    	    	        .build()){
      
      SimpleDateFormat format = new SimpleDateFormat("yyyy");
      List<String[]> allData = csvReader.readAll();

      String title = null;
      Date productionDate = null;

      for (String[] row : allData) {
        for (String cell : row) {
          if (getIndex(path, TITLE) == Arrays.asList(row).indexOf(cell)) {
            title = cell;
          }
          if (getIndex(path, YEAR) == Arrays.asList(row).indexOf(cell)) {
            productionDate = format.parse(cell);
          }
        }
        movieList.add(new Movie(title, productionDate));
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return movieList;
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
      throw new RuntimeException(e);
    }
    return index;
  }
}
