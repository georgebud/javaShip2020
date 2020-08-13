package com.revomatico.play.javaship2020.impl;

import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AncaTest {
  @Test
  public void popcornAppShouldExist() {
    PopcornAppTest.popcornAppShouldExist();
  }

  @Test
  public void listMovies() {
    PopcornAppTest.listMovies();
  }

  @Test
  public void listMoviesSortedAnca() {
    PopcornAppTest.testMyApplication(new PopcornApp(), new AncaMovieImporter());
  }

  @Test
  public void testFullCoverageAnca() {
    AncaMovieImporter movieImporter = new AncaMovieImporter();
    assertThatThrownBy(() -> movieImporter.importMediaItems("movies-inexistent-file.csv"
        + "smth")).isInstanceOf(RuntimeException.class);
  }
}
