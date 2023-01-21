package pl.pjatk.carrental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.pjatk.carrental.model.Car;
import pl.pjatk.carrental.model.CarType;
import pl.pjatk.carrental.model.RentalInfo;
import pl.pjatk.carrental.model.User;
import pl.pjatk.carrental.storage.CarStorage;
import pl.pjatk.carrental.storage.RentalStorage;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarServiceIntegrationTest {

    @MockBean
    private CarStorage carStorage;
    @MockBean
    private RentalStorage rentalStorage;


    @Autowired
    private CarService carService;

    @Test
    void shouldPass() {
        when(carStorage.getCarList()).thenReturn(List.of(new Car("Audi" , "A3", "aaaa", CarType.PREMIUM)));

        List<Car> allCars = carService.getAllCars();
        assertThat(allCars).isNotEmpty();
    }

    @Test
    void successCarRent() {
        when(carStorage.getCarList()).thenReturn(List.of(new Car("Audi" , "A3", "aaaa", CarType.STANDARD)));
        when(carStorage.findCarByVin(any())).thenReturn(Optional.of(new Car("Audi" , "A3", "aaaa", CarType.STANDARD)));

        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "aaaa",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).hasToString("Rental info: price: 300.0 start date: 2022-11-23 end date: 2022-11-24");
    }

    @Test
    void carDoesntExistInDatabase() {
        when(carStorage.findCarByVin(any())).thenReturn(Optional.ofNullable(null));

        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "aaaa",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );

        //then
        assertThat(testRental).isNull();
    }

    @Test
    void carIsCurrentlyRented() {
        when(carStorage.findCarByVin(any())).thenReturn(Optional.of(new Car("Audi" , "A3", "aaaa", CarType.STANDARD)));
        when(rentalStorage.isCarRented(any())).thenReturn(true);

        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "aaaa",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).isNull();
    }

    @Test
    void premiumClassCarIsPricier() {
        when(carStorage.findCarByVin(any())).thenReturn(Optional.of(new Car("Audi" , "A3", "aaaa", CarType.PREMIUM)));

        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "aaaa",
                LocalDate.of(2022, 11, 23),
                LocalDate.of(2022, 11, 24)
        );
        //then
        assertThat(testRental).hasToString("Rental info: price: 450.0 start date: 2022-11-23 end date: 2022-11-24");
    }

    @Test
    void givenEndDateIsBeforeStartDate() {
        when(carStorage.findCarByVin(any())).thenReturn(Optional.of(new Car("Audi" , "A3", "aaaa", CarType.STANDARD)));

        //when
        RentalInfo testRental = carService.rentCar(
                new User("1"), "aaaa",
                LocalDate.of(2022, 11, 24),
                LocalDate.of(2022, 11, 23)
        );
        //then
        assertThat(testRental).isNull();
    }
}
