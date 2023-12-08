import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Iacopo Nohea Lenzi
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

    public static void readGameState(String saveName) {
        File file = new File(SAVE_PATH + saveName);
        if (file.exists()) {

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                GameState loadedGameState = (GameState) inputStream.readObject();
                setInstance(loadedGameState);

                Player loadedPlayer = (Player) inputStream.readObject();
                Player.replacePlayerInstance(loadedPlayer);

                Inventory loadedInventory = (Inventory) inputStream.readObject();
                Inventory.setInstance(loadedInventory);


                java.lang.System.out.println("Loaded Player Name: " + Player.getPlayerName());

                java.lang.System.out.println("Loaded Game Level: " + getInstance().currentLevel);

                java.lang.System.out.println("Player data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                java.lang.System.out.println("Error loading player data: " + e.getMessage());
            }
        }
    }

    public static void saveGame(String saveName) {
        writeGameState(saveName);

    }

    private static void writeGameState(String saveName) {
        File saveFile = new File(SAVE_PATH);
        saveFile.mkdirs(); //Ensures SAVE_PATH Directory exists
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_PATH + saveName))) {
            outputStream.writeObject(getInstance()); // Saves the Game state
            Player.writeToOutputStream(outputStream);
            Inventory.writeToOutputStream(outputStream); //Saves the player Inventory



            java.lang.System.out.println("Player data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            java.lang.System.out.println("Error saving player data: " + e.getMessage());
        }
    }
//    public void update() {
//        if (gamePlay != null) {
//            // Do something with gamePlay
//            gamePlay.updateUI(); // Example method call
//        }
//    }

    //    public void setGamePlay(GamePlay gamePlay) {
//        this.gamePlay = gamePlay;
//    }
    public void nextLevel() {
        getInstance().currentLevel += 1;
    }

    public int currentLevel() {
        return getInstance().currentLevel;
    }

    private static void setInstance(GameState loadedGameState) {
        instance = loadedGameState;
    }
    //public void addInventory() {

}

//        //Testing player data saving
//        Player.setPlayerName("Testnm");
//        java.lang.System.out.println(Player.getHealth());
//        Player.setHealth(5);
//        GameState.saveGame("test");
//        Player.setHealth(10);
//        GameState.readGameState("test");
//        java.lang.System.out.println(Player.getHealth());
//
//
//        //Testing Inventory data saving
//        Inventory inventory = Inventory.getInstance();
//        Weapon sword = new Weapon("Exaclibur", 9, 89, new Attack(new Enemy("Karl")));
//        inventory.addCollectable(sword); // Add an item to the inventory
//        inventory.addCurrency(100); // Add currency
//        GameState.saveGame("test");
//        inventory.removeCollectable(sword); // Manipulate the inventory data
//        inventory.addCurrency(50);
//        GameState.readGameState("test");
//
//        //Testing PlayerSprite data saving
//        // Initialize the PlayerSprite instance and set its starting position
//        Player.setStarting(50, 50); // Set the starting position
//
////        // Move the player sprite
////        Player.move('w');
////        Player.move('d');
//
//        int loadedPosX = Player.getPlayerPosX();
//        int loadedPosY = Player.getPlayerPosY();
//        java.lang.System.out.println(loadedPosX);
//        java.lang.System.out.println(loadedPosY);
//        // Save the game state
//        GameState.saveGame("test");
//
//        // Change the position of the player sprite after saving the game
//        Player.setStarting(100, 100);
//
//        // Load the game state and check if the player sprite position is restored
//        GameState.readGameState("test");
//
//        // Get the player sprite position after loading the game
//        java.lang.System.out.println(loadedPosX);
//        java.lang.System.out.println(loadedPosY);
//        // Check if the position is restored
//        if (loadedPosX == 55 && loadedPosY == 45) {
//            java.lang.System.out.println("PlayerSprite data successfully saved and loaded.");
//        } else {
//            java.lang.System.out.println("PlayerSprite data not loaded correctly.");
//        }
//    public static void main(String[] args){
//        //Testing player data saving
//        PlayerSprite.setPlayerName("Testnm");
//        java.lang.System.out.println(PlayerSprite.getHealth());
//        PlayerSprite.setHealth(5);
//        GameState.saveGame("test");
//        PlayerSprite.setHealth(10);
//        GameState.loadGame("test");
//        java.lang.System.out.println(PlayerSprite.getHealth());
//
//
//        //Testing Inventory data saving
//        Inventory inventory = Inventory.getInstance();
//        Weapon sword = new Weapon("Exaclibur", 9, 89, new Attack(new Enemy("Karl")));
//        inventory.addCollectable(sword); // Add an item to the inventory
//        inventory.addCurrency(100); // Add currency
//        GameState.saveGame("test");
//        inventory.removeCollectable(sword); // Manipulate the inventory data
//        inventory.addCurrency(50);
//        GameState.loadGame("test");
//
//        //Testing PlayerSprite data saving
//        // Initialize the PlayerSprite instance and set its starting position
//        PlayerSprite.setStarting(50, 50); // Set the starting position
//
//        // Move the player sprite
//        PlayerSprite.move('w');
//        PlayerSprite.move('d');
//
//        int loadedPosX = PlayerSprite.getPlayerPosX();
//        int loadedPosY = PlayerSprite.getPlayerPosY();
//        java.lang.System.out.println(loadedPosX);
//        java.lang.System.out.println(loadedPosY);
//        // Save the game state
//        GameState.saveGame("test");
//
//        // Change the position of the player sprite after saving the game
//        PlayerSprite.setStarting(100, 100);
//
//        // Load the game state and check if the player sprite position is restored
//        GameState.loadGame("test");
//
//        // Get the player sprite position after loading the game
//        java.lang.System.out.println(loadedPosX);
//        java.lang.System.out.println(loadedPosY);
//        // Check if the position is restored
//        if (loadedPosX == 55 && loadedPosY == 45) {
//            java.lang.System.out.println("PlayerSprite data successfully saved and loaded.");
//        } else {
//            java.lang.System.out.println("PlayerSprite data not loaded correctly.");
//        }
//    }
//
//    private static void testPlayerDataSaving() {
//        // Test player data saving
//        Player.setPlayerName("Testnm");
//        assert 100 == Player.getHealth();
//        Player.setHealth(5);
//        GameState.saveGame("test");
//        Player.setHealth(10);
//        GameState.readGameState("test");
//        assert 5 == Player.getHealth();
//    }

//    private static void testInventoryDataSaving() {
//        // Test inventory data saving
//        Inventory inventory = Inventory.getInstance();
//        Weapon sword = new Weapon("Excalibur", 9, 89, new Attack(new Enemy("Karl")));
//        inventory.addCollectable(sword);
//        inventory.addCurrency(100);
//        GameState.saveGame("test");
//        inventory.removeCollectable(sword);
//        inventory.addCurrency(50);
//        GameState.readGameState("test");
//        assert 100 == inventory.getCurrency();
//        assert sword == inventory.getInventory()[0][0];
//    }

//    private static void testPlayerSpriteDataSaving() {
//        // Test PlayerSprite data saving
//        Player.getInstance().setStarting(50, 50);
//            Player.getInstance().move('w');
//            Player.getInstance().move('d');
//
//        int initialPosX = Player.getInstance().getPlayerPosX();
//        int initialPosY = Player.getInstance().getPlayerPosY();
//
//        GameState.saveGame("test");
//        Player.getInstance().setStarting(100, 100);
//        GameState.readGameState("test");
//
//        int loadedPosX = Player.getInstance().getPlayerPosX();
//        int loadedPosY = Player.getInstance().getPlayerPosY();
//
//        assert 55 == loadedPosX;
//        assert 45 == loadedPosY;
//        assert initialPosX == loadedPosX;
//        assert initialPosY == loadedPosY;
//    }

//    public static void main(String[] args){
//
//            testPlayerDataSaving();
//            testInventoryDataSaving();
//            testPlayerSpriteDataSaving();
//    }
//}



