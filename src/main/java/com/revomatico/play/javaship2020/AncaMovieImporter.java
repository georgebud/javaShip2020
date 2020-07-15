package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AncaMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {
    ArrayList<Movie> movies = new ArrayList<>();
    try {
      Scanner file = new Scanner(new File(path));
      file.nextLine(); // name of the columns

      while (file.hasNextLine()) {
        String line = file.nextLine();
        String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        movies.add(new Movie(split[5],
          new Date(Long.parseLong(split[10]))));
      }
      file.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return movies;
  }
}
