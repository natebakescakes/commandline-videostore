package cl_videostore;

public class RentalFactory {
    private MovieRepository movieRepository;

    public RentalFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Rental createFrom(String input) {
        final String[] rentalData = input.split(" ");
        int movieKey = Integer.parseInt(rentalData[0]);
        final Movie movie = movieRepository.getByKey(movieKey);
        int daysRented = Integer.parseInt(rentalData[1]);
        switch (movie.getCategory()) {
            case "REGULAR":
                return new RegularRental(movie, daysRented);
            case "NEW_RELEASE":
                return new NewReleaseRental(movie, daysRented);
            case "CHILDRENS":
                return new ChildrensRental(movie, daysRented);
        }

        return new Rental(movie, daysRented);
    }
}
