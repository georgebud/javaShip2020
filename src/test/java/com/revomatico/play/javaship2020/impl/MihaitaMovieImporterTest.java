package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

class MihaitaMovieImporterTest {

  @Test
  public void popcornAppShouldExist() {
    PopcornAppTest.popcornAppShouldExist();
  }

  @Test
  public void listMovies() {
    PopcornAppTest.listMovies();
  }

  @Test
  public void listMoviesSortedMihaita() {
    PopcornAppTest.testMyApplication(new PopcornApp(), new MihaitaMovieImporter());
  }

  @Test
  public void testAllMihaita() {
    MihaitaMovieImporter movieImporter = new MihaitaMovieImporter();
    assertThatThrownBy(() -> movieImporter.importMediaItems("movies-file-none.csv"))
      .isInstanceOf(RuntimeException.class);
  }
}
