package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revomatico.play.javaship2020.MediaItem;
import com.revomatico.play.javaship2020.MediaItemImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class GeorgeMovieImporter implements MediaItemImporter {
  @Override
  public List<MediaItem> importMediaItems(String path) {
    List<MediaItem> movies = new ArrayList<>();

    try (
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
          .withFirstRecordAsHeader()
          .withIgnoreHeaderCase()
          .withTrim())) {

      for (CSVRecord csvRecord : csvParser) {
        String title = csvRecord.get("Title");
        Date releaseDate = new SimpleDateFormat("yyyy-MM-dd")
          .parse(csvRecord.get("Release Date"));

        movies.add(new MediaItem(title, releaseDate));
      }
    } catch (IOException | ParseException e) {
      throw new RuntimeException("When reading file from [" + new File(path).getAbsolutePath() + "]", e);
    }

    return movies;
  }
}
