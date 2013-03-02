package org.manuel.examples.lwjgl.shaders;

import org.apache.log4j.Logger;

public class Time {

	static Logger logger = Logger.getLogger(Time.class);
	
	private long lastTime;
	private float lastFPS;
	private long elapsed;
	private float elapsedSeconds;
	private float fpsRate;
	
	/**
	 * Creates a Time object
	 * @param fpsRate FPS counter update frequency in seconds
	 */
	public Time(float fpsRate) {
		lastTime = System.nanoTime();
		lastFPS = 0;
		this.fpsRate = fpsRate;
	}
	
	/**
	 * Updates the time data
	 */
	public void update() {
		long now = System.nanoTime();
		elapsed = now - lastTime;
		elapsedSeconds = elapsed * (float)1e-9;
		lastFPS += elapsedSeconds;
		lastTime = now;
	}
	
	public int getFPS() {
		return (int)(1 / elapsedSeconds);
	}
	
	public boolean doFPS() {

		if(lastFPS > fpsRate) {
			lastFPS = 0;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return elapsed time since last update() call in nanoseconds.
	 */
	public long getElapsed() {
		return elapsed;
	}
	
	/**
	 * @return elapsed time since last update() call in seconds.
	 */
	public float getElapsedSeconds() {
		return elapsedSeconds;
	}
	
	public float getFPSRate() {
		return fpsRate;
	}
	
	public void setFPSRate(int fpsRate) {
		this.fpsRate = fpsRate;
	}
		
}
