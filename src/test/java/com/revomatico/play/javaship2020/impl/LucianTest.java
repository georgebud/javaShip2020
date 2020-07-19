package com.revomatico.play.javaship2020.impl;

import java.io.FileNotFoundException;
import java.text.ParseException;

import com.revomatico.play.javaship2020.impl.LucianMovieImporter;
import org.junit.Test;

public class LucianTest {
  @Test
  public void popcornAppShouldExist() throws FileNotFoundException, ParseException {
    String path = "src/main/resources/WATCHLIST.csv";
    LucianMovieImporter app = new LucianMovieImporter();
    app.listMovies(path);
  }
}
