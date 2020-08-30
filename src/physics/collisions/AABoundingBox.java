package physics.collisions;

import physics.Collidable;
import physics.vectors.Vector;

/**
 *
 * @author Lennard
 * @param <V>
 */
public interface AABoundingBox <V extends Vector<V>> extends Collidable<V> {
    
    /**
     * Returns the corner that has the lowest values for each dimension.
     * @return corner with the lowest values
     */
    public V getMin();
    
    /**
     * Returns the corner that has the highest values for each dimension.
     * @return  corner with the highest values
     */
    public V getMax();
    
}
