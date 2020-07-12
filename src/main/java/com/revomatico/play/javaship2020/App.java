package com.revomatico.play.javaship2020;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        PopcornApp app = new PopcornApp();
        String path = "./src/main/resources/WATCHLIST.csv";

        //VladMovieImporter vlad = new VladMovieImporter();
        //vlad.importmovies(app);

        AncaMovieImporter anca = new AncaMovieImporter();
        anca.movieImporter(app, path);

        app.setMovies(app.sort_movie(app.listMovies()));
        app.print_Movies(app.listMovies());
    }
}
