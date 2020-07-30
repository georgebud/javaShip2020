package com.revomatico.play.javaship2020;

import com.google.common.base.Preconditions;
import com.revomatico.play.javaship2020.impl.AntoniaMovieImporter;
import com.revomatico.play.javaship2020.impl.DeezerPlaylistImporter;
import com.revomatico.play.javaship2020.impl.YouTubeImporterAntonia;
import com.revomatico.play.javaship2020.impl.YoutubePlaylistImporter;
import io.vavr.collection.Iterator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MultimediaController {
  AntoniaMovieImporter movieImporter;

  public MultimediaController(AntoniaMovieImporter movieImporter) {
    Preconditions.checkNotNull(movieImporter);
    this.movieImporter = movieImporter;
  }

  @GetMapping("/")
  public String myIndex() {
    return "Request to / not defined. Try /movies";
  }

  @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<MovieModel> playlists() {
    List<MediaItem> list = new DeezerPlaylistImporter().importMediaItems("");
    List<MediaItem> list2 = new YoutubePlaylistImporter().importMediaItems("");
    //List<MediaItem> list3 = new YouTubeImporterAntonia().importMediaItems("");

    return Iterator.ofAll(list)
      .toList()
      .appendAll(movieImporter.importMediaItems("src/main/resources/WATCHLIST.csv"))
      .appendAll(list2)
      //.appendAll(list3)
      .map(this::toMovieModel)
      .toJavaList();
  }

  private MovieModel toMovieModel(MediaItem movie) {
    MovieModel result = new MovieModel(movie.url, movie.image(), movie.getTitle(), movie.description());
    return result;
  }
}

class MovieModel {
  public final String itemUrl;
  public final String itemImage;
  public final String itemTitle;
  public final String itemDescription;

  public MovieModel(String itemUrl, String itemImage, String itemTitle, String itemDescription) {
    this.itemUrl = itemUrl;
    this.itemImage = itemImage;
    this.itemTitle = itemTitle;
    this.itemDescription = itemDescription;
  }
}
