package pl.pjatk.carrental.storage;

import org.springframework.stereotype.Component;
import pl.pjatk.carrental.model.Car;
import pl.pjatk.carrental.model.CarType;
import pl.pjatk.carrental.model.Rental;
import pl.pjatk.carrental.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentalStorage {
    private final List<Rental> rentalList = new ArrayList<>();

    public RentalStorage() {
        rentalList.add(new Rental(new User("1"), new Car("Opel", "Astra", "1234", CarType.STANDARD)));
        rentalList.add(new Rental(new User("2"), new Car("Skoda", "Octavia", "133", CarType.STANDARD)));
    }
    public void addNewRental(Rental rental) {
        rentalList.add(rental);
    }

    public boolean isCarRented(String vin) {
        return getRentalList().stream()
                .map(Rental::getCar)
                .anyMatch(car -> car.getVin().equals(vin));
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

}
