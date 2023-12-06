import javax.swing.*;
import java.awt.*;




public class MainDisplay extends JFrame {
    private MainMenu mainMenu;
    private NewGame newGame;

    private Settings settings;
    private GameMenu gameMenu;
    private static MainDisplay instance;

    public MainDisplay() {

        mainMenu = new MainMenu(this);
        newGame = new NewGame(this);
        settings = new Settings(this);

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

        showMainMenu();
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
    public void showMainMenu(){
        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "MainMenu");
    }
    /**
     * displays the NewGame panel to the MainDisplay
     */
    public  void showNewGame(){

        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(),"NewGame");
    }
    /**
     * displays the GamePlay panel to the MainDisplay
     */
    public void showGamePlay(){

        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "START");
        GamePlay.getInstance().initialize(false);

    }
    public void loadedGamePlay(){
        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "LOAD_GAME");
        GamePlay.getInstance().initialize(true);
    }
    /**
     * displays the Settings panel to the MainDisplay
     */
    public void showSettings(){

        ((CardLayout)this.getContentPane().getLayout()).show(this.getContentPane(), "SETTINGS");
    }

    public void showGameMenu(){



    }






}