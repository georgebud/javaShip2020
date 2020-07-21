package com.revomatico.play.javaship2020.impl;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import sun.security.pkcs.ParsingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MihaitaMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> moviesMihaita = new ArrayList<Movie>();
    String line = "";
    String cvsSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      while ((line = br.readLine()) != null) {

        String[] fileContents = line.split(cvsSplitBy);
        Movie movieToBeAdded = new Movie(fileContents[5], dateFormat.parse(fileContents[13]));
      }

    } catch (IOException | ParseException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }
    return moviesMihaita;
  }
}
