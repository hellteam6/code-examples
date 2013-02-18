package org.manuel.guicetutorial.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.manuel.guicetutorial.car.Car;
import org.manuel.guicetutorial.car.ElectricCar;

public class ElectricCarTest extends BaseGuiceTest {
	
	@Test
	public void testCar() {
		
		Car testCar = injector.getInstance(ElectricCar.class);

		testCar.start();
		assertTrue(testCar.isRunning());
		testCar.stop();
		assertFalse(testCar.isRunning());
		
	}
	
}
