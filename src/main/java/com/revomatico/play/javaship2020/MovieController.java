package com.revomatico.play.javaship2020;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MovieController {

  @GetMapping("/movies")
  public List<Movie> movies() {
    ArrayList<Movie> list = new ArrayList<>();
    list.add(new Movie("Nume", new Date()));
    list.add(new Movie("Nume2", new Date()));
    return list;
  }
}
