package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;

public class AdrianMovieImporter {

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

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String path = "/home/adrian/Documents/javaShip2020/src/main/resources/WATCHLIST.csv";
        AdrianMovieImporter a = new AdrianMovieImporter();
        List<Movie> movies = a.readMovies(path);
        System.out.println(movies);
    }
}

class Movie implements Comparable<Movie> {
    private String title;
    private Date releaseDate;

    public Movie(String title, Date releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    @Override
    public int compareTo(Movie movie) {
        if (this.releaseDate.equals(movie.releaseDate)) {
            return this.title.compareTo(movie.title);
        }
        return this.releaseDate.compareTo(movie.releaseDate);
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", releaseDate=" + releaseDate;
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
//                if (ch == QUOTE) {
//                    inQuotes = true;
//                    if (charsOfWord[0] != QUOTE) {
//                        word.append(QUOTE);
//                    }
//                    if (startCollectChar) {
//                        word.append(QUOTE);
//                    }
//                } else if (ch == SEPARATOR) {
//                    words.add(word.toString());
//                    word = new StringBuilder();
//                    startCollectChar = false;
//                } else if (ch == '\r') {
//                    continue;
//                } else if (ch == '\n') {
//                    break;
//                } else {
//                    word.append(ch);
//                }
            }
            if (br)
                break;
        }

        words.add(word.toString());
        return words;
    }
}