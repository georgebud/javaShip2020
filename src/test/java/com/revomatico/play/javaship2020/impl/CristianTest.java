package com.revomatico.play.javaship2020.impl;

import org.junit.jupiter.api.Test;

import com.revomatico.play.javaship2020.PopcornApp;

public class CristianTest {
	
	@Test
	public void popcornAppShouldExist() {
		PopcornAppTest.popcornAppShouldExist();
	}
	
	@Test
	public void listMovies() {
		PopcornAppTest.listMovies();
	}
	
	 @Test
	  public void listMoviesSortedCristianO() {
	    PopcornAppTest.testMyApplication(new PopcornApp(), new CristianMovieImporter());
	  }

}
