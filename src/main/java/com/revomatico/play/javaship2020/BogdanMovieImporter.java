package com.revomatico.play.javaship2020;

import java.io.*;

public class BogdanMovieImporter {

    public void importAllFrom(String path, PopcornApp app) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null) {
                String[] movieProprieties = line.split(",");
              
                int year=Integer.parseInt(movieProprieties[10]);
                app.addMovie(new PopcornApp.Movie(movieProprieties[5],year));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
