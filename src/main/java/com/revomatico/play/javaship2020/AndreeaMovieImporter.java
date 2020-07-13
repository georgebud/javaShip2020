package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class AndreeaMovieImporter {
    public ArrayList<PopcornApp.Movie> importeList() {
        try {
            ArrayList<PopcornApp.Movie> movieList = new ArrayList<>();
            File csvFile = new File("./src/main/resources/WATCHLIST.csv");
            Scanner readerLines = new Scanner(csvFile);
            String line = readerLines.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

            while(readerLines.hasNextLine()) {
                line = readerLines.nextLine();
                String info[] = line.split(",");
                movieList.add(new PopcornApp.Movie(info[5], format.parse(info[info.length - 2])));
            }
            readerLines.close();
            return movieList;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }  catch (ParseException e) {
                e.printStackTrace();
        }
        return null;
    }
}