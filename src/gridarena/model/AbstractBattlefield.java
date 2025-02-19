package gridarena.model;

import gridarena.entity.Entity;
import gridarena.entity.hero.*;
import gridarena.utils.AbstractListenableModel;

import java.util.*;

/**
 * Représente un champ de bataille.
 * 
 * @author Florian Pépin
 * @version 1.0
*/
public abstract class AbstractBattlefield extends AbstractListenableModel {
    
    /**
     * Obtenir le héro qui est en train d'etre joué.
     * 
     * @return le hero en question.
     */
    public abstract Hero getCurrentHero();
    
    /**
     * Changer le hero qui est en train d'etre joué par un autre.
     * 
     * @param i indice du nouveau héro qui est en train d'etre joué.
     */
    public abstract void setCurrentHero(int i);
    
    /**
     * Obtenir la liste des héros.
     * 
     * @return la liste des heros.
     */
    public abstract GroupHeroesArrayList getHeroes();
    
    /**
     * Ajouter un héro dans la grille.
     * 
     * @param specialization du hero a ajouter.
     * @return le hero ajouté.
     */
    public abstract Hero addHero(String specialization);
    
    /**
     * Obtenir la grille de jeu.
     * 
     * @return la grille de jeu.
     */
    public abstract Entity[][] getGrid();
    
    /**
     * Obtenir la taille de la grille.
     * 
     * @return la taille de la grille.
     */
    public abstract int getSize(); 
    
    /**
     * Ajoute un explosif dans la grille.
     *
     * @param h le héro qui dépose la bomb.
     * @param explosif a ajouté dans la grille.
     * @param direction dans laquelle le joueur veut déployer une bombe.
     * @return true si le barrel est posé sinon false.
     */
    public abstract boolean addExplosive(Hero h, String explosif, String direction);

    /**
     * Déplace un joueur à une nouvelle position sur la grille.
     *
     * @param h Le héro à déplacer.
     * @param direction dans laquelle le joueur souhaite se diriger.
     * @return true si le joueur a été déplacé sinon false.
     */
    public abstract boolean moveHero(Hero h, String direction);

    /**
     * Active le bouclier du hero.
     * 
     * @param h hero qui active son bouclier.
     * @return true si le hero a pu activer son bouclier sinon false.
     */
    public abstract boolean activateShield(Hero h);
    
    /**
     * Tirer sur un joueur ou sur un barril.
     * Règle du jeu : tir horizontal ou vertical.
     * 
     * @param h le hero qui tire.
     * @param d direction du tire.
     * @return true si le joueur à touché un joueur ou un barril sinon false.
     */
    public abstract boolean shootHero(Hero h, String d);
    
    /**
     * Frapper un joueur avec une hache.
     * Règle du jeu : frapper horizontalement ou verticalement.
     * 
     * @param h le hero qui tire.
     * @param d direction du tire.
     * @return true si le joueur à touché un joueur sinon false.
     */
    public abstract boolean axAttack(Hero h, String d);
            
}
