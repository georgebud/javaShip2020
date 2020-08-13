package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;

public class RobertMovieImporter implements MediaItemImporter {

  @Override
  public List<MediaItem> importMediaItems(String path) {

    ArrayList<MediaItem> movieList = new ArrayList<>();
    try {
      File fileRead = new File(path);
      Scanner reader = new Scanner(fileRead);
      String data = reader.nextLine();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      while (reader.hasNextLine()) {
        data = reader.nextLine();
        String[] splitData = data.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        movieList.add(new MediaItem(splitData[5], dateFormat.parse(splitData[13])));
      }
      reader.close();
    } catch (FileNotFoundException | ParseException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }
    return movieList;
  }

}
