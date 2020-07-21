package com.revomatico.play.javaship2020;

import com.revomatico.play.javaship2020.impl.AntoniaMovieImporter;
import com.revomatico.play.javaship2020.impl.LauraMovieImporter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MovieController {

  @GetMapping("/movies")
  public List<Movie> movies() {
    List<Movie> list = new AntoniaMovieImporter().importMovies("src/main/resources/WATCHLIST.csv");
    return list;
  }
}
