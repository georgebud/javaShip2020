package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Date;
import java.util.List;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

public class PopcornAppTest {
  @Test
  public void popcornAppShouldExist() {
    PopcornApp app = new PopcornApp();
    List<Movie> movies = app.listMovies();
    assertThat(movies).isNotNull();
    assertThat(movies.size()).isEqualTo(0);
    assertThat(movies).describedAs("the list should always have star trek").contains(new Movie("star", new Date()));
  }

  @Test
  public void listMovies() {
    PopcornApp app = new PopcornApp();
    app.addMovie(new Movie());
    List<Movie> movies = app.listMovies();
    assertThat(movies.size()).isEqualTo(1);
  }

  @Test
  public void listMoviesSortedBianca() {
    testMyApplication(new PopcornApp(), new BiancaMovieImporter());
  }

  @Test
  public void listMoviesSortedLaura() {
    testMyApplication(new PopcornApp(), new LauraMovieImporter());
  }

  @Test
  public void listMoviesSortedAndrei() {
    testMyApplication(new PopcornApp(), new AndreiMovieImporter());
  }

  @Test
  public void listMoviesSortedAdrian() {
    testMyApplication(new PopcornApp(), new AdrianMovieImporter());
  }

  @Test
  public void listMoviesSortedAndreea() {
    testMyApplication(new PopcornApp(), new AndreeaMovieImporter());
  }

  @Test
  public void listMoviesSortedRobert() {
    PopcornApp app = new PopcornApp();
    RobertMovieImporter robert = new RobertMovieImporter();
    app.setMovies(robert.importMovies("./src/main/resources/WATCHLIST.csv"));
    app.setMovies(app.sort_movie(app.listMovies()));
    app.print_Movies(app.listMovies());

  }

  @Test
  public void listMoviesSortedRobertTest() {
    testMyApplication(new PopcornApp(), new RobertMovieImporter());
  }

  @Test
  public void listMoviesSortedAntonia() {
    testMyApplication(new PopcornApp(), new AntoniaMovieImporter());
  }

  @Test
  public void testForFullCoverageAntonia() {
    PopcornApp app = new PopcornApp();
    AntoniaMovieImporter movieImporter = new AntoniaMovieImporter();
    assertThatThrownBy(() -> app.setMovies(movieImporter.importMovies(null))).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void testFullCoverageAntonia2() {
    AntoniaMovieImporter movieImporter = new AntoniaMovieImporter();
    assertThatThrownBy(() -> movieImporter.importMovies("movies-inexistent-file.csv"
        + "smth")).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void listMoviesSortedGeorge() {
    testMyApplication(new PopcornApp(), new GeorgeMovieImporter());
  }

  @Test

  public void listMoviesSortedVladB() {
    testMyApplication(new PopcornApp(), new VladMovieImporter());
  }

  @Test
  public void listMoviesSortedCristianO() {
    testMyApplication(new PopcornApp(), new CristianOMovieImporter());
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
}
