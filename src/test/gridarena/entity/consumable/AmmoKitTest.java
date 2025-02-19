package test.gridarena.entity.consumable;

import gridarena.entity.consumable.AmmoKit;
import gridarena.entity.hero.Hero;
import org.junit.Test;

import static gridarena.entity.consumable.AmmoKit.AMMOS;
import static org.junit.Assert.assertEquals;

/**
 * Représente les testes de la classe AmmoKit.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class AmmoKitTest {
    
    private AmmoKit ak1;
    private Hero h1;
    
    public AmmoKitTest() {
        this.ak1 = new AmmoKit(1, 4);
        this.h1 = Hero.createHero("warrior", 2, 3);
    }

    @Test
    public void testSomeMethod() {
        assertEquals(1, this.ak1.getX());
        assertEquals(4, this.ak1.getY());
    }
    
    @Test
    public void testUseConsumable() {
        int healthMax = this.h1.getHealthMax();
        assertEquals(healthMax, this.h1.getAmmoRemaining());
        this.ak1.useConsumable(this.h1);
        assertEquals(healthMax, this.h1.getAmmoRemaining());
        this.h1.setAmmoRemaining(4);
        assertEquals(4, this.h1.getAmmoRemaining());
        this.ak1.useConsumable(this.h1);
        assertEquals(4+AMMOS, this.h1.getAmmoRemaining());
    }
    
}
