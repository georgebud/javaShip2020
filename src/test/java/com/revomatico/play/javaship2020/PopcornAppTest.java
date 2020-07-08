package com.revomatico.play.javaship2020;

import java.util.List;

import com.revomatico.play.javaship2020.PopcornApp.Movie;
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
  public void listMoviesSorted() {
    PopcornApp app = new PopcornApp();
    //from csv list
    //new IonelMovieImporter().importAllFrom("file.csv",app);
    //new IonelaMovieImporter().importAllFrom("file.csv",app);
    app.addMovie(new Movie());
    List<Movie> movies = app.listMovies();
    //movies.get(0).productionYear <= movies.get(1).productionYear
    System.out.println(movies);
  }
}
