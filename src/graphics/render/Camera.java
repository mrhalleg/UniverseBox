package graphics.render;

import org.joml.Matrix4f;

public class Camera {

	public Matrix4f getProjectionMatrix() {
		return new Matrix4f().perspective((float) Math.toRadians(45.0f), 1.0f, 0.01f, 100.0f)
				.lookAt(0.0f, 0.0f, 10.0f,
						0.0f, 0.0f, 0.0f,
						0.0f, 1.0f, 0.0f);
	}

	public Matrix4f getViewMatrix() {
		return new Matrix4f();
	}
}
