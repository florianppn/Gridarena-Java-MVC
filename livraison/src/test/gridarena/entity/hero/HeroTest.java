package test.gridarena.entity.hero;

import gridarena.entity.explosive.Bomb;
import gridarena.entity.explosive.Explosive;
import gridarena.entity.explosive.Mine;
import gridarena.entity.hero.Hero;
import org.junit.Test;

import static gridarena.entity.hero.Hero.AX_DAMAGE;
import static gridarena.entity.hero.Hero.WEAPON_DAMAGE;
import static org.junit.Assert.*;

/**
 * Représente les testes de la classe Hero.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class HeroTest {
    
    private Hero h1;
    private Hero h2;
    private Hero h3;
    private Hero h4;
    
    public HeroTest() {
        this.h1 = Hero.createHero("assassin", 0, 0);
        this.h2 = Hero.createHero("warrior", 1, 1);
        this.h3 = Hero.createHero("mastodonte", 2, 3);
        this.h4 = Hero.createHero("Hello World !", 3, 2);
    }
    
    @Test
    public void testSomeMethod() {
        this.h1 = Hero.createHero("assassin", 0, 0);
        this.h2 = Hero.createHero("warrior", 1, 1);
        this.h3 = Hero.createHero("mastodonte", 2, 3);
        this.h4 = Hero.createHero("Hello World !", 3, 2);
        assertEquals("assassin", this.h1.getSpecialization());
        assertEquals("warrior", this.h2.getSpecialization());
        assertEquals("mastodonte", this.h3.getSpecialization());
        assertEquals("warrior", this.h4.getSpecialization());
    }
    
    @Test
    public void testIsAlive() {
        this.h1.setHealthRemaining(5);
        assertTrue(this.h1.isAlive());
        this.h1.setHealthRemaining(0);
        assertTrue(!this.h1.isAlive());
        this.h1.setHealthRemaining(20);
        this.h1.setHealthRemaining(20-23);
        assertEquals(0, this.h1.getHealthRemaining());
    }
    
    @Test
    public void testShootHero() {
        this.h2.setHealthRemaining(20);
        this.h3.shootHero(this.h2);
        assertEquals(20-WEAPON_DAMAGE, this.h2.getHealthRemaining());
    }
    
    @Test
    public void testShootHeroWithNegativeValue() {
        this.h2.setHealthRemaining(0);
        this.h3.shootHero(this.h2);
        assertEquals(0, this.h2.getHealthRemaining());
    }
    
    @Test
    public void testAxAttack() {
        this.h2.setHealthRemaining(20);
        this.h3.axAttack(this.h2);
        assertEquals(20-AX_DAMAGE, this.h2.getHealthRemaining());
    }
    
    @Test
    public void testAxAttackWithNegativeValue() {
        this.h2.setHealthRemaining(0);
        this.h3.axAttack(this.h2);
        assertEquals(0, this.h2.getHealthRemaining());
    }
    
    @Test
    public void testDeployExplosive() {
        this.h4.setMineRemaining(0);
        this.h4.setBombRemaining(0);
        Mine m1 = (Mine) this.h4.deployExplosive(2, 3, "mine");
        Bomb b1 = (Bomb) this.h4.deployExplosive(5, 3, "bomb");
        Explosive e1 = this.h4.deployExplosive(5, 3, "Hello World !");
        assertEquals(null, m1);
        assertEquals(null, b1);
        assertEquals(null, e1);
        this.h3.setMineRemaining(1);
        this.h3.setBombRemaining(1);
        Mine m2 = (Mine) this.h3.deployExplosive(2, 3, "mine");
        Bomb b2 = (Bomb) this.h3.deployExplosive(5, 3, "bomb");
        Explosive e2 = this.h3.deployExplosive(5, 3, "Hello World !");
        assertNotEquals(null, m2);
        assertEquals(2, m2.getX());
        assertNotEquals(null, b2);
        assertEquals(5, b2.getX());
        assertEquals(null, e2);
        assertEquals(0, this.h3.getMineRemaining());
        assertEquals(0, this.h3.getBombRemaining());
    }
    
}
