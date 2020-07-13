package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AncaMovieImporter {

  public List<Movie> movieImporter(PopcornApp app, String path) {
    try {
      Scanner file = new Scanner(new File(path));
      file.nextLine(); // name of the columns

      while (file.hasNextLine()) {
        String line = file.nextLine();
        String[] split = line.split(",");
        app.addMovie(new Movie(split[5],
          new Date(Integer.parseInt(split[10]))));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return app.listMovies();
  }
}
