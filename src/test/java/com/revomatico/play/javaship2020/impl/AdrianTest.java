package com.revomatico.play.javaship2020.impl;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import com.revomatico.play.javaship2020.Movie;
import org.junit.jupiter.api.Test;

public class AdrianTest {
  @Test
  public void popcornAppShouldExist() throws FileNotFoundException, ParseException {
    String path = "src/main/resources/WATCHLIST.csv";
    AdrianMovieImporter a = new AdrianMovieImporter();
    List<Movie> movies = a.readMovies(path);
    System.out.println(movies);
  }
}
