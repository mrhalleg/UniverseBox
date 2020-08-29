package scenes;

import org.lwjgl.opengl.GL11;

import graphics.RawModelBuilder;
import graphics.RenderObject;

public class TestScene extends Scene {

	private RawModelBuilder builder;
	private RenderObject obj;

	@Override
	public void init() {
		builder = new RawModelBuilder();
		obj = new RenderObject(builder);

	}

	@Override
	public void update() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);

		obj.render();

	}

}
