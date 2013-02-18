package org.manuel.guicetutorial;

import org.manuel.guicetutorial.car.Car;
import org.manuel.guicetutorial.car.InternalCombustionCar;
import org.manuel.guicetutorial.guice.GuiceTutorialModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	//http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice
	final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) { (new Main()).run(); }
	
	public void run() {
		
		logger.info("Car building app initialized");
	
		// Old style factory methods
		OldStyleCarFactory factory = new OldStyleCarFactory();
		Car electric = factory.createElectricCar();
		Car combustion = factory.createPremiumCombustionCar();
	
		electric.start();
		electric.stop();
		combustion.start();
		combustion.stop();
		
		logger.info("Going with Guice");
		
		// Guice style
		Injector injector = Guice.createInjector(new GuiceTutorialModule());
		electric = injector.getInstance(Car.class);
		
		// This shouldn't be done , guice is designed for dependency resolution
		// at program startup and there is no way to instantiate a combustion car
		// without importing the concrete InternalCombustionCar class since the 
		// Car class is already binded to the ElectricCar class in GuiceTutorialModule. 
		combustion = injector.getInstance(InternalCombustionCar.class);
		
		electric.start();
		electric.stop();
		combustion.start();
		combustion.stop();
		
	}
	
}
