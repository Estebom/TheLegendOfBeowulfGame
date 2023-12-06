import javax.swing.*;
import java.awt.*;




public class MainDisplay extends JFrame {
    private MainMenu mainMenu;
    private NewGame newGame;

    private Settings settings;
    private GameMenu gameMenu;
    private static MainDisplay instance;

    public MainDisplay() {

        mainMenu = new MainMenu();
        newGame = new NewGame();
        settings = new Settings();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Set size for 1080p resolution
        this.setSize(new Dimension(1920, 1080));
        this.setLayout(new CardLayout());


        //this.add(gameMenu, "GameMenu");
        this.add(mainMenu, "MainMenu");
        this.add(newGame, "NewGame");
        this.add(GamePlay.getInstance(), "START");
        this.add(settings, "SETTINGS");
        mainMenu.setVisible(true);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public static MainDisplay getInstance() {

        if (instance == null) {
            instance = new MainDisplay();
        }
        return instance;
    }



    /**
     * displays the MainMenu panel to the MainDisplay
     */
    public static void showMainMenu(){
        getInstance();
        ((CardLayout)instance.getContentPane().getLayout()).show(instance.getContentPane(), "MainMenu");
    }
    /**
     * displays the NewGame panel to the MainDisplay
     */
    public static void showNewGame(){
        getInstance();
        ((CardLayout)instance.getContentPane().getLayout()).show(instance.getContentPane(),"NewGame");
    }
    /**
     * displays the GamePlay panel to the MainDisplay
     */
    public static void showGamePlay(){
        getInstance();
        ((CardLayout)instance.getContentPane().getLayout()).show(instance.getContentPane(), "START");
        GamePlay.initialize(false);

    }
    public static void loadedGamePlay(){
        getInstance();
        ((CardLayout)instance.getContentPane().getLayout()).show(instance.getContentPane(), "START");
        GamePlay.initialize(true);
        GamePlay.initializeKeyPad();
    }
    /**
     * displays the Settings panel to the MainDisplay
     */
    public static void showSettings(){
        getInstance();

        ((CardLayout)instance.getContentPane().getLayout()).show(instance.getContentPane(), "SETTINGS");
    }








}