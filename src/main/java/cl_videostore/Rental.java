package cl_videostore;

public class Rental {
    protected final Movie movie;
    protected final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getAmount() {
        return 0;
    }

    public int getFrequentRenterPoints() {
        // add bonus for a two day new release rental
        if (movie.getCategory().equals("NEW_RELEASE") && daysRented > 1) {
            return 2;
        }
        return 1;
    }

    public String getMovieName() {
        return movie.getName();
    }
}
