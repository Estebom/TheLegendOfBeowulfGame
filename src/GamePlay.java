import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * handles all game logic
 * @author Esteban Rodriguez
 */
public class GamePlay extends JPanel {

    private MainDisplay mainDisplay ;

    private KeyPad keyPad;

    private JLayeredPane layeredPane;

    private GameMenu gameMenu;

    private ArrayList<Enemy> enemies =  new ArrayList<>();
    private boolean hittable = false;

    private Enemy currentTarget;



    public GamePlay(MainDisplay mainDisplay){
        this.mainDisplay = mainDisplay;
        this.keyPad = KeyPad.getInstance(this);
        KeyPad.getInstance(this).setupEscapeKeyBinding(this,this::showGameMenu);
        gameMenu = new GameMenu(this);
        gameMenu.setBounds(800,100,200,400);

        gameMenu.setBackground(Color.WHITE);
        gameMenu.setOpaque(true);
        gameMenu.setVisible(false);

        this.setPreferredSize(new Dimension(1080,1920));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.addKeyListener(keyPad);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 1920));
        layeredPane.setLayout(null); // Set layout to null
        layeredPane.setBackground(Color.BLACK);
        playerImages.setBounds(750, 750, 100, 100); // Set initial position and size
        layeredPane.add(playerImages, Integer.valueOf(1));
        layeredPane.add(gameMenu,Integer.valueOf(2));
        layeredPane.setOpaque(false);

        this.add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * sets all keyboard inputs to this panel
     */

    public void initialize(){;
        PlayerImages playerImages = PlayerImages.getInstance();
        this.requestFocusInWindow();
        PlayerSprite.setStarting(this.getWidth()/2, this.getHeight()/2);

        playerImages.setBounds(PlayerSprite.getPlayerPosX(), PlayerSprite.getPlayerPosY(), 100, 100); // Set initial position and size
        Enemy bobby = new Enemy("bobby");
        enemies.add(bobby);

    }

    public void showGameMenu() {

            gameMenu.setVisible(true);
            this.revalidate();
            this.repaint();

    }
//    public void updateLayout(int width, int height) {
//        PlayerImages playerImages = PlayerImages.getInstance();
//        this.setPreferredSize(new Dimension(width, height));
//
//        // Update the bounds of the playerImages and gameMenu
//        playerImages.setBounds(playerImages.getX(), playerImages.getY(), 100, 100); // Size can be dynamic
//        gameMenu.setBounds(500, 500, 300, 200); // Adjust as needed
//
//        // Update the layeredPane size
//        layeredPane.setPreferredSize(new Dimension(width, height));
//        int newPlayerPosX = (playerImages.getX() * width) / 1080; // 1080 is the original width
//        int newPlayerPosY = (playerImages.getY() * height) / 1920; // 1920 is the original height
//
//        playerImages.setBounds(newPlayerPosX, newPlayerPosY, 100, 100);
//
//        // Repaint and revalidate the panel
//    }
    public void hideMenu(){
        gameMenu.setVisible(false);
        this.revalidate();
        this.repaint();
    }

    public Enemy getEnemy(int i){
        return enemies.get(i);
    }

    public boolean hittable(){
        char[] directions = {'w','s','a','d'};
        for(int i = 0; i < directions.length; i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if ((Math.abs(PlayerSprite.getPlayerPosX() - enemies.get(j).getPosx() )<=10)
                    ||(Math.abs(PlayerSprite.getPlayerPosY() - enemies.get(j).getPosy() )<=10)){

                    this.hittable = true;
                    currentTarget = enemies.get(j);
                    return this.hittable;
                }

            }
        }
            this.hittable = false;
        return this.hittable;
    }
    public Enemy getCurrentTarget(){
        return this.currentTarget;
    }

}