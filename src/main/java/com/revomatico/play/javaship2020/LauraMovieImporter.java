package com.revomatico.play.javaship2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LauraMovieImporter {

    public List<PopcornApp.Movie> importMovies(String path) {

        List<PopcornApp.Movie> movies = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));

            csvReader.readLine();       //read the first line containing the name of columns
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] columns = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //split the data
                String movieTitle = columns[5].replaceAll("^[\"']+|[\"']+$", ""); //remove possible "(movie title)"
                int productionYear = Integer.parseInt(columns[10]);

                movies.add(new PopcornApp.Movie(movieTitle, productionYear));
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
