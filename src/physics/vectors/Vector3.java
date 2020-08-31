package physics.vectors;

/**
 *
 * @author Lennard
 */
public class Vector3 extends Vector<Vector3> {

    public Vector3(double x, double y, double z) {
        super(3, x, y, z);
    }
    
    public Vector3(double[] values) {
        super(3, values);
    }

    public double x() {
        return get(0);
    }
    
    public double y() {
        return get(1);
    }
    
    public double z() {
        return get(2);
    }
    
    @Override
    public int getDimensions() {
        return 3;
    }

    @Override
    public Vector3 copy() {
        return new Vector3(values);
    }
    
}
