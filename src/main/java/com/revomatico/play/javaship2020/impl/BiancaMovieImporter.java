package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class BiancaMovieImporter implements MediaItemImporter {
  @Override
  public List<MediaItem> importMediaItems(String path) {
    ArrayList<MediaItem> movies = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    try {

      File csvReader = new File(path);
      Scanner reader = new Scanner(csvReader);
      String row = reader.nextLine();
      while (reader.hasNextLine()) {
        row = reader.nextLine();
        String[] data = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        movies.add(new MediaItem(path, data[5], dateFormat.parse(data[10])));
      }
      reader.close();

    } catch (IOException | ParseException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
    return movies;
  }
}
