package com.revomatico.play.javaship2020;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import io.vavr.collection.Map;
import org.raisercostin.nodes.Nodes;

public class MediaItem implements Comparable<MediaItem> {
  private final String title;
  private final Date releaseDate;
  private String image;
  private final String description;
  //needed for Deezer
  @JsonProperty("link")
  public final String url;

  private MediaItem() {
    this(null, null, null, null, null);
  }

  @Deprecated
  //Try to use the other better constructor that doesn't allow invalid data
  public MediaItem(String title, Date releaseDate) {
    this(title, releaseDate, "https://cdn.vuetifyjs.com/images/lists/5.jpg", "no movie description", "no url");
  }

  public MediaItem(String title, Date releaseDate, String image, String description, String url) {
    this.title = title;
    this.releaseDate = releaseDate;
    //Preconditions.checkNotNull(releaseDate);
    this.image = image;
    this.description = description;
    this.url = url;
  }

  @Override
  public int compareTo(MediaItem movie) {
    if (this.releaseDate.equals(movie.releaseDate)) {
      return this.title.compareTo(movie.title);
    }
    return this.releaseDate.compareTo(movie.releaseDate);
  }

  @Override
  public String toString() {
    return "title = '" + title + '\'' +
        ", releaseDate = " + (releaseDate == null ? null : (releaseDate.getYear() + 1900));
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

  public MediaItem validate() {
    Preconditions.checkNotNull(title);
    Preconditions.checkNotNull(image);
    //Preconditions.checkNotNull(description);
    return this;
  }

  //needed to read image in deezer
  //instead of @JsonProperty("album.cover_medium")
  @JsonProperty("album")
  private void unpackNameFromNestedObject(Map<String, String> album) {
    image = album.get("cover_medium").getOrElse("deezer no image");
  }

  // needed to read image in youtube
  @JsonProperty("thumbnails")
  private void unpackThumbnailFromNestedObject(Map<String, Map<String, String>> thumbnails) {
    try {
      image = thumbnails.get("default").getOrNull().get("url").getOrNull();
    } catch (NullPointerException e) {
      image = "youtube no image";
    }
  }
}
