package graphics;

import org.lwjgl.Version;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import scenes.Scene;
import scenes.TestScene;

public class WindowManager {

	private int width = 800;
	private int height = 600;
	private String title = "Test";

	private long window;

	private Scene currentScene;

	public WindowManager(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);

		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}

	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW.");
		}

		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);

		window = GLFW.glfwCreateWindow(this.width, this.height, this.title, MemoryUtil.NULL, MemoryUtil.NULL);

		if (window == MemoryUtil.NULL) {
			throw new IllegalStateException("Failed to create the GLFW window.");
		}

		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSwapInterval(1);

		GLFW.glfwShowWindow(window);

		GL.createCapabilities();

		currentScene = new TestScene();
		currentScene.init();
	}

	private void loop() {
		while (!GLFW.glfwWindowShouldClose(window)) {
			GLFW.glfwPollEvents();

			GL11.glClearColor(1.0f, 0f, 1.0f, 1.0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			currentScene.update();

			GLFW.glfwSwapBuffers(window);
		}
	}

	public static void main(String[] args) {
		new WindowManager(600, 800, "test").run();
	}

}