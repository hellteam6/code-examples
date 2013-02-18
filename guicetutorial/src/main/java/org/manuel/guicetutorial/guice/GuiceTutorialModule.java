package org.manuel.guicetutorial.guice;

import org.manuel.guicetutorial.battery.Battery;
import org.manuel.guicetutorial.battery.LiIonBattery;
import org.manuel.guicetutorial.car.Car;
import org.manuel.guicetutorial.car.ElectricCar;
import org.manuel.guicetutorial.engine.CombustionEngine;
import org.manuel.guicetutorial.engine.V8Engine;
import org.manuel.guicetutorial.extras.PremiumTurbo;
import org.manuel.guicetutorial.extras.Turbo;

import com.google.inject.AbstractModule;

public class GuiceTutorialModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Car.class).to(ElectricCar.class);
		bind(Battery.class).to(LiIonBattery.class);	
		bind(CombustionEngine.class).to(V8Engine.class);
		bind(Turbo.class).to(PremiumTurbo.class);
	}

}
