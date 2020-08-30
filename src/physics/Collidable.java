package physics;

import physics.vectors.Vector;

/**
 * An physical Object that can Collide with something.
 * @author Lennard
 * @param <V>
 */
public interface Collidable <V extends Vector<V>> extends Positional<V> {
    
}
