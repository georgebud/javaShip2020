package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AndreiMovieImporter implements MovieImporter{

  @Override
  public List<Movie> importMovies(String path) {
      ArrayList<Movie> AndreiArray = new ArrayList<>();
      DateFormat date = new SimpleDateFormat("yyyy");
      try(Scanner csvReader= new Scanner(new File(path), String.valueOf(StandardCharsets.UTF_8))) {
          csvReader.nextLine();
          while (csvReader.hasNextLine()) {
              String csvReaderLine = csvReader.nextLine();
              String[] bufferSplit = csvReaderLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
              Date movieDate = date.parse(bufferSplit[10]);
              AndreiArray.add(new Movie(bufferSplit[5], movieDate));
          }
          csvReader.close();
      } catch (IOException | ParseException e) {
          throw new RuntimeException("When trying to importMovies from [" + path + "]", e);
      }
      return AndreiArray;
    }
}
