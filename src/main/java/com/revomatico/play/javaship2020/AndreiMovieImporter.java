package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class AndreiMovieImporter implements MovieImporter{

  @Override
  public List<Movie> importMovies(String path) {
      PopcornApp AndreiApp = new PopcornApp();
      DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
      try {
          Scanner file = new Scanner(new File(path));
          file.nextLine();
          while (file.hasNextLine()) {
              String line = file.nextLine();
              String[] bufferSplit = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
              AndreiApp.addMovie(new Movie(bufferSplit[5], date.parse(bufferSplit[13])));
          }
          file.close();
      } catch (FileNotFoundException e) {
          System.out.println("Can not find the csv file");
          e.printStackTrace();
      }
        catch (ParseException e) {
          System.out.println("Can not create Date object");
          e.printStackTrace();
      }
      return AndreiApp.listMovies();
    }
}
