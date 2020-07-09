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
    //new BogdanMovieImporter().importAllFrom("./src/main/resources/WATCHLIST.csv",app);
    BogdanMovieImporter vlad=new BogdanMovieImporter();
    vlad.importAllFrom("./src/main/resources/WATCHLIST.csv",app);
    //app.listMovies();
    //List<Movie> movies = new ArrayList<>();
   //app.sortMovies(app.movies);
    
    //new IonelaMovieImporter().importAllFrom("file.csv",app);
    //app.addMovie(new Movie());
     app.print_sortMovies(app.listMovies());
    //movies.get(0).productionYear <= movies.get(1).productionYear
   // movies.stream().forEach(System.out::println);
  }

  
}
