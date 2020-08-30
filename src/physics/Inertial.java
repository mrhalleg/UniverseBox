package physics;

import physics.vectors.Vector;

/**
 * An physical object that has mass (also can move = has speed).
 * @author Lennard
 * @param <V>
 */
public interface Inertial <V extends Vector<V>> {
    
    /**
     * Returns this Inertial's mass.
     * @return mass in kg
     */
    public double getMass();
    
    /**
     * This Inertial's velocity.
     * @return velocity in m/t
     */
    public V getVelocity();
    
    /**
     * Applys an impulse to this Inertia.
     * @param impulse impulse in kg*m/t = N*t
     */
    public void applyImpulse(V impulse);
}
