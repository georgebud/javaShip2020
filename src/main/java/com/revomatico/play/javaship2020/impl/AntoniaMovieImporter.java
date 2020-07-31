package com.revomatico.play.javaship2020.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AntoniaMovieImporter implements MediaItemImporter {
  public AntoniaMovieImporter() {
    //log.info("AntoniaMovieImporter created");
  }

  @Override
  public List<MediaItem> importMediaItems(String path) {

    List<MediaItem> moviesAntonia = new ArrayList<>();
    String line = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try (BufferedReader b = new BufferedReader(new FileReader(path))) {

      line = b.readLine();//get columns' names
      String[] columnNames = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

      while ((line = b.readLine()) != null) {

        String[] s = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");//instead of line.split(",");
        HashMap<String, String> hm = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
          hm.put(columnNames[i], s[i]);
        }

        MediaItem movie = new MediaItem(path, hm.get("Title"), dateFormat.parse(hm.get("Release Date")),
          hm.get("Image"), line,
          //TODO to add url from csv
          ""); //s[5]=title s[13]=releaseDate, s[17] image
        moviesAntonia.add(movie);
      }
    } catch (IOException | ParseException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }

    return moviesAntonia;
  }

}
