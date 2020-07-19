package com.revomatico.play.javaship2020.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;

public class BiancaMovieImporter implements MovieImporter {
    @Override
    public List<Movie> importMovies(String path) {
        ArrayList<Movie> movies = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            File csvReader = new File(path);
            Scanner reader = new Scanner(csvReader);
            String row = reader.nextLine();
            while (reader.hasNextLine()) {
                row = reader.nextLine();
                String[] data = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                movies.add(new Movie(data[5], dateFormat.parse(data[data.length - 2])));
            }
            reader.close();

        } catch (IOException | ParseException e) {
            System.out.println("Occured an exception!!!");
            e.printStackTrace();
        }
        return movies;
    }
}
