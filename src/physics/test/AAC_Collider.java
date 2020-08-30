package physics.test;

import physics.collisions.AABoundingBox;
import physics.vectors.Vector;

/**
 * Checks for collisions between two AABoundigBoxes.
 * not happy with this, because it's not elegant
 * @author Lennard
 * @param <V>
 */
public class AAC_Collider <V extends Vector<V>> {
    
    /**
     * Returns a Vector that tells you how to move a to undo this collision.
     * @param a bounding box a
     * @param b bounding box b
     * @return how to move a to get it out of the collision
     */
    public V collide(AABoundingBox<V> a, AABoundingBox<V> b) {
        
        
        /*
        V b_max = b.getMax();
        V b_min = b.getMin();
        V a_max = a.getMax();
        V a_min = a.getMin();
        
        System.out.printf("bmax: %s\n", b_max.toString());
        System.out.printf("bmin: %s\n", b_min.toString());
        System.out.printf("amax: %s\n", a_max.toString());
        System.out.printf("amin: %s\n", a_min.toString());
        */
        V diffa = b.getMax().substract(a.getMin());
        V diffb = a.getMax().substract(b.getMin());
        
        System.out.printf("Diffa: %s\n", diffa.toString());
        System.out.printf("Diffb: %s\n", diffb.toString());
        
        PossibleCollision pca = findCollisions(diffa);
        PossibleCollision pcb = findCollisions(diffb);
        if(pca == null || pcb == null) return null; //no collision
        
        if(pca.min_diff.get(pca.index) < pcb.min_diff.get(pcb.index)) 
            return pca.min_diff;
        else
            return pcb.min_diff.multiply(-1);
    }
    
    private PossibleCollision findCollisions(V diff) {
        
        int index_min = 0; //smallest amount >= 0
        for(int i = 0; i < diff.getDimensions(); i++) {
            double d = diff.get(i);
            if(d < 0) return null; //no collisions at all
            if(d < diff.get(index_min)) index_min = i;
        }
        
        final int fin_index_min = index_min;
        V min_diff = diff.onEach((value, index) -> {
            if(fin_index_min == index) return value;
            else return 0;
        });
        return new PossibleCollision(min_diff, index_min);
    }
    
    private class PossibleCollision {
        final V min_diff;
        final int index;

        public PossibleCollision(V min_diff, int index) {
            this.min_diff = min_diff;
            this.index = index;
        }
    }
}
