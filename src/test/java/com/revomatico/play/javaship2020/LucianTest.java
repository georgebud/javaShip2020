package com.revomatico.play.javaship2020;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.Test;

public class LucianTest {
  @Test
  public void popcornAppShouldExist() throws FileNotFoundException, ParseException {
    String path = "src/main/resources/WATCHLIST.csv";
    LucianMovieImporter app = new LucianMovieImporter();
    app.listMovies(path);
  }
}
