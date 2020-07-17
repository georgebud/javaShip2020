package com.revomatico.play.javaship2020;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class AncaTest {
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

    @Test
    public void listMoviesSortedAnca() {
        testMyApplication(new PopcornApp(), new AncaMovieImporter());
    }

    @Test
    public void testFullCoverageAnca() {
        AncaMovieImporter movieImporter = new AncaMovieImporter();
        assertThatThrownBy(() -> movieImporter.importMovies("movies-inexistent-file.csv"
                + "smth")).isInstanceOf(RuntimeException.class);
    }

    private void testMyApplication(PopcornApp app, MovieImporter movieImporter) {
        app.setMovies(movieImporter.importMovies("./src/main/resources/WATCHLIST.csv"));
        app.setMovies(app.sort_movie(app.listMovies()));

        List<Movie> movies = app.listMovies();
        for (int i = 0; i < movies.size() - 1; i++) {
            //check if the list is sorted
            Assert.assertTrue(movies.get(i).compareTo(movies.get(i + 1)) <= 0);
        }
        app.print_Movies(app.listMovies()); //print the movies
    }
}