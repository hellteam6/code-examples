package org.manuel.guicetutorial.engine;

import org.manuel.guicetutorial.extras.Turbo;

import com.google.inject.Inject;

public class V8Engine implements CombustionEngine {

	Turbo turbo;
	
	@Inject
	public V8Engine(Turbo turbo) {
		this.turbo = turbo;
	}
}
