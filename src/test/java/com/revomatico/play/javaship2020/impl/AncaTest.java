package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

public class AncaTest {
  @Test
  public void popcornAppShouldExist() {
    PopcornApp app = new PopcornApp();
    List<Movie> movies = app.listMovies();
    assertThat(movies).isNotNull();
    assertThat(movies.size()).isEqualTo(0);
  }

  @Test
  public void listMovies() {
    PopcornApp app = new PopcornApp();
    app.addMovie(new Movie());
    List<Movie> movies = app.listMovies();
    assertThat(movies.size()).isEqualTo(1);
  }

  @Test
  public void listMoviesSortedAnca() {
    PopcornAppTest.testMyApplication(new PopcornApp(), new AncaMovieImporter());
  }

  @Test
  public void testFullCoverageAnca() {
    AncaMovieImporter movieImporter = new AncaMovieImporter();
    assertThatThrownBy(() -> movieImporter.importMovies("movies-inexistent-file.csv"
        + "smth")).isInstanceOf(RuntimeException.class);
  }
}
