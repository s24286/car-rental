package pl.pjatk.carrental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pjatk.carrental.model.RentalInfo;
import pl.pjatk.carrental.model.User;
import pl.pjatk.carrental.storage.CarStorage;
import pl.pjatk.carrental.storage.RentalStorage;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setup() {
        CarStorage carStorage = new CarStorage();
        RentalStorage rentalStorage = new RentalStorage();
        carService = new CarService(carStorage, rentalStorage);
    }

    @Test
    void successCarRent() {
        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "4321",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).hasToString("Rental info: price: 300.0 start date: 2022-11-23 end date: 2022-11-24");
    }

    @Test
    void carDosentExistinDataBase() {
        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "randomVin",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).isNull();
    }

    @Test
    void carIsCurrentlyRented() {
        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "1234",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).isNull();
    }

    @Test
    void premiumClassCarIsPricier() {
        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "9876",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).hasToString("Rental info: price: 450.0 start date: 2022-11-23 end date: 2022-11-24");
    }

    @Test
    void givenEndDateIsBeforeStartDate() {
        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "9876",
                LocalDate.of(2022, 11, 24),
                LocalDate.of(2022, 11, 23)
        );
        //then
        assertThat(testRental).isNull();
    }
}