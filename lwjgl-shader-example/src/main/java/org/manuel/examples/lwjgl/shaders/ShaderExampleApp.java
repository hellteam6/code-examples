package org.manuel.examples.lwjgl.shaders;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaderExampleApp {

	final Logger logger = LoggerFactory.getLogger(ShaderExampleApp.class);
	
	public static void main(String[] args) { (new ShaderExampleApp()).run(); }

	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 768;
	
	//program state
	private float lightspin = 0.0f;
	private boolean shaderOn = true;
	
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

			if(doController()) {
				
				update();
				
				doView();
				
				Display.update();
				Display.sync(30);
			} else {
				break;
			}
			
		}
		
		Display.destroy();
		
		logger.info("ShaderExampleApp exiting");
		
	}
	
	public void update() {
		lightspin += 0.01f;
	}
	
	public boolean doController() {
		
		while (Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					return false;
				} else if(Keyboard.getEventKey() == Keyboard.KEY_S) {
					shaderOn = shaderOn ? false : true;
				}
			}
		}
		
		return true;
	}
	
	//Lighting stuffs
	private void setUpLighting() {
		GL11.glEnable(GL11.GL_LIGHT1);
		
		FloatBuffer ambient = BufferUtils.createFloatBuffer(4).put(new float[]{ 0.6f, 0.6f, 0.6f, 1.0f });
		ambient.rewind();
		FloatBuffer diffuse = BufferUtils.createFloatBuffer(4).put(new float[]{ 0.6f, 0.6f, 0.6f, 1.0f });
		diffuse.rewind();
		FloatBuffer specular = BufferUtils.createFloatBuffer(4).put(new float[]{ 0.5f, 0.5f, 0.5f, 1.0f });
		specular.rewind();
		FloatBuffer position = BufferUtils.createFloatBuffer(4).put(
				new float[] { (float) Math.cos(lightspin) * 3.0f, 0.0f,
						(float) Math.sin(lightspin) * 3.0f, 1.0f });
		position.rewind();
		
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, ambient);
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, diffuse);
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, specular);
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, position);	
	}
	
	private void renderLight() {
		GL11.glTranslatef((float)Math.cos(lightspin)*3.0f , 0.0f,(float)Math.sin(lightspin)*3.0f);
		FloatBuffer position = BufferUtils.createFloatBuffer(4).put(
				new float[] { (float) Math.cos(lightspin) * 3.0f, 0.0f,
						(float) Math.sin(lightspin) * 3.0f, 1.0f });
		position.rewind();
		GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, position);
		Sphere s2 = new Sphere();
		s2.draw(0.2f, 10, 10);
	}
	
	//Shader stuffs
	private int program = 0;
	public void setUpProgram() throws Exception {
		
		int vertShader = ShaderUtils.createShader("src/main/resources/basic_diffuse.vert",GL20.GL_VERTEX_SHADER);
        int fragShader = ShaderUtils.createShader("src/main/resources/basic_diffuse.frag",GL20.GL_FRAGMENT_SHADER);
        
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
	
	public void initView() {
		
		GL11.glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//Options

		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		//Depth & color buffers default value
		GL11.glClearDepth(1.0f);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		//Lightning
		setUpLighting();
		
		//Projection
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(45.0f, (float)WINDOW_WIDTH/(float)WINDOW_HEIGHT, 0.1f, 200.0f);

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
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		//Camera
		GL11.glTranslatef(0.0f, 0.0f, -10.0f);
		
		//Mesh
		GL20.glUseProgram(shaderOn ? program : 0);
		Sphere s = new Sphere();
		s.draw(1.0f, 10, 10);
		GL20.glUseProgram(0);

		//Light
		renderLight();
		
	}
	

	
}
