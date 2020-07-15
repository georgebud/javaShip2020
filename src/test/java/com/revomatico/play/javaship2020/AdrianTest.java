package com.revomatico.play.javaship2020;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

public class AdrianTest {
  @Test
  public void popcornAppShouldExist() throws FileNotFoundException, ParseException {
    String path = "src/main/resources/WATCHLIST.csv";
    AdrianMovieImporter a = new AdrianMovieImporter();
    List<Movie> movies = a.readMovies(path);
    System.out.println(movies);
  }
}
