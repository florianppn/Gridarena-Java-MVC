package gridarena.controller.ia;

import gridarena.entity.Entity;
import gridarena.entity.consumable.Consumable;
import gridarena.entity.environment.Wall;
import gridarena.entity.explosive.Barrel;
import gridarena.entity.explosive.Mine;
import gridarena.entity.hero.Hero;
import gridarena.model.BattlefieldProxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Représente une stratégie de joueur robot plus avancée.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class AdvancedBotStrategy implements BotStrategy {
    
    // Directions de déplacement possibles
    protected static final String[] moveDirections = {"up", "down", "left", "right"};
    // Directions d'explosifs possibles
    protected static final String[] explosiveDirections = {"up", "down", "left", "right", "ld", "lu", "ru", "rd"};

    /**
     * Définit la stratégie d'action du bot.
     * Si le héro du bot n'est pas encore défini, il en ajoute un avec la spécialité warrior.
     * Sinon, il effectue une action plus réfléchi parmi certains choix.
     *
     * @param battlefieldProxy Le proxy du champ de bataille utilisé pour interagir avec le modèle.
     */
    @Override
    public void actionStrategy(BattlefieldProxy battlefieldProxy) {
        if (battlefieldProxy.getHero() == null) {
            battlefieldProxy.addHero("warrior");
        } else {
            Random random = new Random();
            Hero hero = battlefieldProxy.getHero();
            String axDirection = this.findAvailableDirection(battlefieldProxy, hero, true);
            String shootDirection = this.isTargetInRange(battlefieldProxy, hero);
            String moveDirection = this.findAvailableDirection(battlefieldProxy, hero, false);
            String explosiveDirection = this.findMineLocation(battlefieldProxy, hero);
            if (!axDirection.equals("none")) {
                battlefieldProxy.axAttack(hero, axDirection);
            } else if (!shootDirection.equals("none")) {
                battlefieldProxy.shootHero(hero, shootDirection);
            } else {
                int[] values = {0, 0, 0, 1};
                int choice = values[random.nextInt(values.length)];
                switch(choice) {
                    case 0:
                        if (!moveDirection.equals("none")) {
                            battlefieldProxy.moveHero(hero, moveDirection);
                        } else if ((!explosiveDirection.equals("none"))) {
                            battlefieldProxy.addExplosive(hero, explosiveDirection, "mine");
                        } else {
                            battlefieldProxy.activateShield(hero);
                        }
                        break;
                    case 1:
                        if (!explosiveDirection.equals("none")) {
                            battlefieldProxy.addExplosive(hero, explosiveDirection, "mine");
                        } else if (!moveDirection.equals("none")) {
                            battlefieldProxy.moveHero(hero, moveDirection);
                        } else {
                            battlefieldProxy.activateShield(hero);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    /**
     * Cherche une direction où le héro peut poser une mine.
     * Si le bot peut placer une bombe sur 1 seul emplacement de déplacement
     * Alors il ne peut pas poser de mine, afin d'assurer au bot de ne pas prendre de dégâts.â
     * 
     * @param battlefieldProxy modèle restreint uniquement afin de ne pas tricher.
     * @param hero qui cherche une direction.
     * @return une direction.
     */
    private String findMineLocation(BattlefieldProxy battlefieldProxy, Hero hero) {
        Entity[][] grid = battlefieldProxy.getGrid();
        int size = grid.length;
        int[][] directions = {{-1, 0},{1, 0},{0, -1},{0, 1},{1, -1},{-1, -1},{-1, 1},{1, 1}};
        List<String> locations = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        int x = hero.getX();
        int y = hero.getY();
        for (int i = 0; i < directions.length; i++) {
            if (x+directions[i][0] >= 0 && x+directions[i][0] < size && y+directions[i][1] >= 0 && y+directions[i][1] < size) {
                if (grid[x+directions[i][0]][y+directions[i][1]] == null) {
                    if (i < 4) {
                        moves.add(AdvancedBotStrategy.explosiveDirections[i]);
                    }
                    locations.add(AdvancedBotStrategy.explosiveDirections[i]);
                }
            }
        }
        if (moves.size() > 1) {
            Collections.shuffle(locations);
            return locations.get(0);   
        }
        return "none";
    }
    
    /**
     * Cherche une direction où le hero peut avancer.
     * 
     * @param battlefieldProxy modèle restreint uniquement afin de ne pas tricher.
     * @param hero qui cherche une direction.
     * @return une direction.
     */
    private String findAvailableDirection(BattlefieldProxy battlefieldProxy, Hero hero, boolean state) {
        Entity[][] grid = battlefieldProxy.getGrid();
        int size = grid.length;
        List<String> locations = new ArrayList<>();
        int[][] directions = {{-1, 0},{1, 0},{0, -1},{0, 1}};
        int x = hero.getX();
        int y = hero.getY();
        for (int i = 0; i < directions.length; i++) {
            if (x+directions[i][0] >= 0 && x+directions[i][0] < size && y+directions[i][1] >= 0 && y+directions[i][1] < size) {
                if (state && grid[x+directions[i][0]][y+directions[i][1]] instanceof Hero) {
                    locations.add(AdvancedBotStrategy.moveDirections[i]);   
                } else if (!state && (grid[x+directions[i][0]][y+directions[i][1]] == null || grid[x+directions[i][0]][y+directions[i][1]] instanceof Consumable || grid[x+directions[i][0]][y+directions[i][1]] instanceof Mine)) {
                    locations.add(AdvancedBotStrategy.moveDirections[i]);   
                }
            }
        }
        if (!locations.isEmpty()) {
            Collections.shuffle(locations);
            return locations.get(0);
        }
        return "none";
    }
    
    /**
     * Cherche une direction vers laquelle se trouve un hero (max case autour de lui).
     * 
     * @param battlefieldProxy modèle restreint uniquement afin de ne pas tricher.
     * @param hero qui cherche un joueur.
     * @return une direction.
     */
    private String isTargetInRange(BattlefieldProxy battlefieldProxy, Hero hero) {
        Entity[][] grid = battlefieldProxy.getGrid();
        int size = grid.length;
        List<String> locations = new ArrayList<>();
        int[][] directions = {{-1, 0},{1, 0},{0, -1},{0, 1}};
        for (int i = 0; i < directions.length; i++) {
            int x = hero.getX();
            int y = hero.getY();
            for (int j = 0; j < size; j++) {
                x += directions[i][0];
                y += directions[i][1];
                if(x >= 0 && x < size && y >= 0 && y < size) {
                    if (grid[x][y] instanceof Wall) {
                        break;
                    } else if (grid[x][y] instanceof Hero || grid[x][y] instanceof Barrel) {
                        locations.add(AdvancedBotStrategy.moveDirections[i]);
                    }
                }
            }
        }
       if (!locations.isEmpty()) {
            Collections.shuffle(locations);
            return locations.get(0);
        }
        return "none";
    }
    
}
