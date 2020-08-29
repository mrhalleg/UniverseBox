package graphics.shader;

public class TestShader extends ShaderProgram {

	public TestShader() {
		super("src/ressources/shaders/vert.txt", "src/ressources/shaders/frag.txt");
	}

	@Override
	protected void bindAttributes() {

	}
}
