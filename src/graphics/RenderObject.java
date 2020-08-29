package graphics;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import graphics.shader.ShaderProgram;
import graphics.shader.TestShader;

public class RenderObject {
	private RawModel model;
	private ShaderProgram shader;

	public RenderObject(RawModelBuilder builder) {
		shader = new TestShader();

		float[] vertices = { -0.5f, 0.5f, 0, // V0
				-0.5f, -0.5f, 0, // V1
				0.5f, -0.5f, 0, // V2
				0.5f, 0.5f, 0 // V3
		};

		int[] indices = { 0, 1, 3, // Top left triangle (V0,V1,V3)
				3, 1, 2 // Bottom right triangle (V3,V1,V2)
		};

		model = builder.loadToVAO(vertices, indices);
	}

	public void render() {

		shader.start();
		shader.loadMatrix(0, new Matrix4f().rotateY(2));

		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
