package physics.collisions;

import physics.Collidable;
import physics.vectors.Vector;

/**
 *
 * @author Lennard
 * @param <V>
 */
public interface BoundingSphere <V extends Vector<V>> extends Collidable<V> {
    
    /**
     * Returns the radius of this bounding sphere.
     * @return the radius
     */
    public double getRadius();
}
