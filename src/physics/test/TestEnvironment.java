package physics.test;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import physics.vectors.Vector3;

/**
 * Shitty Ass Test Fuck You I'm the Best Programmer alive Fuck Yeah
 * @author Lennard
 */
public class TestEnvironment {
    
    AxisAlignedCube cube1;
    AxisAlignedCube cube2;
    
    public static void main(String[] args) {
        new TestEnvironment().doIt();
    }
    
    public void doIt() {
        Scanner sc = new Scanner(System.in);
        
        cube1 = new AxisAlignedCube(5, 2, new Vector3(0, 0, 0), new Vector3(0, 0, 0));
        cube2 = new AxisAlignedCube(5, 2, new Vector3(0, 0, 0), new Vector3(5, 0, 0));
        int active_axis = 0;
        
        while(true) {
            
            
            System.out.println("Cube1:");
            printCube(cube1);
            System.out.println("Cube2:");
            printCube(cube2);
            
            AAC_Collider<Vector3> collider = new AAC_Collider<>();
            Vector3 diffa = collider.collide(cube1, cube2);
            if(diffa != null) {
                System.out.printf("collision detected\n");
                System.out.printf("Diff: %s\n", diffa.toString());
                
                diffa = diffa.divide(2);
                cube1.positionalChange(diffa);
                cube2.positionalChange(diffa.multiply(-1));
                
                System.out.printf("collision changes made\n");
                System.out.println("Cube1:");
                printCube(cube1);
                System.out.println("Cube2:");
                printCube(cube2);
            } else {
                System.out.printf("no collision detected\n");
            }
            
            commandLoop:
            while(true) {
                System.out.printf("Active axis: %d\n", active_axis);
                try {
                    String s = sc.nextLine();
                    char c = s.charAt(0);
                    
                    switch(c) {
                        case ' ': {
                            System.out.println("skipping");
                        } break commandLoop;
                        case 'w': {
                            Vector3 v = getVector3ForDirection(active_axis);
                            cube1.positionalChange(v);
                        } break commandLoop;
                        case 's': {
                            Vector3 v = getVector3ForDirection(active_axis);
                            v.multiply(-1);
                            cube1.positionalChange(v);
                        } break commandLoop;
                        case 'a': {
                            active_axis--;
                            if(active_axis < 0) active_axis = 2;
                        } continue commandLoop;
                        case 'd': {
                            active_axis++;
                            if(active_axis > 2) active_axis = 0;
                        } continue commandLoop;
                            
                    }
                    
                } catch (NoSuchElementException ex) {
                    ex.printStackTrace();
                }
            }
            
            
        }
        
    }
    
    private Vector3 getVector3ForDirection(int active_direction) {
        Vector3 v = new Vector3(0, 0, 0);
        v = v.set(active_direction, 0.5);
        return v;
    }
    
    private void printCube(AxisAlignedCube cube) {
        System.out.printf("Cube Position: %s\n", cube.getPosition().toString());
        System.out.printf("Cube Start: %s\n", cube.getMin().toString());
        System.out.printf("Cube End: %s\n", cube.getMax().toString());
        System.out.printf("Cube Velocity: %s\n", cube.getVelocity().toString());
    }
    
}
