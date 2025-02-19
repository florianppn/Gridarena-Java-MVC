package test.gridarena.entity.environment;

import gridarena.entity.environment.Wall;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe Wall.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class WallTest {

    @Test
    public void testSomeMethod() {
       Wall wall = new Wall(2, 3);
       assertEquals(2, wall.getX());
       assertEquals(3, wall.getY());
    }
    
}
