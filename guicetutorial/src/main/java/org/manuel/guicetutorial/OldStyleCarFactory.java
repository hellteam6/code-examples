package org.manuel.guicetutorial;

import org.manuel.guicetutorial.battery.Battery;
import org.manuel.guicetutorial.battery.LiIonBattery;
import org.manuel.guicetutorial.car.Car;
import org.manuel.guicetutorial.car.ElectricCar;
import org.manuel.guicetutorial.car.InternalCombustionCar;
import org.manuel.guicetutorial.engine.ACEngine;
import org.manuel.guicetutorial.engine.CombustionEngine;
import org.manuel.guicetutorial.engine.V8Engine;
import org.manuel.guicetutorial.extras.PremiumTurbo;

public class OldStyleCarFactory {

	public Car createElectricCar() {
		ACEngine engine = new ACEngine();
		Battery battery = new LiIonBattery();
		ElectricCar car = new ElectricCar(engine, battery);
		return car;
	}
	
	public Car createPremiumCombustionCar() {
		CombustionEngine engine = new V8Engine(new PremiumTurbo());
		InternalCombustionCar car = new InternalCombustionCar(engine);
		return car;
	}

}
