package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class AndreeaMovieImporter implements MediaItemImporter {
  private SimpleDateFormat format;

  public AndreeaMovieImporter() {
    this(new SimpleDateFormat("yyyy"));
  }

  public AndreeaMovieImporter(SimpleDateFormat format) {
    this.format = format;
  }

  @Override
  public List<MediaItem> importMediaItems(String path) {
    ArrayList<MediaItem> movieList = new ArrayList<>();
    try {
      Scanner readerLines = new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8));
      String line = readerLines.nextLine();
      while (readerLines.hasNextLine()) {
        line = readerLines.nextLine();
        String[] info = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        movieList.add(new MediaItem(path, info[5],
          format.parse(info[10])));
      }
      readerLines.close();

    } catch (FileNotFoundException | ParseException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }
    return movieList;
  }
}
