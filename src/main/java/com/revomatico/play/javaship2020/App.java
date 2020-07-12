package com.revomatico.play.javaship2020;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        PopcornApp app = new PopcornApp();

        VladMovieImporter vlad = new VladMovieImporter();
        vlad.importmovies(app);
        app.setMovies(app.sort_movie(app.listMovies()));
        app.print_Movies(app.listMovies());

    }
}
