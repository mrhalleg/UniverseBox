package graphics.renderObjects;

import graphics.data.TexturedDataHandle;
import graphics.enities.Entity3D;
import graphics.render.Camera;
import graphics.shader.TextureShader;

public class RenderObject3D extends RenderObject {
	private TextureShader shader;
	private TexturedDataHandle data;
	private Entity3D entity;

	public RenderObject3D(TextureShader shader, float[] positions, float[] textureCoords, int[] indices) {
		data = new TexturedDataHandle();
		data.init().storePositions(positions).storeTextureCoords(textureCoords).storeIndeces(indices).create();
		this.shader = shader;
		entity = new Entity3D();
	}

	@Override
	public void render() {

		entity.getRotation().rotateY(0.01f);
		entity.getRotation().rotateZ(0.01f);


		shader.renderWithData(data, entity, new Camera());

	}
}
