package scenes;

public abstract class Scene {

	public Scene() {
	}

	public abstract void init();

	public abstract void update(float dtime);

	public abstract void cleanUp();

}
