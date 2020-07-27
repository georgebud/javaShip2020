package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

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
