package com.revomatico.play.javaship2020;
import java.util.List;
import com.revomatico.play.javaship2020.PopcornApp.Movie;
import org.junit.Assert;
import org.junit.Test;

//import main.java.com.revomatico.play.javaship2020.VladMovieImporter;

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

    VladMovieImporter vlad=new VladMovieImporter();
    vlad.importmovies(app);
    
    app.setMovies(app.sort_movie(app.listMovies()));
   // app.setMovies(app.listMovies());
    app.print_Movies(app.listMovies());
   // app.movies=sort_movie(app.listMovies());

  }

  @Test
  public void listMoviesSortedLaura() {
    PopcornApp app = new PopcornApp();

    LauraMovieImporter lauraMovieImporter = new LauraMovieImporter();
    app.setMovies(lauraMovieImporter.importMovies("./src/main/resources/WATCHLIST.csv"));
    app.setMovies(app.sort_movie(app.listMovies()));

    List<Movie> movies = app.listMovies();
    for(int i = 0; i < movies.size() - 1; i++)
      Assert.assertTrue(movies.get(i).compareTo(movies.get(i + 1)) <= 0); //check if the list is sorted
    app.print_Movies(app.listMovies());   //print the movies
  }

  
}
