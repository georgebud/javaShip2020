package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class MihaitaMovieImporter implements MediaItemImporter {

  @Override
  public List<MediaItem> importMediaItems(String path) {

    List<MediaItem> moviesMihaita = new ArrayList<>();
    String csvSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    try (Scanner moviesFile = new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8))) {

      moviesFile.nextLine();
      while (moviesFile.hasNextLine()) {

        String fileContents = moviesFile.nextLine();
        String[] moviesProperties = fileContents.split(csvSplitBy);
        MediaItem movieToBeAdded = new MediaItem(moviesProperties[5], new Date(Long.parseLong(moviesProperties[10])));
        moviesMihaita.add(movieToBeAdded);
      }

    } catch (IOException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }
    return moviesMihaita;
  }
}
