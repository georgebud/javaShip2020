package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class AncaMovieImporter implements MediaItemImporter {

  @Override
  public List<MediaItem> importMediaItems(String path) {
    ArrayList<MediaItem> movies = new ArrayList<>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try (Scanner file = new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8))) {
      String firstLine = file.nextLine();// name of the columns
      String[] c = firstLine.split(",");

      while (file.hasNextLine()) {
        String line = file.nextLine();
        String[] split = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
          hashMap.put(c[i], split[i]);
        }

        movies.add(new MediaItem(path, hashMap.get("Title"),
          df.parse(hashMap.get("Release Date")),
          hashMap.get("Image"), "",
          hashMap.get("URL")));
      }

    } catch (FileNotFoundException | ParseException e) {
      throw new RuntimeException("When trying to import movies from [" + path + "]", e);
    }

    return movies;
  }
}
