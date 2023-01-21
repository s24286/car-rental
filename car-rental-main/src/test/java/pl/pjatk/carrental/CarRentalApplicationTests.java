package pl.pjatk.carrental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.carrental.model.RentalInfo;
import pl.pjatk.carrental.model.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@SpringBootTest
class CarRentalApplicationTests {
	@Autowired
	private CarService carService;

	@Test
	void contextLoads() {

	}
}
