package org.manuel.guicetutorial.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.manuel.guicetutorial.OldStyleCarFactory;
import org.manuel.guicetutorial.battery.Battery;
import org.manuel.guicetutorial.battery.NiCadBattery;
import org.manuel.guicetutorial.car.Car;
import org.manuel.guicetutorial.car.ElectricCar;
import org.manuel.guicetutorial.car.InternalCombustionCar;
import org.manuel.guicetutorial.engine.ACEngine;
import org.manuel.guicetutorial.engine.CombustionEngine;
import org.manuel.guicetutorial.engine.WankelEngine;

public class OldStyleTest {

	// We don't need a real battery to test the car, just plug
	// it into the grid 
	@Test
	public void testElectricOldStyle() {
		
		OldStyleCarFactory factory = new OldStyleCarFactory() {
			@Override
			public Car createElectricCar() {
				ACEngine engine = new ACEngine();
				Battery battery = new MockBattery();
				ElectricCar car = new ElectricCar(engine, battery);
				return car;
			}
		};
		
		Car testCar = factory.createElectricCar();
		testCar.start();
		assertTrue(testCar.isRunning());
		testCar.stop();
		assertFalse(testCar.isRunning());
		
	}
	
	// Test a car with a NiCad battery
	@Test
	public void testCarWithNiCadChemistry() {
		
		OldStyleCarFactory factory = new OldStyleCarFactory() {
			@Override
			public Car createElectricCar() {
				ACEngine engine = new ACEngine();
				Battery battery = new NiCadBattery();
				ElectricCar car = new ElectricCar(engine, battery);
				return car;
			}
		};
		
		Car testCar = factory.createElectricCar();
		testCar.start();
		assertTrue(testCar.isRunning());
		testCar.stop();
		assertFalse(testCar.isRunning());
		
	}
	
	// Test combustion car structural margins, we don't need a real engine for that,
	// just one block of metal with similar mass and rough shape I guess.. 
	@Test
	public void testCombustionStructuralMarginsOldStyle() {
		
		OldStyleCarFactory factory = new OldStyleCarFactory() {
			@Override
			public Car createPremiumCombustionCar() {
				CombustionEngine engine = new MockCombustionEngine();
				InternalCombustionCar car = new InternalCombustionCar(engine);
				return car;
			}
		};
		
		Car testCar = factory.createPremiumCombustionCar();
		testCar.start();
		assertTrue(testCar.isRunning());
		testCar.stop();
		assertFalse(testCar.isRunning());
		
	}
	
	// Test a combustion car with a wankel engine
	@Test
	public void testCarWithWankelEngine() {
		
		OldStyleCarFactory factory = new OldStyleCarFactory() {
			@Override
			public Car createPremiumCombustionCar() {
				CombustionEngine engine = new WankelEngine();
				InternalCombustionCar car = new InternalCombustionCar(engine);
				return car;
			}
		};
		
		Car testCar = factory.createPremiumCombustionCar();
		testCar.start();
		assertTrue(testCar.isRunning());
		testCar.stop();
		assertFalse(testCar.isRunning());
	}

}
