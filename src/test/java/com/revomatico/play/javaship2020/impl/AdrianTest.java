package com.revomatico.play.javaship2020.impl;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class AdrianTest {
  @Test
  public void popcornAppShouldExist() throws FileNotFoundException, ParseException {
    String path = "src/main/resources/WATCHLIST.csv";
    AdrianMovieImporter a = new AdrianMovieImporter();
    List<MediaItem> movies = a.readMovies(path);
    log.info("loading {} movies", movies.size());
  }
}
