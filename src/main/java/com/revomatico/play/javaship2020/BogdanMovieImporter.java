package com.revomatico.play.javaship2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BogdanMovieImporter implements MovieImporter {

    @Override
    public List<Movie> importMovies(String path) {
        List<Movie> movies = new ArrayList<Movie>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null) {
                String[] movieProprieties = line.split(",");

                try{
                    Date date=new SimpleDateFormat("mm/dd/yyyy").parse(movieProprieties[movieProprieties.length-2]);
                    movies.add(new Movie(movieProprieties[5], date));
                } catch(ParseException e) {
                    e.printStackTrace();
                }

                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
