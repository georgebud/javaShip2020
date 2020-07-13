package com.revomatico.play.javaship2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopcornApp {
  private List<Movie> movies = new ArrayList<>();

  public void setMovies(List<Movie> movies) // useful for sorting
  {
    this.movies = movies;
  }

  public List<Movie> listMovies() {
    return movies;
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
  }

  public List<Movie> sort_movie(List<Movie> movies) {
    Collections.sort(movies);
    return movies;
  }

  public void print_Movies(List<Movie> movies) {
    System.out.println("Sorted movies are: ");
    for (Movie movie : movies) {
      System.out.println(movie);
    }
  }

  /*
   * class Sortbyyear implements Comparator<Movie> {
   * public int compare(Movie a, Movie b) { return a.getDate() - b.getDate(); } }
   */
}
