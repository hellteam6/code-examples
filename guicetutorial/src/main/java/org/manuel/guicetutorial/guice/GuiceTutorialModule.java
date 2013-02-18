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
		//We can bind classes to providers if we need complex creation logic
//		bind(InternalCombustionCar).toProvider(InternalCombustionCarProvider.class);
	}
	
	//Example of a Guice style factory or provider method. For these methods
	//we don't need to specify a binding, guice finds the return type by introspection and does
	//the binding for us.
//	@Provides
//	InternalCombustionCar provideInternalCombustionCar() {
//		CombustionEngine engine = new V8Engine(new PremiumTurbo());
//		InternalCombustionCar car = new InternalCombustionCar(engine);
//		return car;
//	}

	//Example of a Guice style factory or provider
//	class InternalCombustionCarProvider implements Provider<InternalCombustionCar> {
//
//		private final CombustionEngine engine;
//
//		@Inject
//		public InternalCombustionCarProvider(CombustionEngine engine) {
//			this.engine = engine;
//		}
//
//		public InternalCombustionCar get() {
//			InternalCombustionCar car = new InternalCombustionCar(engine);
//			car.setComplicatedStuff1();
//			car.doComplexInitializationStuff();
//			return car;
//		}
//	}

}
