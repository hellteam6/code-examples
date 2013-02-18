package org.manuel.guicetutorial.car;

import org.manuel.guicetutorial.engine.CombustionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

@SuppressWarnings("unused")
public class InternalCombustionCar extends BaseCar {

	final Logger logger = LoggerFactory.getLogger(InternalCombustionCar.class);
	
	private CombustionEngine engine;
	
	@Inject
	public InternalCombustionCar(CombustionEngine engine) {
		this.engine = engine;
	}

	private void starter() {
		setRunning(true);
		
		logger.info("Combustion car started");
	}
	
	@Override
	public void start() {
		starter();
	}

	@Override
	public void stop() {
		setRunning(false);
		
		logger.info("Combustion car stopped");
	}
	
}
