package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.revomatico.play.javaship2020.Movie;
import com.revomatico.play.javaship2020.MovieImporter;
import com.revomatico.play.javaship2020.PopcornApp;
import org.junit.jupiter.api.Test;

public class AndreeaTest {

  @Test
  public void popcornAppShouldExist() {
    PopcornAppTest.popcornAppShouldExist();
  }


  @Test
  public void listMovies() {
    PopcornAppTest.listMovies();
  }

  @Test
  public void listMoviesSortedAndreea() {
    PopcornAppTest.testMyApplication(new PopcornApp(), new AndreeaMovieImporter());
  }

  @Test
  public void notAFileException() {
    assertThatThrownBy(() -> new AndreeaMovieImporter().importMovies("not a file " +
        "or other type of file")).isInstanceOf(RuntimeException.class);
  }

}
