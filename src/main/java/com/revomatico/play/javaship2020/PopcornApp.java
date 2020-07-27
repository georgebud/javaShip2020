package com.revomatico.play.javaship2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopcornApp {
  private List<MediaItem> movies = new ArrayList<>();

  public void setMovies(List<MediaItem> movies) // useful for sorting
  {
    this.movies = movies;
  }

  public List<MediaItem> listMovies() {
    return movies;
  }

  public void addMovie(MediaItem movie) {
    movies.add(movie);
  }

  public List<MediaItem> sort_movie(List<MediaItem> movies) {
    Collections.sort(movies);
    return movies;
  }

  public void print_Movies(List<MediaItem> movies) {
    System.out.println("Sorted movies are: ");
    for (MediaItem movie : movies) {
      System.out.println(movie);
    }
  }

  /*
   * class Sortbyyear implements Comparator<Movie> {
   * public int compare(Movie a, Movie b) { return a.getDate() - b.getDate(); } }
   */
}
