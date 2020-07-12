package com.revomatico.play.javaship2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LauraMovieImporter {

    public List<PopcornApp.Movie> importMovies(String path) {

        List<PopcornApp.Movie> movies = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));

            csvReader.readLine();       //read the first line containing the name of columns
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] columns = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //split the data
                String movieTitle = columns[5].replaceAll("^[\"']+|[\"']+$", ""); //remove possible "(movie title)"
                Date productionYear = format.parse(columns[10]);
                movies.add(new PopcornApp.Movie(movieTitle, productionYear));
            }
            csvReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
