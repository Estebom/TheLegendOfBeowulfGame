import javax.swing.*;
import java.awt.*;

/**
 * handles all game logic
 * @author Esteban Rodriguez
 */
public class GamePlay extends JPanel {

    private MainDisplay mainDisplay ;

    private KeyPad keyPad;

    private PlayerSprite playerSprite;
    private JLayeredPane layeredPane;

    private GameMenu gameMenu;



    public GamePlay(MainDisplay mainDisplay){
            this.mainDisplay = mainDisplay;
            this.keyPad = KeyPad.getInstance(this);
            gameMenu = new GameMenu(mainDisplay);
            gameMenu.setBounds(500,500,300,200);
            gameMenu.setVisible(false);
            gameMenu.setOpaque(true);

            this.setPreferredSize(new Dimension(1080,1920));
            this.setLayout(new BorderLayout());
            this.setFocusable(true);
            this.addKeyListener(keyPad);



            layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(1080, 1920));
            layeredPane.setLayout(null); // Set layout to null
            layeredPane.setBackground(Color.BLACK);
            playerSprite = PlayerSprite.getInstance();
            layeredPane.add(playerSprite, Integer.valueOf(1));
            layeredPane.add(gameMenu,Integer.valueOf(2));
            layeredPane.setOpaque(true);


            this.add(layeredPane, BorderLayout.CENTER);





    }

    /**
     * sets all keyboard inputs to this panel
     */
    public void initialize(){
        this.requestFocusInWindow();
        playerSprite.setStarting(this.getWidth()/2, this.getHeight()/2);

        playerSprite.setBounds(playerSprite.getPlayerPosX(), playerSprite.getPlayerPosY(), 100, 100); // Set initial position and size

    }

    public void showGameMenu(){
        mainDisplay.showGameMenu();
    }


    public void updateLayout(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));

        // Update the bounds of the playerSprite and gameMenu
        playerSprite.setBounds(playerSprite.getX(), playerSprite.getY(), 100, 100); // Size can be dynamic
        gameMenu.setBounds(500, 500, 300, 200); // Adjust as needed

        // Update the layeredPane size
        layeredPane.setPreferredSize(new Dimension(width, height));
        int newPlayerPosX = (playerSprite.getX() * width) / 1080; // 1080 is the original width
        int newPlayerPosY = (playerSprite.getY() * height) / 1920; // 1920 is the original height

        playerSprite.setBounds(newPlayerPosX, newPlayerPosY, 100, 100);

        // Repaint and revalidate the panel
        this.revalidate();
        this.repaint();
    }

}
