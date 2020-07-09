package com.revomatico.play.javaship2020;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class PopcornApp {
  public static class Movie {
    private String name;
    private int prod_date;

    Movie() {}

    Movie(String name, int prod_date) {
      this.name = name;
      this.prod_date=prod_date;
    }

    @Override
    public String toString() {
      return this.name;
    }

    public int getDate() //useful for sorting
    {
      return this.prod_date;
    }
  }

  private List<Movie> movies = new ArrayList<>();

  public List<Movie> listMovies() {
    return movies;
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
  }

  public void print_sortMovies(List<Movie> movies)
  {
      Movie aux;
      boolean swapped;
      for(int i=0; i< movies.size()-1; i++)
      {
        swapped=false;
        for(int j=0;j<movies.size()-i-1;j++)
        {
          if(movies.get(i).getDate()>movies.get(j+1).getDate())
          {
            Collections.swap(movies, j, j+1); 
            swapped=true;
          }
        }
          if(swapped=false)
            break;
      }
      System.out.println("Sorted movies are: ");
      for(int i=0;i<movies.size();i++)
      {
        System.out.println(movies.get(i));
      }
  }
}
