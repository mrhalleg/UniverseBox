package physics;

import physics.vectors.Vector;

/**
 * An Object that has an position.
 * @author Lennard
 * @param <V>
 */
public interface Positional <V extends Vector<V>> {
    
    /**
     * This Inertial's posiiton;
     * @return position as m from center
     */
    public V getPosition();
    
}
