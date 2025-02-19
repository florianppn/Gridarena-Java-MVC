package gridarena.controller.ia;

import gridarena.model.BattlefieldProxy;

import java.util.Random;

/**
 * Représente une stratégie de joueur robot naive.
 * Ce bot effectue des actions aléatoires parmi un ensemble prédéfini d'actions.
 * Les actions possibles sont : "move", "bomb", "mine", "shield", "shoot".
 * Les directions de déplacement possibles sont : "up", "down", "left", "right".
 * Les directions d'explosifs possibles sont : "ld", "down", "lu", "right", "left", "ru", "up", "rd".
 * Les spécialisations possibles sont : "warrior", "assassin", "mastodonte".
 *
 * @author Emilien Huron.
 * @version 1.0
 */
public class NoobyBotStrategy implements BotStrategy {

    // Actions possibles pour le bot
    protected static final String[] actions = {"move", "bomb", "mine", "shield", "shoot"};
    // Directions de déplacement possibles
    protected static final String[] moveDirections = {"up", "down", "left", "right"};
    // Directions d'explosifs possibles
    protected static final String[] explosiveDirections = {"ld", "down", "lu", "right", "left", "ru", "up", "rd"};
    // Spécialisations possibles pour le bot
    protected static final String[] specializations = {"warrior", "assassin", "mastodonte"};

    /**
     * Définit la stratégie d'action du bot.
     * Si le héro du bot n'est pas encore défini, il en ajoute un avec une spécialisation aléatoire.
     * Sinon, il effectue une action aléatoire parmi les actions possibles.
     *
     * @param battlefieldProxy Le proxy du champ de bataille utilisé pour interagir avec le modèle.
     */
    @Override
    public void actionStrategy(BattlefieldProxy battlefieldProxy) {
        Random random = new Random();
        if (battlefieldProxy.getHero() == null) {
            // Ajoute un héros avec une spécialisation aléatoire
            String specialization = NoobyBotStrategy.specializations[random.nextInt(NoobyBotStrategy.specializations.length)];
            battlefieldProxy.addHero(specialization);
        } else {
            // Effectue une action aléatoire
            String action = NoobyBotStrategy.actions[random.nextInt(NoobyBotStrategy.actions.length)];
            String direction = "";
            switch(action) {
                case "move":
                    direction = NoobyBotStrategy.moveDirections[random.nextInt(NoobyBotStrategy.moveDirections.length)];
                    battlefieldProxy.moveHero(battlefieldProxy.getHero(), direction);
                    break;
                case "bomb":
                    direction = NoobyBotStrategy.explosiveDirections[random.nextInt(NoobyBotStrategy.explosiveDirections.length)];
                    battlefieldProxy.addExplosive(battlefieldProxy.getHero(), direction, "bomb");
                    break;
                case "mine":
                    direction = NoobyBotStrategy.explosiveDirections[random.nextInt(NoobyBotStrategy.explosiveDirections.length)];
                    battlefieldProxy.addExplosive(battlefieldProxy.getHero(), direction, "mine");
                    break;
                case "shield":
                    battlefieldProxy.activateShield(battlefieldProxy.getHero());
                    break;
                case "shoot":
                    direction = NoobyBotStrategy.moveDirections[random.nextInt(NoobyBotStrategy.moveDirections.length)];
                    battlefieldProxy.shootHero(battlefieldProxy.getHero(), direction);
                    break;
                default:
                    throw new RuntimeException("action inexistante : " + action);
            }
        }
    }

}