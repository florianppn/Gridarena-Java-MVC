package test.gridarena.entity.explosive;

import gridarena.entity.explosive.Bomb;
import gridarena.entity.hero.Hero;
import org.junit.Test;

import static gridarena.entity.explosive.Bomb.*;
import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe Bomb.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class BombTest {

    private Hero h1;
    private Bomb b1;
    
    public BombTest() {
        this.h1 = Hero.createHero("warrior", 0, 3);
        this.b1 = new Bomb(3, 18, this.h1);
    }
    
    @Test
    public void testSomeMethod() {
        assertEquals(DAMAGES, this.b1.getDamages());
        assertEquals(EXPLOSION_RADIUS, this.b1.getExplosionRadius());
        assertEquals(EXPLOSION_DELAY, this.b1.getExplosionDelay());
        assertEquals(this.h1, this.b1.getBelongsTo());
        assertEquals(this.h1.getSpecialization(), this.b1.getBelongsTo().getSpecialization());
        assertEquals(3, this.b1.getX());
        assertEquals(18, this.b1.getY());
    }
    
    @Test
    public void testDecrementeTimer() {
        int delay = this.b1.getExplosionDelay();
        this.b1.decrementeTimer();
        assertEquals(delay-1, this.b1.getExplosionDelay());
    }
    
}

