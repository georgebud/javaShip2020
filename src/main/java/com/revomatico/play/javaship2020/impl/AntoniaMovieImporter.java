package com.revomatico.play.javaship2020.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.io.Files;
import com.opencsv.CSVParser;
import com.opencsv.CSVReaderBuilder;
import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AntoniaMovieImporter implements MovieImporter {
  public AntoniaMovieImporter() {
    //log.info("AntoniaMovieImporter created");
  }

  @Override
  public List<Movie> importMovies(String path) {

    List<Movie> moviesAntonia = new ArrayList<>();
    String line = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try (BufferedReader b = new BufferedReader(new FileReader(path))) {

      line = b.readLine();//get columns' names
      String[] columnNames = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

      while ((line = b.readLine()) != null) {

        String[] s = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");//instead of line.split(",");
        HashMap hm = new HashMap<String, String>();
        for (int i = 0; i < s.length; i++)
          hm.put(columnNames[i], s[i]);

        Movie movie = new Movie((String) hm.get("Title"), dateFormat.parse((String) hm.get("Release Date")),
          (String) hm.get("Image"), line); //s[5]=title s[13]=releaseDate, s[17] image
        moviesAntonia.add(movie);
      }
    } catch (IOException | ParseException e) {
      throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
    }

    return moviesAntonia;
  }

}
