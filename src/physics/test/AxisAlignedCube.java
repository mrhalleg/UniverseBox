package physics.test;

import physics.Collidable;
import physics.Inertial;
import physics.collisions.AABoundingBox;
import physics.vectors.Vector3;

/**
 * An axis alligned cube for early physics (and maybe rendering) testing.
 * @author Lennard
 */
public class AxisAlignedCube implements AABoundingBox<Vector3>, Collidable<Vector3>, Inertial<Vector3> {

    private double mass;
    private double size;
        
    private Vector3 velocity;
    private Vector3 position;

    public AxisAlignedCube(double mass, double size, Vector3 velocity, Vector3 position) {
        this.mass = mass;
        this.size = size;
        this.velocity = velocity;
        this.position = position;
    }
    
    public double getSize() {
        return size;
    }
    
    public void positionalChange(Vector3 vec) {
        position = position.add(vec);
    }
    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Vector3 getVelocity() {
        return velocity;
    }

    @Override
    public Vector3 getPosition() {
        return position;
    }
    
    @Override
    public void applyImpulse(Vector3 impulse) {
        velocity = velocity.add(impulse.divide(mass));
    }

    @Override
    public Vector3 getMin() {
        return position.onEach((pos, index) -> {
            return pos - (size / 2);
        });
    }

    @Override
    public Vector3 getMax() {
        return position.onEach((pos, index) -> {
            return pos + (size / 2);
        });
    }
    
    
}
