package graphics.data;

public class TexturedDataHandle extends DataHandle<TexturedDataHandle> {

	public TexturedDataHandle() {
		super(2, true);
	}

	public TexturedDataHandle storePositions(float[] positions) {
		super.store(0, positions, 3);
		return this;
	}

	public TexturedDataHandle storeTextureCoords(float[] coords) {
		super.store(1, coords, 2);
		return this;
	}
}
