package com.revomatico.play.javaship2020;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CristianOMovieImporter implements MovieImporter {


    public static void main(String[] args) {
        String path = "src\\main\\resources\\WATCHLIST.csv";

        CristianOMovieImporter importer = new CristianOMovieImporter();

        PopcornApp app = new PopcornApp();
        List<Movie> sortedMovieList = app.sort_movie(importer.importMovies(path));
        app.setMovies(sortedMovieList);
        app.print_Movies(app.listMovies());
    }

    @Override
    public List<Movie> importMovies(String path) {


        List<Movie> movieList = new ArrayList<>();

        try {
            FileReader filereader = new FileReader(path);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                String title = String.valueOf(Array.get(row, 5));
                Date productionDate = format.parse(String.valueOf(Array.get(row, 10)));
                movieList.add(new Movie(title, productionDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }
}
