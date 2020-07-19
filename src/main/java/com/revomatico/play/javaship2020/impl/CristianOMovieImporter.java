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

public class CristianOMovieImporter implements MovieImporter {
  private static final String YEAR = "year";
  private static final String TITLE = "title";

  public static void main(String[] args) {
    String path = "src\\main\\resources\\WATCHLIST.csv";

    CristianOMovieImporter importer = new CristianOMovieImporter();

    PopcornApp app = new PopcornApp();
    List<Movie> sortedMovieList = app.sort_movie(importer.importMovies(path));
    app.setMovies(sortedMovieList);
    app.print_Movies(app.listMovies());
  }

  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> movieList = new ArrayList<>();

    try {
      FileReader filereader = new FileReader(path);

      // create csvReader object and skip first Line
      CSVReader csvReader = new CSVReaderBuilder(filereader)
        .withSkipLines(1)
        .build();
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
      e.printStackTrace();
    }
    return movieList;
  }

  public int getIndex(String path, String field) {
    int result = 0;

    try (FileReader reader = new FileReader(path)) {

      CSVReader csvReader = new CSVReader(reader);
      String[] nextData;

      while ((nextData = csvReader.readNext()) != null) {
        for (String cell : nextData) {
          if (cell.equalsIgnoreCase(field)) {
            result = Arrays.asList(nextData).indexOf(cell);
            break;
          }

        }
        break;
      }

    } catch (IOException | CsvValidationException e) {
      e.printStackTrace();
    }
    return result;
  }
}
