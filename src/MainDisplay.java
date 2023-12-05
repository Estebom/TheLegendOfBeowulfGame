import javax.swing.*;
import java.awt.*;




public class MainDisplay extends JFrame {

    private MainMenu mainMenu;
    private NewGame newGame;
    private GamePlay gamePlay;

    private Settings settings;
    private GameMenu gameMenu;
    private JLayeredPane layeredPane;
    private JPanel mainPanel;
    private JPanel smallPanel;

    public MainDisplay() {
        mainMenu = new MainMenu(this);
        newGame = new NewGame(this);
        gamePlay = new GamePlay(this);
        settings = new Settings(this);
        gameMenu = new GameMenu(this);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));

        mainMenu.setBounds(0, 0, 1920, 1080);
        newGame.setBounds(0, 0, 1920, 1080);
        gamePlay.setBounds(0, 0, 1920, 1080);
        settings.setBounds(0, 0, 1920, 1080);

        layeredPane.add(mainMenu, Integer.valueOf(2));
        layeredPane.add(newGame, Integer.valueOf(3));
        layeredPane.add(gamePlay, Integer.valueOf(4));
        layeredPane.add(settings, Integer.valueOf(3));

        // Initially, set the visibility of panels
        mainMenu.setVisible(false);
        newGame.setVisible(false);
        gamePlay.setVisible(false);
        settings.setVisible(false);

        this.add(layeredPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(1920, 1080));


        showMainMenu();
        this.setVisible(true);
    }

    /**
     * displays the MainMenu panel to the MainDisplay
     */
    public void showMainMenu(){
        mainMenu.setVisible(true);
        newGame.setVisible(false);
        gamePlay.setVisible(false);
        settings.setVisible(false);    }
    /**
     * displays the NewGame panel to the MainDisplay
     */
    public  void showNewGame(){

        int x = (this.getWidth() - newGame.getPreferredSize().width) / 2;
        int y = (this.getHeight() - newGame.getPreferredSize().height) / 2;
        newGame.setBounds(x, y, newGame.getPreferredSize().width, newGame.getPreferredSize().height);

        // Assuming you are using layeredPane to manage visibility
        newGame.setVisible(true);
        newGame.setVisible(true);

    }
    /**
     * displays the GamePlay panel to the MainDisplay
     */
    public void showGamePlay(){

        this.gamePlay.setVisible(true);
        gamePlay.initialize();

    }
    /**
     * displays the Settings panel to the MainDisplay
     */
    public void showSettings(){

        int x = (this.getWidth() - settings.getPreferredSize().width) / 2;
        int y = (this.getHeight() - settings.getPreferredSize().height) / 2;
        settings.setBounds(x, y, settings.getPreferredSize().width, settings.getPreferredSize().height);

        // Assuming you are using layeredPane to manage visibility
        layeredPane.moveToFront(settings);
        settings.setVisible(true);
    }




    public void updateLayout() {
        int newWidth = this.getWidth();
        int newHeight = this.getHeight();

        // Assuming mainMenu and newGame are instances of your MainMenu and NewGame classes
        mainMenu.updateLayout(newWidth, newHeight);
        newGame.updateLayout(newWidth, newHeight);
        gamePlay.updateLayout(newWidth, newHeight);
        // Update the bounds of each panel in layeredPane
        for (Component component : layeredPane.getComponents()) {
            if (component instanceof JLabel) {
                component.setBounds(0, 0, newWidth, newHeight); // For the background label
            } else {
                // For other panels like NewGame
                component.setBounds(0, 0, newWidth, newHeight);
            }
        }

        // Repaint to apply changes
        this.repaint();
    }



}