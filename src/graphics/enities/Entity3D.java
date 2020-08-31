package graphics.enities;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Entity3D extends Entity {

	public Entity3D() {
		position = new Vector3f(0f, 0f, 0f);
		rotation = new Quaternionf();
		scale = new Vector3f(1f, 1f, 1f);
	}

	private Vector3f position;
	private Quaternionf rotation;
	private Vector3f scale;

	public Matrix4f getTransformMatrix() {
		Matrix4f transMat = new Matrix4f();
		transMat.rotate(rotation);
		transMat.translate(position);
		transMat.scale(scale);

		Matrix4f projectMat = new Matrix4f();
		projectMat.perspective((float) Math.toRadians(45.0f), 1.0f, 0.01f, 100.0f);
		projectMat.lookAt(0.0f, 0.0f, 10.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f);
		return transMat;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Quaternionf getRotation() {
		return rotation;
	}

	public void setRotation(Quaternionf rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
}
