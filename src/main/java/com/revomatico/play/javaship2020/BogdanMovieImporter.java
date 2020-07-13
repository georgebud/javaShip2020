package com.revomatico.play.javaship2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BogdanMovieImporter {

    public void importAllFrom(String path, PopcornApp app) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            while (line != null) {
                String[] movieProprieties = line.split(",");

             //   int year = Integer.parseInt(movieProprieties[10]);
                try{
                    Date date1=new SimpleDateFormat("mm/dd/yyyy").parse(movieProprieties[movieProprieties.length-2]);
                    app.addMovie(new Movie(movieProprieties[5], date1));
                } catch(ParseException e)
                {  //
                    int b;
                    e.printStackTrace();
                }
               
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
