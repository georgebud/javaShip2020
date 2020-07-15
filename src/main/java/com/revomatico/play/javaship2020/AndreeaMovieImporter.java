package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AndreeaMovieImporter implements MovieImporter {
    public List<Movie> importMovies(String path) {
        ArrayList<Movie> movieList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");

        try {
            File csvFile = new File(path);
            Scanner readerLines = new Scanner(csvFile);
            String line = readerLines.nextLine();
            while(readerLines.hasNextLine()) {
                line = readerLines.nextLine();
                String[] info = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                movieList.add(new Movie(info[5],
                        format.parse(info[info.length - 2])));
            }
            readerLines.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }  catch (ParseException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}