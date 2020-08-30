package physics.vectors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This Vector is as long as the array its initialized with.It's also immutable.
 * Childclasses fixing the soze of the vector should be 
 * @author Lennard
 * @param <C>
 */
public abstract class Vector <C extends Vector> {
    
    protected double[] values;

    /**
     * Clones the array, so it can ensure the array is immutable.You may use the refference to the original array as you wish.
     * @param dimensions how many dimensions this vector should have
     * @param values the values to initialize this vector with
     */
    public Vector(int dimensions, double... values) {
        if(dimensions != values.length) throw new VectorDimensionMissmatchException();
        this.values = values.clone();
    }
    
    public abstract int getDimensions();
    public abstract C copy();
    
    
    public final double getLength() {
        double s = 0;
        for(double d : values) {
            s += d;
        }
        return Math.sqrt(s);
    }
    
    public final C add(Vector<C> vec2) throws VectorDimensionMissmatchException {
        checkVectorCompatibility(vec2);
        C nv = copy();
        for(int i = 0; i < getDimensions(); i++) {
            nv.values[i] = values[i] + vec2.values[i];
        }
        return nv;
    }
    
    public final C substract(Vector<C> vec2) throws VectorDimensionMissmatchException {
        checkVectorCompatibility(vec2);
        C nv = copy();
        for(int i = 0; i < getDimensions(); i++) {
            nv.values[i] = values[i] - vec2.values[i];
        }
        return nv;
    }
    
    public final C multiply(double scalar) throws VectorDimensionMissmatchException {
        C nv = copy();
        for(int i = 0; i < getDimensions(); i++) {
            nv.values[i] = values[i] * scalar;
        }
        return nv;
    }
    
    public final C divide(double scalar) throws VectorDimensionMissmatchException {
        C nv = copy();
        for(int i = 0; i < getDimensions(); i++) {
            nv.values[i] = values[i] / scalar;
        }
        return nv;
    }
    
    public final double get(int index) throws VectorDimensionMissmatchException {
        if(index < 0 || index >= getDimensions())
            throw new VectorDimensionMissmatchException();
        else
            return values[index];
    }
    
    public final C set(int index, double value) throws VectorDimensionMissmatchException {
        if(index < 0 || index >= getDimensions())
            throw new VectorDimensionMissmatchException();
        else {
            C nv = copy();
            nv.values[index] = value;
            return nv;
        } 
    }
    
    /**
     * Returns an immutable List of all the values.
     * @return 
     */
    public final List<Double> getAll() {
        List<Double> list = Arrays.stream(values).boxed().collect(Collectors.toList());
        return Collections.unmodifiableList(list);
    }
    
    public final C onEach(DimensionOperator filter) {
        C nv = copy();
        for(int i = 0; i < nv.getDimensions(); i++) {
            nv.values[i] = filter.operationOnDimension(nv.values[i], i);
        }
        return nv;
    }
    
    public interface DimensionOperator {
    
        public abstract double operationOnDimension(double value, int index);
    
    }
    
    protected final void checkIndexCompatibility(int index) throws VectorDimensionMissmatchException {
        if(index < 0 || index >= getDimensions()) throw new VectorDimensionMissmatchException();
    }
    
    protected final void checkVectorCompatibility(Vector<C> vec2) throws VectorDimensionMissmatchException {
        if(getDimensions() != vec2.getDimensions()) throw new VectorDimensionMissmatchException();
    }
    
    @Override
    public String toString() {
        return "vec" + getDimensions() + Arrays.toString(values);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Vector)) return false;
        Vector uv = (Vector) obj;
        return Arrays.equals(values, uv.values);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Arrays.hashCode(this.values);
        return hash;
    }
    
    
    
    
    
    
}
