package com.revomatico.play.javaship2020;

import java.util.List;

import com.google.common.base.Preconditions;
import com.revomatico.play.javaship2020.impl.AntoniaMovieImporter;
import com.revomatico.play.javaship2020.impl.DeezerPlaylistImporter;
import io.vavr.collection.Iterator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/movies")
  public List<MovieModel> playlists() {
    List<MediaItem> list = new DeezerPlaylistImporter().importMediaItems("src/main/resources/WATCHLIST.csv");
    return Iterator.ofAll(list)
      .toList()
      .appendAll(movieImporter.importMediaItems("src/main/resources/WATCHLIST.csv"))
      //.filter(m -> m.getTitle().contains("Pulp"))
      .map(movie -> toMovieModel(movie))
      .toJavaList();
  }

  private MovieModel toMovieModel(MediaItem movie) {
    MovieModel result = new MovieModel(movie.image(), movie.getTitle(), movie.description());
    //result.itemTitle = "bla bla";
    return result;
  }
}

class MovieModel {
  public MovieModel(String itemImage, String itemTitle, String itemDescription) {
    this.itemImage = itemImage;
    this.itemTitle = itemTitle;
    this.itemDescription = itemDescription;
  }

  public final String itemImage;
  public final String itemTitle;
  public final String itemDescription;
}