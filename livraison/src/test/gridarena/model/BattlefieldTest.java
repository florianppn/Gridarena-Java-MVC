package test.gridarena.model;

import gridarena.entity.Entity;
import gridarena.entity.hero.Hero;
import gridarena.model.Battlefield;
import gridarena.model.fillgrid.RandomFillStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe Battlefield.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class BattlefieldTest {
    
    private Battlefield battlefield;
    private Hero h1;
    
    public BattlefieldTest() {
        this.battlefield = new Battlefield(7, 3, 4, 4, 3, new RandomFillStrategy());
        this.h1 = this.battlefield.addHero("mastodonte");
    }
    
    @Test
    public void testEntityInsertion() {
        Entity[][] grid = this.battlefield.getGrid();
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    cnt ++;
                }
            }
        }
        assertEquals(cnt, 15);
    }
    
    @Test
    public void testShootHero() {
        this.battlefield.shootHero(h1, "up");
        this.battlefield.shootHero(h1, "right");
        this.battlefield.shootHero(h1, "left");
        this.battlefield.shootHero(h1, "down");
        this.battlefield.shootHero(h1, "ld");
        this.battlefield.shootHero(h1, "Hello World !");
    }
    
    @Test
    public void testAxAttack() {
        this.battlefield.axAttack(h1, "up");
        this.battlefield.axAttack(h1, "right");
        this.battlefield.axAttack(h1, "left");
        this.battlefield.axAttack(h1, "down");
        this.battlefield.axAttack(h1, "ld");
        this.battlefield.axAttack(h1, "Hello World !");
    }
    
    @Test
    public void testActivateShield() {
        this.battlefield.activateShield(h1);
    }
    
}
