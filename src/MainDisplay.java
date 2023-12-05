import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class MainDisplay extends JFrame {

    private MainMenu mainMenu;
    private NewGame newGame;
    private GamePlay gamePlay;

    private Settings settings;
    private GameMenu gameMenu;
    private JLayeredPane layeredPane;
    private JPanel mainPanel;
    private JPanel smallPanel;
    private Map<Component, Rectangle> originalBounds = new HashMap<>();

    public MainDisplay() {
        mainMenu = new MainMenu(this);
        newGame = new NewGame(this);
        gamePlay = new GamePlay(this);
        settings = new Settings(this);
        gameMenu = new GameMenu(this);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setOpaque(true);
        mainPanel.setBounds(0, 0, 1980, 1080); // Set the bounds to cover the entire area


        mainMenu.setBounds(0, 0, 1920, 1080);
        newGame.setBounds(0, 0, 1920, 1080);
        gamePlay.setBounds(0, 0, 1920, 1080);
        settings.setBounds(0, 0, 1920, 1080);
        gameMenu.setBounds(0,0,1920,1080);

        mainPanel.setVisible(true);
        mainMenu.setVisible(false);
        newGame.setVisible(false);
        gamePlay.setVisible(false);
        settings.setVisible(false);
        gameMenu.setVisible(false);

        layeredPane.add(mainPanel, Integer.valueOf(1));
        layeredPane.add(mainMenu, Integer.valueOf(2));
        layeredPane.add(newGame, Integer.valueOf(3));
        layeredPane.add(gamePlay, Integer.valueOf(4));
        layeredPane.add(settings, Integer.valueOf(3));
        layeredPane.add(gameMenu, Integer.valueOf(5));

        layeredPane.setOpaque(true);

        // Initially, set the visibility of panels
        mainPanel.setVisible(true);
        mainMenu.setVisible(false);
        newGame.setVisible(false);
        gamePlay.setVisible(false);
        settings.setVisible(false);
        gameMenu.setVisible(false);

        this.add(layeredPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(new Dimension(1920, 1080));
        this.setLocationRelativeTo(null);



        showMainMenu();
        this.setVisible(true);
        storeOriginalBounds();
    }

    /**
     * displays the MainMenu panel to the MainDisplay
     */
    public void showMainMenu(){
        int x = (this.getWidth() - newGame.getPreferredSize().width) / 2;
        int y = (this.getHeight() - newGame.getPreferredSize().height) / 2;
        mainMenu.setBounds(x, y, mainMenu.getPreferredSize().width, mainMenu.getPreferredSize().height);

        layeredPane.moveToFront(mainMenu);
        mainPanel.setVisible(true);
        mainMenu.setVisible(true);
        newGame.setVisible(false);
        gamePlay.setVisible(false);
        settings.setVisible(false);
        this.repaint();
    }
    /**
     * displays the NewGame panel to the MainDisplay
     */
    public  void showNewGame(){

        int x = (this.getWidth() - newGame.getPreferredSize().width) / 2;
        int y = (this.getHeight() - newGame.getPreferredSize().height) / 2;
        newGame.setBounds(x, y, newGame.getPreferredSize().width, newGame.getPreferredSize().height);

        // Assuming you are using layeredPane to manage visibility
        layeredPane.moveToFront(newGame);
        newGame.setVisible(true);
        mainMenu.setVisible(false);
        mainPanel.setVisible(true);
        settings.setVisible(false);

    }
    /**
     * displays the GamePlay panel to the MainDisplay
     */
    public void showGamePlay(){

        this.gamePlay.setVisible(true);
        gamePlay.initialize();
        newGame.setVisible(false);
        mainMenu.setVisible(false);
        settings.setVisible(false);
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
        mainMenu.setVisible(false);
        mainPanel.setVisible(true);

    }
    public void showGameMenu(){


        gameMenu.setVisible(true);
        layeredPane.moveToFront(gameMenu);
        layeredPane.revalidate();
        layeredPane.repaint();

    }




    public void updateLayout(int newWidth, int newHeight) {
        // Assume originalWidth and originalHeight are the original dimensions of MainDisplay
        int originalWidth = 1980; // Replace with actual original width
        int originalHeight = 1080; // Replace with actual original height

        // Calculate scale factors
        double widthScale = (double) newWidth / originalWidth;
        double heightScale = (double) newHeight / originalHeight;

        // Update the bounds of each panel in layeredPane
        for (Component component : layeredPane.getComponents()) {
            // Get the current bounds of the component
            Rectangle bounds = component.getBounds();

            // Scale the bounds according to the new size
            int scaledX = (int) (bounds.x * widthScale);
            int scaledY = (int) (bounds.y * heightScale);
            int scaledWidth = (int) (bounds.width * widthScale);
            int scaledHeight = (int) (bounds.height * heightScale);

            // Set the new bounds to the component
            component.setBounds(scaledX, scaledY, scaledWidth, scaledHeight);
        }

        // Update the size of the main panel
        this.setPreferredSize(new Dimension(newWidth, newHeight));

        // Revalidate and repaint to apply changes
        this.revalidate();
        this.repaint();
    }
    private void storeOriginalBounds() {
        for (Component component : layeredPane.getComponents()) {
            originalBounds.put(component, new Rectangle(component.getBounds()));
        }
    }



}