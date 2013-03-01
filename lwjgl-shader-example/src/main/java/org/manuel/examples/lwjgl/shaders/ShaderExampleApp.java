package org.manuel.examples.lwjgl.shaders;

import org.manuel.engine.EngineException;
import org.manuel.engine.UI;
import org.manuel.engine.exampleapp.SimpleController;
import org.manuel.engine.exampleapp.SimpleView;
import org.manuel.engine.math.Vector3f;
import org.manuel.engine.mvc.Controller;
import org.manuel.engine.mvc.Model;
import org.manuel.engine.mvc.View;
import org.manuel.engine.mvc.model.Entity;
import org.manuel.engine.mvc.model.HashMapModel;
import org.manuel.engine.mvc.model.InertialCamera;
import org.manuel.engine.utils.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaderExampleApp {

	final Logger logger = LoggerFactory.getLogger(ShaderExampleApp.class);
	
	public static void main(String[] args) { (new ShaderExampleApp()).run(); }

	public void run() {

		logger.info("ShaderExampleApp starting");
		
		final UI ui = new UI();
		
		ui.setInitCallback(new Function() { @Override public void exec() {
		
			try {
				
				Model<String, Entity> model = new HashMapModel<String, Entity>();
				
				InertialCamera defaultCamera = new InertialCamera();
				defaultCamera.setPosition(new Vector3f(0.0f, 0.0f, 10.0f));
				model.put("firstPersonCam1", defaultCamera);
				
				// Create view and controller
				View view = new SimpleView(ui, model, defaultCamera);
				Controller controller = new SimpleController(ui, view, model);
				
				// Attach view and controller to the UI context
				ui.attachView(view);
				ui.attachController(controller);
				
				ui.requestFocus();
				
				ui.loop();
			
			} catch(EngineException e) {
				
				logger.error(e.getMessage());
				
			}
			
		}});
		
		logger.info("ShaderExampleApp exiting");
		
	}
	
}
