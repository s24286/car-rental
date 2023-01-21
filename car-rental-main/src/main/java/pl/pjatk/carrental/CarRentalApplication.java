package pl.pjatk.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.pjatk.carrental.model.RentalInfo;
import pl.pjatk.carrental.model.User;

import java.time.LocalDate;

@SpringBootApplication
public class CarRentalApplication {

	public CarRentalApplication(CarService carService) {

//		RentalInfo testRental = carService.rentCar(
//				new User("1"), "9876",
//				LocalDate.of(2022, 11, 23),
//				LocalDate.of(2022, 11, 22)
//		);
//		System.out.println(testRental);
	}

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}

}
