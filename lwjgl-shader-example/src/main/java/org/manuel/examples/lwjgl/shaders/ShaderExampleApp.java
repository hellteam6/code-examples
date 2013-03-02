package org.manuel.examples.lwjgl.shaders;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.glu.GLU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaderExampleApp {

	final Logger logger = LoggerFactory.getLogger(ShaderExampleApp.class);
	
	public static void main(String[] args) { (new ShaderExampleApp()).run(); }

	public void run() {

		logger.info("ShaderExampleApp starting");
		
		try {
			
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
			Display.setVSyncEnabled(true);
			Display.setTitle("Pruebas LWJGL Manuel Martín 2013");
			Display.create();
		
		} catch (LWJGLException e) {
		
			logger.error("Error initializing display", e);
			System.exit(0);
		}

		logger.info("Display created");

		Time time = new Time(0.5f);
		
		initView();
		
		while(!Display.isCloseRequested()) {

			time.update();
			
			if(time.doFPS()) {
				Display.setTitle(String.format("%d fps", time.getFPS()));
			}
			
			if(!doController()) {
				break;
			} else {
				doView();
				
				Display.update();
				Display.sync(30);
			}
		
		}
		
		Display.destroy();
		
		logger.info("ShaderExampleApp exiting");
		
	}
	
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 768;
	
	public boolean doController() {
		
		while (Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void initView() {
		
		GL11.glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//Options
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		//Depth & color buffers default value
		GL11.glClearDepth(1.0f);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		//Projection
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45.0f, (float)WINDOW_WIDTH/(float)WINDOW_HEIGHT, 0.1f, 200.0f);
		
		//Default modes
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		//Create shader program
		try {
			setUpProgram();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void doView() {

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		GL20.glUseProgram(program);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		//Camera
		GL11.glTranslatef(0.0f, 0.0f, -5.0f);
		
		//Scene
		GL11.glColor3f(1.0f,1.0f,1.0f);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(0.0f, 0.0f, 0.0f);
		GL11.glVertex3f(1.0f, 0.0f, 0.0f);
		GL11.glVertex3f(1.0f, 1.0f, 0.0f);
		GL11.glVertex3f(0.0f, 1.0f, 0.0f);
		GL11.glEnd();
		
		GL20.glUseProgram(0);
		
	}
	
	//Shader stuffs
	private int program = 0;
	public void setUpProgram() throws Exception {
		
		logger.debug("creating shader src/main/resources/screen.vert");
		logger.debug("creating shader src/main/resources/screen.frag");
		
		int vertShader = ShaderUtils.createShader("src/main/resources/screen.vert",GL20.GL_VERTEX_SHADER);
        int fragShader = ShaderUtils.createShader("src/main/resources/screen.frag",GL20.GL_FRAGMENT_SHADER);
        
        program = GL20.glCreateProgram();
        GL20.glAttachShader(program, vertShader);
        GL20.glAttachShader(program, fragShader);
        GL20.glLinkProgram(program);
        
        if(GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
        	String infoLog = GL20.glGetProgramInfoLog(program,
					GL20.glGetProgrami(program, GL20.GL_INFO_LOG_LENGTH));
        	
        	logger.error("Error linking shader program");
        	
        	throw new RuntimeException(infoLog);
        	
        }
        
        GL20.glValidateProgram(program);
        if(GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
        	String infoLog = GL20.glGetProgramInfoLog(program,
					GL20.glGetProgrami(program, GL20.GL_INFO_LOG_LENGTH));
        	
        	logger.error("Error validating shader program");
        	
        	throw new RuntimeException(infoLog);
        }

	}
	
}
