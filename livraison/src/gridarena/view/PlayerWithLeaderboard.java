package gridarena.view;

import java.util.*;

/**
 * Représente le joueur avec un affichage de fin de jeu.
 * 
 * @author Tom David
 * @version 1.0
 */
public interface PlayerWithLeaderboard extends Player {
    
    /**
     * Afficher la fin du jeu.
     * 
     * @param players liste des joueurs.
     */
    void showLeaderboard();
    
}
