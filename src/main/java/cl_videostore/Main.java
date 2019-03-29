package cl_videostore;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private final InputStream in;
    private final PrintStream out;

    public Main(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) throws IOException {
        new Main(System.in, System.out).run();
    }

    void run() throws IOException {
        // read movies from file
        final InputStream movieStream = Main.class.getResourceAsStream("/movies.cvs");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(movieStream));
        final Map<Integer, Movie> movies = new HashMap<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] movieData = line.split(";");
            final Movie movie = new Movie(Integer.parseInt(movieData[0]), movieData[1], movieData[2]);
            movies.put(movie.getKey(), movie);
            out.print(movie.getKey() + ": " + movie.getName() + "\n");
        }

        final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(in));
        out.print("Enter customer name: ");
        String customerName = inputStreamReader.readLine();

        out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customerName + "\n";
        while (true) {
            String input = inputStreamReader.readLine();
            if (input.isEmpty()) {
                break;
            }
            final String[] rental = input.split(" ");
            final Movie movie = movies.get(Integer.parseInt(rental[0]));
            double thisAmount = 0;

            int daysRented = Integer.parseInt(rental[1]);
            //determine amounts for rental
            switch (movie.getCategory()) {
                case "REGULAR":
                    thisAmount += 2;
                    if (daysRented > 2)
                        thisAmount += (daysRented - 2) * 1.5;
                    break;
                case "NEW_RELEASE":
                    thisAmount += daysRented * 3;
                    break;
                case "CHILDRENS":
                    thisAmount += 1.5;
                    if (daysRented > 3)
                        thisAmount += (daysRented - 3) * 1.5;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (movie.getCategory().equals("NEW_RELEASE") && daysRented > 1) {
                frequentRenterPoints++;
            }
            // show figures for this rental
            result += "\t" + movie.getName() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

        out.print(result);
    }
}
