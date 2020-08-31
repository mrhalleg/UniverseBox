package graphics.data;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class DataHandle<C extends DataHandle<?>> {
	private int vaoID;
	private int[] vboIDs;
	private int indexVboID;
	private int vertexCount;
	private boolean[] isSet;


	public DataHandle(int length) {
		vboIDs = new int[length];
		isSet = new boolean[length];
	}

	public C init() {
		vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		return (C) this;
	}

	public C create() {
		GL30.glBindVertexArray(0);
		for (boolean b : isSet) {
			if (!b) {
				throw new RuntimeException();
			}
		}
		if (indexVboID == 0) {
			throw new RuntimeException();
		}
		return (C) this;
	}

	public void enable() {
		GL30.glBindVertexArray(vaoID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexVboID);
		for (int i = 0; i < vboIDs.length; i++) {
			GL20.glEnableVertexAttribArray(i);
		}
	}

	public void disable() {
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		for (int i = 0; i < vboIDs.length; i++) {
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
	}

	protected void store(int index, float[] data, int size) {
		if (isSet[index]) {
			throw new RuntimeException();
		}
		isSet[index] = true;

		int vboID = GL15.glGenBuffers();
		vboIDs[index] = vboID;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public C storeIndeces(int[] data) {
		int vboID = GL15.glGenBuffers();
		indexVboID = vboID;
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(data);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		vertexCount = data.length;
		return (C) this;
	}

	protected void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public int vertexCount() {
		return this.vertexCount;
	}

	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
