package cl_videostore;

import java.io.*;
import java.util.List;

public class Main {

    private final MovieRepository movieRepository;
    private final RentalFactory rentalFactory;
    private Console console;

    public static void main(String[] args) throws IOException {
        new Main(System.in, System.out).run();
    }

    public Main(InputStream in, PrintStream out) throws IOException {
        movieRepository = new MovieRepository();
        rentalFactory = new RentalFactory(movieRepository);
        console = new Console(in, out, rentalFactory);
    }

    void run() throws IOException {
        console.printMovies(movieRepository);

        String customerName = console.inputCustomerName();

        List<Rental> rentals = console.inputRentals();
        RentalRecord rentalRecord = new RentalRecord(rentals, customerName);

        console.printRentalRecord(rentalRecord);
    }

}
