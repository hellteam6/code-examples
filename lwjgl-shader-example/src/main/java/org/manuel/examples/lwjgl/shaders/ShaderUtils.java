package org.manuel.examples.lwjgl.shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderUtils {

	public static int createShader(String filename, int shaderType) throws Exception {
		
		int shader = 0;
		
		try {
			shader = GL20.glCreateShader(shaderType);

			if (shader == 0)
				return 0;

			GL20.glShaderSource(shader, Utils.readFileAsString(filename));
			GL20.glCompileShader(shader);

			if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
				String infoLog = GL20.glGetShaderInfoLog(shader,
						GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH));
				throw new RuntimeException(String.format("Error creating shader: %s", infoLog));
			}

			return shader;

		} catch (Exception ex) {
			throw ex;
		} finally {
			GL20.glDeleteShader(shader);
		}
	}
}
