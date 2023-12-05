
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author iacopolenzi
 */
public class GameState implements Serializable {
    private int currentLevel;
    private static final String SAVE_PATH = "saves/";
    public static final String DEFAULT_SAVE = "latestGameState";
    private static GameState instance = null;


    public static GameState getInstance() {

        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    private GameState() {
        this.currentLevel = 0;
    }

    public static void loadGame(String saveName) {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SAVE_PATH + saveName))) {
            instance = (GameState) inputStream.readObject(); // Load the player object
            PlayerSprite.replacePlayerInstance((PlayerSprite) inputStream.readObject());

            // Load the Inventory object
            Inventory.setInstance((Inventory) inputStream.readObject()); // Set the loaded Inventory
            
            java.lang.System.out.println("Loaded Player Name: " + PlayerSprite.getPlayerName());

            java.lang.System.out.println("Loaded Game Level: " + getInstance().currentLevel);

            java.lang.System.out.println("Player data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            java.lang.System.out.println("Error loading player data: " + e.getMessage());
        }
    }

    public static void saveGame(String saveName) {
        writeGameState(saveName);
        writeGameState(DEFAULT_SAVE);
    }

    private static void writeGameState(String saveName) {
        File saveFile = new File(SAVE_PATH);
        saveFile.mkdirs(); //Ensures SAVE_PATH Directory exists
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_PATH + saveName))) {
            outputStream.writeObject(getInstance()); // Saves the Game state
            outputStream.writeObject(PlayerSprite.getInstance()); // Saves the entire player object
            outputStream.writeObject(Inventory.getInstance()); //Saves the player Inventory
            java.lang.System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            java.lang.System.out.println("Error saving player data: " + e.getMessage());
        }
    }

    public void nextLevel() {
        getInstance().currentLevel += 1;
    }

    public int currentLevel() {
        return getInstance().currentLevel;
    }

    public void addInventory() {



        //Testing player data saving
        PlayerSprite.setPlayerName("Testnm");
        java.lang.System.out.println(PlayerSprite.getHealth());
        PlayerSprite.setHealth(5);
        GameState.saveGame("test");
        PlayerSprite.setHealth(10);
        GameState.loadGame("test");
        java.lang.System.out.println(PlayerSprite.getHealth());


        //Testing Inventory data saving
        Inventory inventory = Inventory.getInstance();
        Weapon sword = new Weapon("Exaclibur", 9, 89, new Attack(new Enemy("Karl")));
        inventory.addCollectable(sword); // Add an item to the inventory
        inventory.addCurrency(100); // Add currency
        GameState.saveGame("test");
        inventory.removeCollectable(sword); // Manipulate the inventory data
        inventory.addCurrency(50);
        GameState.loadGame("test");

        //Testing PlayerSprite data saving
        // Initialize the PlayerSprite instance and set its starting position
        PlayerSprite.setStarting(50, 50); // Set the starting position

        // Move the player sprite
        PlayerSprite.move('w');
        PlayerSprite.move('d');

        int loadedPosX = PlayerSprite.getPlayerPosX();
        int loadedPosY = PlayerSprite.getPlayerPosY();
        java.lang.System.out.println(loadedPosX);
        java.lang.System.out.println(loadedPosY);
        // Save the game state
        GameState.saveGame("test");

        // Change the position of the player sprite after saving the game
        PlayerSprite.setStarting(100, 100);

        // Load the game state and check if the player sprite position is restored
        GameState.loadGame("test");

        // Get the player sprite position after loading the game
        java.lang.System.out.println(loadedPosX);
        java.lang.System.out.println(loadedPosY);
        // Check if the position is restored
        if (loadedPosX == 55 && loadedPosY == 45) {
            java.lang.System.out.println("PlayerSprite data successfully saved and loaded.");
        } else {
            java.lang.System.out.println("PlayerSprite data not loaded correctly.");
        }
    }
}



