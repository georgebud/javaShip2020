package com.revomatico.play.javaship2020;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PopcornAppTest {
  @Test
  public void popcornAppShouldExist() {
    PopcornApp app = new PopcornApp();
    List<Movie> movies = app.listMovies();
    Assert.assertNotNull(movies);
    Assert.assertEquals(0, movies.size());
  }

  @Test
  public void listMovies() {
    PopcornApp app = new PopcornApp();
    app.addMovie(new Movie());
    List<Movie> movies = app.listMovies();
    Assert.assertEquals(1, movies.size());
  }

  @Test
  public void listMoviesSortedBianca() { testMyApplication(new PopcornApp(), new BiancaMovieImporter());
  }

  @Test
  public void listMoviesSortedLaura() {
    testMyApplication(new PopcornApp(), new LauraMovieImporter());
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

  @Test(expected = NullPointerException.class)
  public void testForFullCoverageAntonia() {
    PopcornApp app = new PopcornApp();
    AntoniaMovieImporter movieImporter = new AntoniaMovieImporter();
    app.setMovies(movieImporter.importMovies(null));
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
      Assert.assertTrue(movies.get(i).compareTo(movies.get(i + 1)) <= 0); //check if the list is sorted
    }
    app.print_Movies(app.listMovies()); //print the movies
  }
}
