package com.revomatico.play.javaship2020.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.text.SimpleDateFormat;

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
    PopcornAppTest.testMyApplication(new PopcornApp(), new AndreeaMovieImporter(new SimpleDateFormat("yyyy")));
  }

  @Test
  public void notAFileException() {
    assertThatThrownBy(
      () -> new AndreeaMovieImporter(new SimpleDateFormat("yyyy-MM-dd")).importMediaItems("not a file " +
          "or other type of file")).isInstanceOf(RuntimeException.class);
  }

}
