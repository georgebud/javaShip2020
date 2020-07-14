package com.revomatico.play.javaship2020;

import java.util.List;

public interface MovieImporter {
  List<Movie> importMovies(String path);
}
