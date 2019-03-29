package cl_videostore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final InputStream in;
    private final PrintStream out;
    private final MovieRepository movieRepository;
    private final RentalFactory rentalFactory;

    public Main(InputStream in, PrintStream out) throws IOException {
        this.in = in;
        this.out = out;
        movieRepository = new MovieRepository();
        rentalFactory = new RentalFactory(movieRepository);
    }

    public static void main(String[] args) throws IOException {
        new Main(System.in, System.out).run();
    }

    void run() throws IOException {

        for (Movie movie : movieRepository.getAllMovies()) {
            out.print(movie.getKey() + ": " + movie.getName() + "\n");
        }

        final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(in));
        out.print("Enter customer name: ");
        String customerName = inputStreamReader.readLine();

        out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");

        String result = "cl_videostore.Rental Record for " + customerName + "\n";
        List<Rental> rentalList = inputRentals(inputStreamReader);
        int frequentRenterPoints = getFrequentRenterPoints(rentalList);
        double totalAmount = getTotalAmount(rentalList);

        for (Rental rental : rentalList) {
            // show figures for this rental
            result += "\t" + rental.getMovieName() + "\t" + rental.getAmount() + "\n";
        }

        // add footer lines
        result += "You owed " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        out.print(result);
    }

    private double getTotalAmount(List<Rental> rentalList) {
        double totalAmount = 0;
        for (Rental rental : rentalList) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    private int getFrequentRenterPoints(List<Rental> rentalList) {
        int frequentRenterPoints = 0;
        for (Rental rental : rentalList) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    private List<Rental> inputRentals(BufferedReader inputStreamReader) throws IOException {
        List<Rental> rentalList = new ArrayList<>();
        while (true) {
            String input = inputStreamReader.readLine();
            if (input.isEmpty()) {
                break;
            }
            final Rental rental = rentalFactory.createFrom(input);
            rentalList.add(rental);
        }
        return rentalList;
    }

}
