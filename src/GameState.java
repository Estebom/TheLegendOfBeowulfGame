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
