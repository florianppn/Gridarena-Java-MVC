package test.gridarena.entity.explosive;

import gridarena.entity.explosive.Mine;
import gridarena.entity.hero.Hero;
import org.junit.Test;

import static gridarena.entity.explosive.Mine.DAMAGES;
import static gridarena.entity.explosive.Mine.EXPLOSION_RADIUS;
import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe this.m1.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class MineTest {

    private Hero h1;
    private Mine m1;
    
    public MineTest() {
        this.h1 = Hero.createHero("assassin", 1, 2);
        this.m1 = new Mine(3, 18, this.h1);
    }
    
    @Test
    public void testSomeMethod() {
        assertEquals(DAMAGES, this.m1.getDamages());
        assertEquals(EXPLOSION_RADIUS, this.m1.getExplosionRadius());
        assertEquals(this.h1, this.m1.getBelongsTo());
        assertEquals(this.h1.getSpecialization(), this.m1.getBelongsTo().getSpecialization());
        assertEquals(3, this.m1.getX());
        assertEquals(18, this.m1.getY());
    }
    
    @Test
    public void testExplode() {
        int healthMax = this.h1.getHealthMax();
        this.h1.setImmune(true);
        assertEquals(healthMax, this.h1.getHealthRemaining());
        this.m1.explode(this.h1);
        assertEquals(healthMax, this.h1.getHealthRemaining());
        this.h1.setImmune(false);
        this.h1.setHealthRemaining(30);
        assertEquals(30, this.h1.getHealthRemaining());
        this.m1.explode(this.h1);
        assertEquals(30-DAMAGES, this.h1.getHealthRemaining());
    }
    
    @Test
    public void testExplodeWithNegativeValue() {
        this.h1.setHealthRemaining(0);
        assertEquals(0, this.h1.getHealthRemaining());
        this.m1.explode(this.h1);
        assertEquals(0, this.h1.getHealthRemaining());
    }
    
}
