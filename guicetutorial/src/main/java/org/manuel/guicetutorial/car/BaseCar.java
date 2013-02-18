package org.manuel.guicetutorial.car;

public abstract class BaseCar implements Car {

	private boolean isRunning;
	
	@Override
	public abstract void start();
	
	@Override
	public abstract void stop();

	@Override
	public void setRunning(boolean val) {
		isRunning = val;
	}
	
	@Override
	public boolean isRunning() {
		return isRunning;
	}


}
