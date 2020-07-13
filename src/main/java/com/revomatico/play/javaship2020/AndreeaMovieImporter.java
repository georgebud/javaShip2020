package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class AndreeaMovieImporter {
    public ArrayList<Movie> importeList() {
        ArrayList<Movie> movieList = new ArrayList<>();
        File csvFile = new File("./src/main/resources/WATCHLIST.csv");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Scanner readerLines = new Scanner(csvFile);
            String line = readerLines.nextLine();
            while(readerLines.hasNextLine()) {
                line = readerLines.nextLine();
                String[] info = line.split(",");
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