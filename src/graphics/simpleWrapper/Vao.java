package graphics.simpleWrapper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Vao {
	private int vaoID;
	private int vertexCount;


	private Vbo[] attributes;
	private Vbo indeces;

	public Vao() {
		vaoID = GL30.glGenVertexArrays();
		attributes = new Vbo[16];
	}

	public void loadAttribList(int attributeNumber, Vbo vbo, int size) {
		loadAttribList(attributeNumber, vbo, size, 0, 0);
	}

	public void loadAttribList(int attributeNumber, Vbo vbo, int size, int stride, int offset) {
		bind();
		vbo.bind();
		GL20.glVertexAttribPointer(attributeNumber, size, GL11.GL_FLOAT, false, stride, offset);
		unbind();
		vbo.unbind();
	}

	public void index(int[] index) {
		GL30.glBindVertexArray(vaoID);
		int vboId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, index, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}

	public void bind() {
		GL30.glBindVertexArray(vaoID);
	}

	public void unbind() {
		GL30.glBindVertexArray(0);
	}

	public void enableAttribArrays() {
		GL30.glBindVertexArray(vaoID);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
	}

	public void disableAttribArrays() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
