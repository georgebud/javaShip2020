package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

public class AndreeaTest {

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

  private void testMyApplication(PopcornApp app, MovieImporter movieImporter) {
    app.setMovies(movieImporter.importMovies("./src/main/resources/WATCHLIST.csv"));
    app.setMovies(app.sort_movie(app.listMovies()));

    List<Movie> movies = app.listMovies();
    for (int i = 0; i < movies.size() - 1; i++) {
      assertThat(movies.get(i).compareTo(movies.get(i + 1)) <= 0).isTrue(); //check if the list is sorted
    }
    app.print_Movies(app.listMovies()); //print the movies
  }

  @Test
  public void listMoviesSortedAndreea() {
    testMyApplication(new PopcornApp(), new AndreeaMovieImporter());
  }

  @Test
  public void notAFileException() {
    assertThatThrownBy(() -> new AndreeaMovieImporter().importMovies("not a file " +
        "or other type of file")).isInstanceOf(RuntimeException.class);
  }

}
