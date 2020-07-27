package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import com.revomatico.play.javaship2020.PopcornApp;

public class VladMovieImporter implements MediaItemImporter {

  @Override
  public List<MediaItem> importMediaItems(String path) {
    PopcornApp app = new PopcornApp();

    try (Scanner buff = new Scanner(new File(path))) {
      String entire_line = buff.nextLine();// we have column name on the first row
      entire_line = buff.nextLine();
      SimpleDateFormat format = new SimpleDateFormat("yyyy"); // Date format used in date parsing

      while (buff.hasNextLine()) {

        String[] arrSplit = entire_line.split(",");
        // we have the entire line read in a String, we need to parse it
        String string_year = arrSplit[10];
        try {
          Date year = format.parse(string_year);
          String name = arrSplit[5];
          app.addMovie(new MediaItem(name, year));
        } catch (ParseException e) {
          throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
        }
        entire_line = buff.nextLine();

      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
    return app.listMovies();
  }

  /*
   * public void sortlist(PopcornApp ap) { List<Movie> movies=ap.listMovies();
   * Collection.sort(movies); app.print_Movies(app.listMovies()); }
   */
}
