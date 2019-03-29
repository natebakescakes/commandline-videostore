package cl_videostore;

import java.util.List;

public class RentalRecord {

    private String customerName;
    private List<Rental> rentals;

    public RentalRecord(String customerName, List<Rental> rentals) {
        this.customerName = customerName;
        this.rentals = rentals;
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : this.rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
            // show figures for this rental
        }
        return frequentRenterPoints;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : this.rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }
}
