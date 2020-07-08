package com.revomatico.play.javaship2020;

import java.util.ArrayList;
import java.util.List;

public class PopcornApp {
  public static class Movie {

  }

  private List<Movie> movies = new ArrayList<>();

  public List<Movie> listMovies() {
    return movies;
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
  }
}
