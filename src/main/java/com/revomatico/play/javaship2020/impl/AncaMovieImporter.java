package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;

public class AncaMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {
    ArrayList<Movie> movies = new ArrayList<>();
    try (Scanner file = new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8))){
      file.nextLine(); // name of the columns

      while (file.hasNextLine()) {
        String line = file.nextLine();
        String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        movies.add(new Movie(split[5],
                new Date(Long.parseLong(split[10]))));
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("When trying to import movies from [" + path + "]", e);
    }

    return movies;
  }
}
