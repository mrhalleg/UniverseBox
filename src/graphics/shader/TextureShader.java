package graphics.shader;

import graphics.data.TexturedDataHandle;
import graphics.enities.Entity3D;
import graphics.renderObjects.Camera;
import org.lwjgl.opengl.GL11;

public class TextureShader extends ShaderProgram<TexturedDataHandle, Entity3D> {

	public TextureShader() {
		super("shaders/vert.shader", "shaders/frag.shader");
	}

	public void renderWithData(TexturedDataHandle data, Entity3D entity, Camera camera) {
		start();
		loadMatrix(getUniformLocation("transformMat"), entity.getTransformMatrix());
		loadMatrix(getUniformLocation("projectMat"), camera.getProjectionMatrix());
		loadMatrix(getUniformLocation("viewMat"), camera.getViewMatrix());

		data.enable();
		GL11.glDrawElements(GL11.GL_TRIANGLES, data.vertexCount(), GL11.GL_UNSIGNED_INT, 0);
		data.disable();
		stop();
	}
}
