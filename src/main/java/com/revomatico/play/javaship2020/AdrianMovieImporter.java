package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdrianMovieImporter implements MovieImporter {

  @Override
  public List<com.revomatico.play.javaship2020.Movie> importMovies(String path) {
    try {
      return readMovies(path);
    } catch (FileNotFoundException | ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Movie> readMovies(String filepath) throws FileNotFoundException, ParseException {
    int titleIndex = 5;
    int dateIndex = 13;

    Scanner s = new Scanner(new File(filepath));
    // skip the first line
    s.nextLine();
    List<Movie> movies = new ArrayList<>();

    while (s.hasNext()) {
      List<String> line = CSVReader.parseLine(s.nextLine());
      String title = line.get(titleIndex);
      String date = line.get(dateIndex);
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date releaseDate = format.parse(date);

      Movie movie = new Movie(title, releaseDate);
      movies.add(movie);
    }
    Collections.sort(movies);

    return movies;
  }
}

class CSVReader {
  private static final char SEPARATOR = ',';
  private static final char QUOTE = '"';

  public static List<String> parseLine(String csvLine) {
    List<String> words = new ArrayList<>();
    boolean br = false;

    if (csvLine == null || csvLine.isEmpty()) {
      return words;
    }

    StringBuilder word = new StringBuilder();
    boolean inQuotes = false;
    boolean startCollectChar = false;
    boolean doubleQuotesInColumn = false;

    char[] charsOfWord = csvLine.toCharArray();

    for (char ch : charsOfWord) {
      if (inQuotes) {
        startCollectChar = true;
        if (ch == QUOTE) {
          inQuotes = false;
          doubleQuotesInColumn = false;
        } else {
          if (ch == '\"') {
            if (!doubleQuotesInColumn) {
              word.append(ch);
              doubleQuotesInColumn = true;
            } else {
              word.append(ch);
            }
          }
        }
      } else {
        switch (ch) {
          case QUOTE:
            inQuotes = true;
            if (charsOfWord[0] != QUOTE) {
              word.append(QUOTE);
            }
            if (startCollectChar) {
              word.append(QUOTE);
            }
            break;
          case SEPARATOR:
            words.add(word.toString());
            word = new StringBuilder();
            startCollectChar = false;
            break;
          case '\r':
            continue;
          case '\n':
            br = true;
            break;
          default:
            word.append(ch);
            break;
        }
      }
      if (br) {
        break;
      }
    }

    words.add(word.toString());
    return words;
  }
}