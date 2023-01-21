package pl.pjatk.carrental.storage;

import org.springframework.stereotype.Component;
import pl.pjatk.carrental.model.Car;
import pl.pjatk.carrental.model.CarType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarStorage {
    private final List<Car> carList = new ArrayList<>();

    public CarStorage() {
        carList.add(new Car("Opel", "Astra", "1234", CarType.STANDARD));
        carList.add(new Car("Fiat", "Punto", "4321", CarType.STANDARD));
        carList.add(new Car("Porsche ", "Cayenne", "9876", CarType.PREMIUM));
    }

    public Optional<Car> findCarByVin(String vin) {
        return getCarList().stream()
                .filter(storage -> storage.getVin().equals(vin))
                .findFirst();
    }

    public List<Car> getCarList() {
        return carList;
    }
}
