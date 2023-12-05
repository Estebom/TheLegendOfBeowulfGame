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

    private PlayerSprite playerSprite;
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
        playerSprite = PlayerSprite.getInstance();
        playerSprite.setBounds(750, 750, 100, 100); // Set initial position and size
        layeredPane.add(playerSprite, Integer.valueOf(1));
        layeredPane.add(gameMenu,Integer.valueOf(2));
        layeredPane.setOpaque(false);


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

    public void showGameMenu() {

            gameMenu.setVisible(true);
            this.revalidate();
            this.repaint();

    }
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
                if ((Math.abs(playerSprite.getPlayerPosX() - enemies.get(j).getPosx() )<=10)
                    ||(Math.abs(playerSprite.getPlayerPosY() - enemies.get(j).getPosy() )<=10)){

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