package cl_videostore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

public class MovieRepository {
    private final ArrayList<Movie> movies;

    public MovieRepository(String filePath) throws IOException {
        final InputStream movieStream = Main.class.getResourceAsStream(filePath);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(movieStream));
        movies = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] movieData = line.split(";");
            Movie movie = new Movie(Integer.parseInt(movieData[0]), movieData[1], movieData[2]);
            movies.add(movie);
        }
    }

    public Collection<Movie> getAll() {
        return movies;
    }

    public Movie getByKey(int key) {
        return movies.get(key);
    }
}
