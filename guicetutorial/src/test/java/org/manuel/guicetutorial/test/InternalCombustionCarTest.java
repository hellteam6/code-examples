package org.manuel.guicetutorial.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.manuel.guicetutorial.car.Car;
import org.manuel.guicetutorial.car.InternalCombustionCar;


public class InternalCombustionCarTest extends BaseGuiceTest {

	@Test
	public void testCar() {
		
		Car testCar = injector.getInstance(InternalCombustionCar.class);

		testCar.start();
		assertTrue(testCar.isRunning());
		testCar.stop();
		assertFalse(testCar.isRunning());
		
	}
}
