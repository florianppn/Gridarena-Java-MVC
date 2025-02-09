package gridarena.controller.ia;

import gridarena.model.BattlefieldProxy;

/**
 * Représente la stratégie d'un joueur robot.
 * 
 * @author Emilien Huron.
 * @version 1.0
 */
public interface BotStrategy {
    
    /**
     * Définit la stratégie d'action du bot.
     * 
     * @param battlefieldProxy Le proxy du champ de bataille utilisé pour interagir avec le modèle.
     */
    void actionStrategy(BattlefieldProxy battlefieldProxy);
    
}