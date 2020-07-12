package com.revomatico.play.javaship2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class AncaMovieImporter {

    public List<PopcornApp.Movie> movieImporter(PopcornApp app, String path) {
        try {
            Scanner file = new Scanner(new File(path));
            file.nextLine(); // name of the columns
            SimpleDateFormat date = new SimpleDateFormat("yyyy");

            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] split = line.split(",");
                app.addMovie(new PopcornApp.Movie(split[5], date.parse(split[10])));
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

        return app.listMovies();
    }
}
