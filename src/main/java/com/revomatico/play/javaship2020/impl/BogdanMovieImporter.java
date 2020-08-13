package com.revomatico.play.javaship2020.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class BogdanMovieImporter implements MediaItemImporter {

  @Override
  public List<MediaItem> importMediaItems(String path) {
    List<MediaItem> movies = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      String line = bufferedReader.readLine();
      line = bufferedReader.readLine();
      while (line != null) {
        String[] movieProprieties = line.split(",");

        try {
          Date date = new SimpleDateFormat("mm/dd/yyyy").parse(movieProprieties[movieProprieties.length - 2]);
          movies.add(new MediaItem(path, movieProprieties[5], date));
        } catch (ParseException e) {
          throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
        }

        line = bufferedReader.readLine();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    } catch (IOException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
    return movies;
  }
}
