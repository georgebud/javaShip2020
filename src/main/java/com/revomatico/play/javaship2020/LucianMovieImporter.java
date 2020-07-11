import com.sun.jdi.FloatValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LucianMovieImporter {
    class Movie implements Comparable<Movie> {
       private String title;
       private float rating;
       private Date release_date;

       public Movie(String title, float rating, Date release_date) {
           this.title = title;
           this.rating = rating;
           this.release_date = release_date;
       }

        @Override
        public int compareTo(Movie m) {
           if(release_date.after(m.release_date))
               return 1;
           else if(release_date.before(m.release_date))
               return -1;
           else {
               return title.compareTo(m.title);
           }
        }

        @Override
        public String toString() {
            return "Title: " + title + " Rating: " + rating + " Release date: " + release_date + "\n";
        }
    }

    public void listMovies(String filePath) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(new File(filePath));
        scanner.nextLine(); //Skipping the first line, which contains the column names
        List<Movie> movieList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy"); //Date format used in date parsing

        while (scanner.hasNext()) {
            List<String> line = CSVUtils.parseLine(scanner.nextLine());
            String title = line.get(5); //Getting the movie title
            String ratingString = line.get(8); //Getting the movie's score
            float rating = Float.parseFloat(ratingString);
            String dateString = line.get(13); //Getting the date of the movie
            Date date = format.parse(dateString); //Formating the date from string to date
            Movie current_movie = new Movie(title, rating, date); //Creating the movie
            movieList.add(current_movie); //Adding the movie to the list
        }

        Collections.sort(movieList); //Sorting the movies
        System.out.println(movieList);
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String path = "C:\\Users\\ilies\\OneDrive\\Desktop\\WATCHLIST.csv";
        LucianMovieImporter app = new LucianMovieImporter();
        app.listMovies(path);
    }

}
