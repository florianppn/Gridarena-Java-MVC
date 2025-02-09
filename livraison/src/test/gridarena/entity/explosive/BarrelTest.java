package test.gridarena.entity.explosive;

import gridarena.entity.explosive.Barrel;
import gridarena.entity.hero.Hero;
import org.junit.Test;

import static gridarena.entity.explosive.Barrel.DAMAGES;
import static gridarena.entity.explosive.Barrel.EXPLOSION_RADIUS;
import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe Barrel.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class BarrelTest {
    
    private Barrel b1;
    private Hero h1;
    
    public BarrelTest() {
        this.b1 = new Barrel(3, 18);
        this.h1 = Hero.createHero("assassin", 3, 9);
    }
    
    @Test
    public void testSomeMethod() {
        assertEquals(DAMAGES, this.b1.getDamages());
        assertEquals(EXPLOSION_RADIUS, this.b1.getExplosionRadius());
        assertEquals(null, this.b1.getBelongsTo());
        assertEquals(3, this.b1.getX());
        assertEquals(18, this.b1.getY());
    }
    
    @Test
    public void testExplode() {
        int healthMax = this.h1.getHealthMax();
        this.h1.setImmune(true);
        assertEquals(healthMax, this.h1.getHealthRemaining());
        this.b1.explode(this.h1);
        assertEquals(healthMax, this.h1.getHealthRemaining());
        this.h1.setImmune(false);
        this.h1.setHealthRemaining(30);
        assertEquals(30, this.h1.getHealthRemaining());
        this.b1.explode(this.h1);
        assertEquals(30-DAMAGES, this.h1.getHealthRemaining());
    }
    
    @Test
    public void testExplodeWithNegativeValue() {
        this.h1.setHealthRemaining(0);
        assertEquals(0, this.h1.getHealthRemaining());
        this.b1.explode(this.h1);
        assertEquals(0, this.h1.getHealthRemaining());
    }
    
}
