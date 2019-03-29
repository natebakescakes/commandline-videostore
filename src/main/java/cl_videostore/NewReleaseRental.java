package cl_videostore;

public class NewReleaseRental extends Rental {

    public NewReleaseRental(Movie movie, int daysRented) {
        super(movie, daysRented);
    }

    @Override
    public double getAmount() {
        return daysRented * 3;
    }
}
