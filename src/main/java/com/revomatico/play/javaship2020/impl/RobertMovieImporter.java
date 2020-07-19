package com.revomatico.play.javaship2020.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RobertMovieImporter implements MovieImporter {

  @Override
  public List<Movie> importMovies(String path) {
    
    ArrayList<Movie> movieList = new ArrayList<Movie>();
    
  try {
    File fileRead = new File(path);
    Scanner reader = new Scanner(fileRead);
    String data = reader.nextLine();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    while (reader.hasNextLine()) {
      data = reader.nextLine();
    String[] splitData = data.split(",");
    
    movieList.add(new Movie(splitData[5], dateFormat.parse(splitData[splitData.length - 2])));
    }
    reader.close();
  } catch (FileNotFoundException | ParseException e) {
        System.out.println("An error occurred.");
    e.printStackTrace();
  }
    
  return movieList;
  }
  
  

}
