package com.demo.tdd.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.demo.tdd.domain.Car;
import com.demo.tdd.exception.CarNotFoundException;
import com.demo.tdd.repository.CarRepository;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

	@Mock
	private CarRepository carRepository;

	private CarService carService;

	@Before
	public void setUp() throws Exception {
		carService = new CarService(carRepository);
	}

	@Test
	public void getCarDetails_returnCarInfo() {
		//given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));
		when(carRepository.findByName(anyString())).thenReturn(new Car("Tata", "hybrid"));

		Car car = carService.getCarDetails("xyz");

		assertThat(car.getName()).isEqualTo("prius");
		assertThat(car.getType()).isEqualTo("hybrid");
	}

	@Test(expected = CarNotFoundException.class)
	public void getCarDetails_whenCarNotFound() throws Exception {
		given(carRepository.findByName("prius")).willReturn(null);

		carService.getCarDetails("prius");
	}

}
