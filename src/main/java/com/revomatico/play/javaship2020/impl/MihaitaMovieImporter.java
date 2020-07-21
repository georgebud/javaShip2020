package com.revomatico.play.javaship2020.impl;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MihaitaMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> moviesMihaita = new ArrayList<>();
    String csvSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    try (Scanner moviesFile = new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8))) {

      moviesFile.nextLine();
      while (moviesFile.hasNextLine()) {

        String fileContents = moviesFile.nextLine();
        String[] moviesProperties = fileContents.split(csvSplitBy);
        Movie movieToBeAdded = new Movie(moviesProperties[5], new Date(Long.parseLong(moviesProperties[10])));
        moviesMihaita.add(movieToBeAdded);
      }

    } catch (IOException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }
    return moviesMihaita;
  }
}
