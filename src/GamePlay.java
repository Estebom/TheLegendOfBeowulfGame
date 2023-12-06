import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * handles all game logic
 * @author Esteban Rodriguez
 */
public  class GamePlay extends JPanel {





    transient private JLayeredPane layeredPane;

    transient private GameMenu gameMenu;


    private static ArrayList<Enemy> enemies =  new ArrayList<>();
    private boolean hittable = false;

    private Enemy currentTarget;

    private static GamePlay instance;

    public static GamePlay getInstance(){

        if(instance == null){
            instance = new GamePlay();
            // Player Health and Damage

        }
        return instance;
    }

    public GamePlay(){


        gameMenu = new GameMenu();
        gameMenu.setBounds(800,100,200,400);

        gameMenu.setBackground(Color.WHITE);
        gameMenu.setOpaque(true);
        gameMenu.setVisible(false);


        this.setPreferredSize(new Dimension(1080,1920));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.requestFocusInWindow();


        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 1920));
        layeredPane.setLayout(null); // Set layout to null
        layeredPane.setBackground(Color.BLACK);
        PlayerImages.getInstance().setBounds(750, 750, 100, 100); // Set initial position and size
        layeredPane.add(PlayerImages.getInstance(), Integer.valueOf(1));
        layeredPane.add(gameMenu,Integer.valueOf(2));
        layeredPane.setOpaque(false);

        setupKeyBindings();
        this.add(layeredPane, BorderLayout.CENTER);
    }

    /**
     * sets all keyboard inputs to this panel
     */

    public static void initialize(boolean b){
        if(false) {
            getInstance();
            instance.requestFocusInWindow();
            Player.setStarting(instance.getWidth() / 2, instance.getHeight() / 2);

            PlayerImages.getInstance().setBounds(Player.getPlayerPosX(), Player.getPlayerPosY(), 100, 100); // Set initial position and size
            Enemy bobby = new Enemy("bobby");
            instance.enemies.add(bobby);
        }
        else{
            getInstance();
            instance.requestFocusInWindow();

            Player.setStarting(Player.getPlayerPosX(),Player.getPlayerPosY());
            PlayerImages.getInstance().setBounds(Player.getPlayerPosX(), Player.getPlayerPosY(), 100, 100); // Set initial position and size
            Enemy bobby = new Enemy("bobby");
            instance.enemies.add(bobby);
        }
    }
    private void setupKeyBindings() {
        // Ensure the current instance is not null
        if (instance != null) {
            KeyPad.setupEscapeKeyBinding(instance, GamePlay::showGameMenu);
        }
    }
    public static void initializeKeyPad() {
        getInstance();
        instance.addKeyListener(KeyPad.getInstance(instance));
        instance.requestFocusInWindow();
    }


    public static void showGameMenu() {
            getInstance();
            instance.gameMenu.setVisible(true);
            instance.revalidate();
            instance.repaint();

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
    public  static void hideMenu(){
        getInstance();
        instance.gameMenu.setVisible(false);
        instance.revalidate();
        instance.repaint();
    }

    public static Enemy getEnemy(int i){
        return instance.enemies.get(i);
    }

    public static boolean hittable(){
        getInstance();
        char[] directions = {'w','s','a','d'};
        for(int i = 0; i < directions.length; i++) {
            for (int j = 0; j < instance.enemies.size(); j++) {
                if ((Math.abs(Player.getPlayerPosX() - enemies.get(j).getPosx() )<=10)
                    ||(Math.abs(Player.getPlayerPosY() - enemies.get(j).getPosy() )<=10)){

                    instance.hittable = true;
                    instance.currentTarget = enemies.get(j);
                    return instance.hittable;
                }

            }
        }
        instance.hittable = false;
        return instance.hittable;
    }
    public static Enemy getCurrentTarget(){
        getInstance();

        return instance.currentTarget;
    }

}