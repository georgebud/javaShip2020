package com.revomatico.play.javaship2020;

import java.util.Date;

public class Movie implements Comparable<Movie> {
  private final String title;
  private final Date releaseDate;
  private final String image;
  private final String description;

  @Deprecated
  //Try to use the other better constructor that doesn't allow invalid data
  public Movie(String title, Date releaseDate) {
    this(title, releaseDate, "https://cdn.vuetifyjs.com/images/lists/5.jpg", "no movie description");
  }

  public Movie(String title, Date releaseDate, String image, String description) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.image = image;
    this.description = description;
  }

  @Override
  public int compareTo(Movie movie) {
    if (this.releaseDate.equals(movie.releaseDate)) {
      return this.title.compareTo(movie.title);
    }
    return this.releaseDate.compareTo(movie.releaseDate);
  }

  @Override
  public String toString() {
    return "title = '" + title + '\'' +
        ", releaseDate = " + (releaseDate.getYear() + 1900);
  }

  public String getTitle() {
    return title;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public String image() {
    //return null;//break of FailFast break of PLS
    //return "";//break of FailFast break of PLS
    //good: throw new RuntimeException("Not implemented yet!!!");
    return image;
  }

  public String description() {
    return description;
  }

  //  @Override
  //  public int compareTo(Movie o) {
  //    if (getDate() == null || o.getDate() == null) {
  //      return 0;
  //    }
  //    return getDate().compareTo(o.getDate());
  //  }
}
