package cl_videostore;

public class RentalFactory {
    private MovieRepository movieRepository;

    public RentalFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Rental createRental(String input) {
        final String[] rentalData = input.split(" ");
        int movieKey = Integer.parseInt(rentalData[0]);
        final Movie movie = this.movieRepository.getByKey(movieKey);
        return new Rental(movie, Integer.parseInt(rentalData[1]));
    }
}
