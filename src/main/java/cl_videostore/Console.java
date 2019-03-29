package cl_videostore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Console {

    private InputStream in;
    private PrintStream out;
    private RentalFactory rentalFactory;
    private BufferedReader inputStreamReader;

    public Console(InputStream in, PrintStream out, RentalFactory rentalFactory) {
        this.in = in;
        this.out = out;
        this.rentalFactory = rentalFactory;
        this.inputStreamReader = new BufferedReader(new InputStreamReader(in));
    }

    public String inputCustomerName() throws IOException {
        out.print("Enter customer name: ");
        return this.inputStreamReader.readLine();
    }

    public List<Rental> inputRentals() throws IOException {
        out.print("Choose movie by number followed by rental days, just ENTER for bill:\n");

        List<Rental> rentals = new ArrayList<>();
        while (true) {
            String input = this.inputStreamReader.readLine();
            if (input.isEmpty()) {
                break;
            }
            final Rental rental = rentalFactory.createFrom(input);
            rentals.add(rental);
        }
        return rentals;
    }

    public void printRentalRecord(RentalRecord rentalRecord) {
        String result = "cl_videostore.Rental Record for " + rentalRecord.getCustomerName() + "\n";
        for (Rental rental : rentalRecord.getRentals()) {
            result += "\t" + rental.getMovieName() + "\t" + rental.getAmount() + "\n";
        }

        // add footer lines
        result += "You owed " + rentalRecord.getTotalAmount() + "\n";
        result += "You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n";

        out.print(result);
    }

    public void printMovies(MovieRepository movieRepository) {
        for (Movie movie : movieRepository.getAllMovies()) {
            out.print(movie.getKey() + ": " + movie.getName() + "\n");
        }
    }
}
