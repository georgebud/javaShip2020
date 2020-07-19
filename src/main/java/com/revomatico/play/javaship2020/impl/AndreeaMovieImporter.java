package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;

public class AndreeaMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {
    ArrayList<Movie> movieList = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    try {
      Scanner readerLines = new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8));
      String line = readerLines.nextLine();
      while (readerLines.hasNextLine()) {
        line = readerLines.nextLine();
        String[] info = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        movieList.add(new Movie(info[5],
          format.parse(info[info.length - 2])));
      }
      readerLines.close();

    } catch (FileNotFoundException | ParseException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }
    return movieList;
  }
}
