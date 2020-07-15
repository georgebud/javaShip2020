package com.revomatico.play.javaship2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AntoniaMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> moviesAntonia = new ArrayList<>();
    String line = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try (BufferedReader b = new BufferedReader(new FileReader(path))) {
      line = b.readLine();//get over columns' names
      while ((line = b.readLine()) != null) {

        String[] s = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");//instead of line.split(",");
        Movie movie = new Movie(s[5], dateFormat.parse(s[13])); //s[5]=title s[13]=releaseDate
        moviesAntonia.add(movie);
      }
    } catch (IOException | ParseException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }
    return moviesAntonia;
  }

}
