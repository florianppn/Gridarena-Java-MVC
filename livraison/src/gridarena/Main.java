package gridarena;

import gridarena.controller.GameController;
import gridarena.controller.ia.*;
import gridarena.model.fillgrid.*;

import java.io.IOException;
import java.util.Properties;

/**
 * Représente la classe principale qui lance le jeu.
 *
 * @author Florian Pépin, Tom David et Emilien Huron.
 * @version 1.0
 */
public class Main {
    
    private static boolean SHOW_OVERVIEW;
    private static int GUI_PLAYERS, CLI_PLAYERS, BOT_PLAYERS, WALLS, MEDICAL_KITS, AMMO_KITS, BARRELS, SIZE_GRID;
    //Stratégies : new PatternFillStrategy() ou new RandomFillStrategy() ou new ModuloFillStrategy()
    private static FillStrategy FILL_STRATEGY = new PatternFillStrategy();
    //Stratégies : AdvancedBotStrategy() ou NoobyBotStrategy()
    private static BotStrategy BOT_STRATEGY = new AdvancedBotStrategy();
    
    static {
        try {
            Properties prop = new Properties();
            prop.load(GameController.class.getResourceAsStream("/resources/config.properties"));
            SHOW_OVERVIEW = Boolean.parseBoolean(prop.getProperty("SHOW_OVERVIEW", "false"));
            GUI_PLAYERS = Integer.parseInt(prop.getProperty("GUI_PLAYERS"));
            CLI_PLAYERS = Integer.parseInt(prop.getProperty("CLI_PLAYERS"));
            BOT_PLAYERS = Integer.parseInt(prop.getProperty("BOT_PLAYERS"));
            SIZE_GRID = Integer.parseInt(prop.getProperty("SIZE_GRID"));
            WALLS = Integer.parseInt(prop.getProperty("WALLS"));
            MEDICAL_KITS = Integer.parseInt(prop.getProperty("MEDICAL_KITS"));
            AMMO_KITS = Integer.parseInt(prop.getProperty("AMMO_KITS"));
            BARRELS = Integer.parseInt(prop.getProperty("BARRELS"));
        } catch (IOException ex) {
            throw new Error("Erreur lors du chargement du fichier de configuration : " + ex.getMessage());
        }
    }
    
    /**
     * Lancer le jeu.
     * 
     * @param args
     */
    public static void main(String[] args) {
        if ((GUI_PLAYERS+CLI_PLAYERS+BOT_PLAYERS)*2 > SIZE_GRID || WALLS > SIZE_GRID || MEDICAL_KITS > SIZE_GRID || AMMO_KITS > SIZE_GRID || BARRELS > SIZE_GRID) {
            throw new Error("Erreur : veuillez respecter les règles isncritent dans le dossier config.properties ! ");
        }
        GameController game = new GameController(SHOW_OVERVIEW, GUI_PLAYERS, CLI_PLAYERS, BOT_PLAYERS, SIZE_GRID, WALLS, MEDICAL_KITS, AMMO_KITS, BARRELS, FILL_STRATEGY);
        game.demarrer(BOT_STRATEGY);
    }
    
}
