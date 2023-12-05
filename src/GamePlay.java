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

        this.setPreferredSize(new Dimension(1080,1920));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.addKeyListener(keyPad);



        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 1920));
        layeredPane.setLayout(null); // Set layout to null
        layeredPane.setBackground(Color.BLACK);
        playerSprite = PlayerSprite.getInstance();
        playerSprite.setBounds(750, 750, 100, 100); // Set initial position and size
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

        playerSprite.setBounds(playerSprite.getPlayerPosX(), playerSprite.getPlayerPosY(), 100, 100);
    }

    public void showGameMenu(){

        this.mainDisplay.showGameMenu();
        this.mainDisplay.revalidate();
        this.mainDisplay.repaint();

    }

}