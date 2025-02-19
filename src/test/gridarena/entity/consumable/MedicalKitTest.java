package test.gridarena.entity.consumable;

import gridarena.entity.consumable.MedicalKit;
import gridarena.entity.hero.Hero;
import org.junit.Test;

import static gridarena.entity.consumable.MedicalKit.HEALTHS;
import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe MedicalKit.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class MedicalKitTest {
    
    private MedicalKit mk1;
    private Hero h1;
    
    public MedicalKitTest() {
        this.mk1 = new MedicalKit(2, 8);
        this.h1 = Hero.createHero("mastodonte", 5, 3);
    }

    @Test
    public void testSomeMethod() {
        assertEquals(2, this.mk1.getX());
        assertEquals(8, this.mk1.getY());
    }
    
    @Test
    public void testUseConsumable() {
        int healthMax = this.h1.getHealthMax();
        assertEquals(healthMax, this.h1.getHealthRemaining());
        this.mk1.useConsumable(this.h1);
        assertEquals(healthMax, this.h1.getHealthRemaining());
        this.h1.setHealthRemaining(10);
        assertEquals(10, this.h1.getHealthRemaining());
        this.mk1.useConsumable(this.h1);
        assertEquals(10+HEALTHS, this.h1.getHealthRemaining());
    }
    
}
