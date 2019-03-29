package cl_videostore;

public class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public String getMovieName() {
        return this.movie.getName();
    }

    public int getFrequentRenterPoints(int frequentRenterPoints) {
        // add frequent renter points
        frequentRenterPoints++;
        // add bonus for a two day new release rental
        if (this.movie.getCategory().equals("NEW_RELEASE") && this.daysRented > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public double getAmount() {
        double thisAmount = 0;

        //determine amounts for rental
        switch (this.movie.getCategory()) {
            case "REGULAR":
                thisAmount += 2;
                if (this.daysRented > 2)
                    thisAmount += (this.daysRented - 2) * 1.5;
                break;
            case "NEW_RELEASE":
                thisAmount += this.daysRented * 3;
                break;
            case "CHILDRENS":
                thisAmount += 1.5;
                if (this.daysRented > 3)
                    thisAmount += (this.daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
