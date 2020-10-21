package physics.vectors;

/**
 *
 * @author Lennard
 */
public class Vector3i extends Vectori<Vector3i> {

    public Vector3i(int x, int y, int z) {
        super(3, x, y, z);
    }
    
    public Vector3i(int[] values) {
        super(3, values);
    }

    public int x() {
        return get(0);
    }
    
    public int y() {
        return get(1);
    }
    
    public int z() {
        return get(2);
    }
    
    @Override
    public int getDimensions() {
        return 3;
    }

    @Override
    public Vector3i copy() {
        return new Vector3i(values);
    }
    
}
