package com.revomatico.play.javaship2020;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AndreeaTest {

    @Test
    public void popcornAppShouldExist() {
        PopcornApp app = new PopcornApp();
        List<Movie> movies = app.listMovies();
        Assert.assertNotNull(movies);
        Assert.assertEquals(0, movies.size());
    }

    @Test
    public void listMovies() {
        PopcornApp app = new PopcornApp();
        app.addMovie(new Movie());
        List<Movie> movies = app.listMovies();
        Assert.assertEquals(1, movies.size());
    }

    private void testMyApplication(PopcornApp app, MovieImporter movieImporter) {
        app.setMovies(movieImporter.importMovies("./src/main/resources/WATCHLIST.csv"));
        app.setMovies(app.sort_movie(app.listMovies()));

        List<Movie> movies = app.listMovies();
        for (int i = 0; i < movies.size() - 1; i++) {
            Assert.assertTrue(movies.get(i).compareTo(movies.get(i + 1)) <= 0); //check if the list is sorted
        }
        app.print_Movies(app.listMovies()); //print the movies
    }

    @Test
    public void listMoviesSortedAndreea() {
        testMyApplication(new PopcornApp(), new AndreeaMovieImporter());
    }

    @Test
    public void notAFileException() {
        assertThatThrownBy(() -> new AndreeaMovieImporter().importMovies("not a file " +
                "or other type of file")).isInstanceOf(RuntimeException.class);
    }

}
