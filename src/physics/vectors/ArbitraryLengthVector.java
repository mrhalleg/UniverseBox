package physics.vectors;

/**
 * 
 * @author Lennard
 */
public class ArbitraryLengthVector extends Vector<ArbitraryLengthVector> {

    public ArbitraryLengthVector(double... values) {
        super(values.length, values);
    }
    
    @Override
    public int getDimensions() {
        if(values != null)
            return values.length;
        else
            return 0;
    }

    @Override
    public ArbitraryLengthVector copy() {
        return new ArbitraryLengthVector(values);
    }
    
}
