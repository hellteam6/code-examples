package org.manuel.guicetutorial.test;

import org.junit.BeforeClass;
import org.manuel.guicetutorial.battery.Battery;
import org.manuel.guicetutorial.engine.CombustionEngine;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class BaseGuiceTest {

	protected static Injector injector;

	@BeforeClass
	public static void setUp() {
		injector = Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				bind(Battery.class).to(MockBattery.class);
				bind(CombustionEngine.class).to(MockCombustionEngine.class);
			}
			
		});
	}
}
