package graphics.simpleWrapper;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Vbo {
	private int vboID;
	private int target;
	private int usage;

	public Vbo(int[] data) {
		this(data, GL15.GL_ELEMENT_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
	}

	public Vbo(int[] data, int target, int usage) {
		this.vboID = GL15.glGenBuffers();
		this.target = target;
		this.usage = usage;
		//vbos.add(vboID);
		bind();
		IntBuffer buffer = storeDataInIntBuffer(data);
		GL15.glBufferData(target, buffer, usage);
		//unbind();
	}

	public Vbo(float[] data) {
		this(data, GL15.GL_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
	}

	public Vbo(float[] data, int target, int usage) {
		this.vboID = GL15.glGenBuffers();
		this.target = target;
		this.usage = usage;
		//vbos.add(vboID);
		bind();
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(target, buffer, usage);
		unbind();
	}

	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public void bind() {
		GL15.glBindBuffer(target, vboID);
	}

	public void unbind() {
		GL15.glBindBuffer(target, 0);
	}
}
