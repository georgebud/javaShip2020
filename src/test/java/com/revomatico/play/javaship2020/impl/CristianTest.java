package com.revomatico.play.javaship2020.impl;

import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CristianTest {

  @Test
  public void popcornAppShouldExist() {
    PopcornAppTest.popcornAppShouldExist();
  }

  @Test
  public void listMovies() {
    PopcornAppTest.listMovies();
  }

  @Test
  public void listMoviesSortedCristianO() {
    PopcornApp app = new PopcornApp();
    CristianMovieImporter importer = new CristianMovieImporter();
    PopcornAppTest.testMyApplication(app, importer);
  }

  @Test
  @Tag("slow")
  @Tag("performance")
  @Disabled
  //TODO assertTakesSomeTime 5s
  public void listMoviesSortedCristianOPerformanceTest() {
    PopcornApp app = new PopcornApp();
    CristianMovieImporter importer = new CristianMovieImporter();
    for (int i = 0; i < 10_000; i++) {
      PopcornAppTest.testMyApplication(app, importer);
    }
  }
}
