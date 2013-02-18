package org.manuel.guicetutorial.car;

import org.manuel.guicetutorial.Main;
import org.manuel.guicetutorial.battery.Battery;
import org.manuel.guicetutorial.engine.ACEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

@SuppressWarnings("unused")
public class ElectricCar extends BaseCar {

	final Logger logger = LoggerFactory.getLogger(ElectricCar.class);
	
	private ACEngine engine;
	private Battery battery;
	private boolean isRunning;
	
	@Inject
	public ElectricCar(ACEngine engine, Battery battery) {
		this.engine = engine;
		this.battery = battery;
	}

	@Override
	public void start() {
		setRunning(true);
		
		logger.info("Electric car started");
	}

	@Override
	public void stop() {
		setRunning(false);
		
		logger.info("Electric car stopped");
	}

	
}
