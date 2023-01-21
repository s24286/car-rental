package pl.pjatk.carrental.model;

import java.time.LocalDate;

public class RentalInfo {
    private final double price;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public RentalInfo(double price, LocalDate startDate, LocalDate endDate) {
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "Rental info: price: " + price + " start date: " + startDate + " end date: " + endDate;
    }
}
