
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


    
    public static GameState getInstance(){

        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }
    
    private GameState(){
        this.currentLevel = 0;
    }
    
    public static void loadGame(String saveName) {
        
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(SAVE_PATH + saveName))) {
            instance = (GameState) inputStream.readObject(); // Load the player object
            Player.replacePlayerInstance((Player) inputStream.readObject());
            
            
            java.lang.System.out.println("Loaded Player Name: " + Player.getInstance().getPlayerName());
            java.lang.System.out.println("Loaded Game Level: " + getInstance().currentLevel);

            java.lang.System.out.println("Player data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            java.lang.System.out.println("Error loading player data: " + e.getMessage());
        }
    }
        
    public static void saveGame(String saveName){
        writeGameState(saveName);
        writeGameState(DEFAULT_SAVE);
    }    
        
    private static void writeGameState(String saveName) {
        File saveFile = new File(SAVE_PATH);
        saveFile.mkdirs(); //Ensures SAVE_PATH Directory exists
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SAVE_PATH + saveName))) {
            outputStream.writeObject(getInstance()); // Saves the Game state
            outputStream.writeObject(Player.getInstance()); // Saves the entire player object
            outputStream.writeObject(Inventory.getInstance()); //Saves the player Inventory
            java.lang.System.out.println("Player data saved successfully.");
        } 

        catch (IOException e) {
            e.printStackTrace();
            java.lang.System.out.println("Error saving player data: " + e.getMessage());
        }
    }
    
    public void nextLevel(){
        getInstance().currentLevel+=1;
    }
    
    public int currentLevel(){
        return getInstance().currentLevel;
    }
    
    public void addInventory(){
        
        
}
    
    public static void main(String[] args){
        //Testing Level saving
        GameState.getInstance().nextLevel();
        GameState.getInstance().nextLevel();
        GameState.getInstance().nextLevel();
        GameState.saveGame("test");
        GameState.getInstance().nextLevel();
        GameState.getInstance().nextLevel();
        GameState.loadGame("test");
        java.lang.System.out.println(GameState.getInstance().currentLevel());


        //Testing player data saving
        Player.getInstance().setPlayerName("Testnm");
        java.lang.System.out.println(Player.getInstance().getHealth());
        Player.getInstance().setHealth(5);
        GameState.saveGame("test");
        Player.getInstance().setHealth(10);
        GameState.loadGame("test");
        java.lang.System.out.println(Player.getInstance().getHealth());


        //Testing Inventory data saving
        Inventory inventory = Inventory.getInstance();
        Weapon sword = new Weapon("Exaclibur", 9, 89);
        inventory.addCollectable(sword); // Add an item to the inventory
        inventory.addCurrency(100); // Add currency
        GameState.saveGame("test");
        inventory.removeCollectable(sword); // Manipulate the inventory data
        inventory.addCurrency(50);
        GameState.loadGame("test");
    }
}

