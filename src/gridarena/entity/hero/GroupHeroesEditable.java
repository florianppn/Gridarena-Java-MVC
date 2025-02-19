package gridarena.entity.hero;

/**
 * Représente un groupe de heros éditable.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public interface GroupHeroesEditable extends GroupHeroes {
    
    /**
     * Ajouter un hero dasn le groupe.
     * 
     * @param h hero a ajouté.
     */
    public void addHero(Hero h);
    
}
