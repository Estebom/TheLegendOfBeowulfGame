import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * handles all game logic
 * @author Esteban Rodriguez
 *
 */
public  class GamePlay extends JPanel implements Serializable {
    private JPanel interactionPanel;
    transient private JLayeredPane layeredPane;
    transient private GameMenu gameMenu;
    private ArrayList<NPC> npcs = new ArrayList<>();
    private static ArrayList<Enemy> enemies =  new ArrayList<>();
    private ArrayList<Chest> chests = new ArrayList<>();
    private boolean hittable = false;
    private Enemy currentTarget;
    private static GamePlay instance;
    private Attack attack;
    private JPanel gameUI;
    private JProgressBar healthBar;
    public static GamePlay getInstance(){
        if(instance == null){
            instance = new GamePlay();
            // Player Health and Damage
        }
        return instance;
    }
    public GamePlay(){
        this.setPreferredSize(new Dimension(1080,1920));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        JLabel backmap = new JLabel();
        ImageIcon mapIcon = new ImageIcon(loadImage("resources/images/map.png"));
        backmap.setIcon(mapIcon);
        backmap.setBounds(0,0,1980,1080);
        backmap.setOpaque(false);
        NPC vendor1 = new NPC("seller", 'd');
        npcs.add(vendor1);
        npcs.get(0).setPostion(100,300);
        npcs.get(0).setBounds(npcs.get(0).getNpcPosX(),npcs.get(0).getNpcPosY(),100,100);
        interactionPanel = npcs.get(0).showInteractionPanel();
        interactionPanel.setBounds(npcs.get(0).getX() + - 75, npcs.get(0).getY() + - 200, interactionPanel.getWidth(), interactionPanel.getHeight());
        interactionPanel.setVisible(false);
        Enemy mimic = new Enemy("mimic");
        mimic.setPosition(600,600);
        mimic.setBounds(mimic.getPosx(), mimic.getPosy(),100,100);

       Enemy mimic2 = new Enemy("mimic2");
       mimic2.setPosition(800,800);
       mimic2.setBounds(mimic.getPosx(), mimic.getPosy(),100,100);

       Enemy mimic3 = new Enemy("mimic3");
        mimic3.setPosition(300,300);
      mimic3.setBounds(mimic.getPosx(), mimic.getPosy(),100,100);

       enemies.add(mimic);
       enemies.add(mimic2);
       enemies.add(mimic3);

        gameMenu = new GameMenu();
        gameMenu.setBounds(800,100,200,400);
        gameMenu.setBackground(Color.WHITE);
        gameMenu.setOpaque(true);
        gameMenu.setVisible(false);


        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1080, 1920));
        layeredPane.setLayout(null); // Set layout to null
        layeredPane.setBackground(Color.BLACK);
        layeredPane.add(backmap, Integer.valueOf(0));
        layeredPane.add(npcs.get(0).showInteractionPanel(),Integer.valueOf(1));
        layeredPane.add(npcs.get(0), Integer.valueOf(1));
        for (Enemy enemy : enemies) {
            layeredPane.add(enemy, Integer.valueOf(1));
        }
        PlayerImages.getInstance().setBounds(750, 750, 100, 100); // Set initial position and size
        layeredPane.add(PlayerImages.getInstance(), Integer.valueOf(1));
        Inventory.getInstance().setBounds(500,500,300,300);
        layeredPane.add(Inventory.getInstance(),Integer.valueOf(2));
        layeredPane.add(gameMenu,Integer.valueOf(3));
        layeredPane.setOpaque(true);
        setupKeyBindings();
        this.add(layeredPane, BorderLayout.CENTER);

        attack = new Attack(enemies.get(0));
        // TODO: 12/8/2023 THIS IS THE ENEMY LOGIC BREAKING BUG
//        ShortSword sword = new ShortSword("speedy", 1000.0, 200, attack);
//        Inventory.addCollectable(sword);
//        ShortSword sword = new ShortSword("speedy", 1000.0, 200, attack);
//        Inventory.addCollectable(sword);
//
        Chest chest1 = new Chest("chestone", 'd');

        ShortSword sword1= new ShortSword("speed", 10, 200, attack);
        chest1.addCollectable(sword1);
        Item bandage = new Item("bandage", "heals players",10);
        chest1.addCollectable(bandage);


        ShortSword sword2= new ShortSword("lightning", 20, 300, attack);
        chest1.addCollectable(sword2);
        ShortSword sword3= new ShortSword("onyx", 30, 400, attack);
        chest1.addCollectable(sword3);
        Item potion = new Item("potion", "applies mystical effect to the user",20);
        chest1.addCollectable(potion);

        Item rope = new Item("rope", "gets player out of tricky situtation",40);
        chest1.addCollectable(rope);
        chest1.setBounds(200,200,100,100);
        chest1.setChestPosX(200);
        chest1.setChestPosY(200);
        chest1.setBounds(chest1.getChestPosX(),chest1.getChestPosY(),100,100);

        Chest chest2 = new Chest("chestone", 'd');

        chest2.addCollectable(sword1);
        chest2.addCollectable(bandage);


        chest2.addCollectable(sword2);
        chest2.addCollectable(sword3);
        chest2.addCollectable(potion);

        chest2.addCollectable(rope);
        chest2.setBounds(200,500,100,100);
        chest2.setChestPosX(200);
        chest2.setChestPosY(500);
        chest2.setBounds(chest2.getChestPosX(),chest2.getChestPosY(),100,100);

        Chest chest3 = new Chest("chestone", 'd');

        chest3.addCollectable(sword1);
        chest3.addCollectable(bandage);


        chest3.addCollectable(sword2);
        chest3.addCollectable(sword3);
        chest3.addCollectable(potion);

        chest3.addCollectable(rope);
        chest3.setBounds(500,200,100,100);
        chest3.setChestPosX(500);
        chest3.setChestPosY(200);
        chest3.setBounds(chest3.getChestPosX(),chest3.getChestPosY(),100,100);

        chests.add(chest1);
        chests.add(chest2);
        chests.add(chest3);

        for (Chest chest : chests) {
            layeredPane.add(chest, Integer.valueOf(1));
        }




        healthBar = new JProgressBar();
        healthBar.setValue((int)Player.getHealth());
        healthBar.setBounds(0,0,420,50);
        healthBar.setStringPainted(true);


        JPanel playerinfo = new JPanel();
        playerinfo.add(healthBar);
        playerinfo.setSize(600,200);
        playerinfo.setLayout(null);
        playerinfo.setVisible(true);


        gameUI = new JPanel();
        gameUI.setBounds(0,0,400,50);
        gameUI.setOpaque(false);
        gameUI.setLayout(new BorderLayout());
        gameUI.add(playerinfo, BorderLayout.CENTER);

        layeredPane.add(gameUI,Integer.valueOf(2));



        setupKeyBindings();
        this.add(layeredPane, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    public static void fill(){
        getInstance();
        if (instance.healthBar != null) { // Check if healthBar is not null
            int healthValue = (int) Math.max(Player.getHealth(), 0); // Ensure health is not negative
            instance.healthBar.setValue(healthValue);
            instance.healthBar.setString("Health: " + healthValue);
        }
    }

    /**
     * sets all keyboard inputs to this panel
     * @param b this boolean sets the action for the gameplay to be initialized
     */
    public static void initialize(boolean b){
        if(false) {
            getInstance();
            instance.requestFocusInWindow();
            Player.setStarting(instance.getWidth() / 2, instance.getHeight() / 2);
            PlayerImages.getInstance().setBounds(Player.getPlayerPosX(), Player.getPlayerPosY(), 100, 100); // Set initial position and size
        }
        else{
            getInstance();
            instance.requestFocusInWindow();
            Player.setStarting(Player.getPlayerPosX(),Player.getPlayerPosY());
            PlayerImages.getInstance().setBounds(Player.getPlayerPosX(), Player.getPlayerPosY(), 100, 100); // Set initial position and size
        }
    }
    /**
     * starts all enemy gameplay
     * @author Estban Rodriguez
     */
    public static void startGame() {
        getInstance();
        for (Enemy enemy : enemies) {
            enemy.startBehavior();
        }
    }
    /**
     * pauses enemy behavior when returning to menu, in inventory, or in the gamemenu
     */
    public static void pauseGame(){
        getInstance();
        for (Enemy enemy: enemies){
            enemy.stopBehavior();
        }
    }

    public static ArrayList<Enemy> accessEnemies() {
        getInstance();
        return enemies;
    }

    private void setupKeyBindings() {
        // Ensure the current instance is not null
        if (instance != null) {
            KeyPad.setupEscapeKeyBinding(instance, GamePlay::showGameMenu);
            KeyPad.setupMKeyBinding(instance, GamePlay::showInventory);
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
    public static void showInventory(){
        getInstance();
        Inventory.getInstance().setVisible(true);
        instance.revalidate();
        instance.repaint();
    }
    public static void showInteraction(){
        getInstance();
        instance.interactionPanel.setVisible(true);
        instance.revalidate();
        instance.repaint();
    }
    public  static void hideMenu(){
        getInstance();
        instance.gameMenu.setVisible(false);
        instance.revalidate();
        instance.repaint();
    }
    public static Enemy getEnemy(int i){
        getInstance();
        return instance.enemies.get(i);
    }
    public static boolean hittable() {
        getInstance();
        char playerDirection = Player.getCurrentDirection();
        for (Enemy enemy : enemies) {
            int xDistance = Player.getPlayerPosX() - enemy.getPosx();
            int yDistance = Player.getPlayerPosY() - enemy.getPosy();
            switch (playerDirection) {
                case 'w':
                    if (yDistance > 0 && Math.abs(yDistance) <= 35 && Math.abs(xDistance) <= 35) {
                        setHittableEnemy(enemy);
                        return true;
                    }
                    break;
                case 's':
                    if (yDistance < 0 && Math.abs(yDistance) <= 35 && Math.abs(xDistance) <= 35) {
                        setHittableEnemy(enemy);
                        return true;
                    }
                    break;
                case 'a':
                    if (xDistance > 0 && Math.abs(xDistance) <= 35 && Math.abs(yDistance) <= 35) {
                        setHittableEnemy(enemy);
                        return true;
                    }
                    break;
                case 'd':
                    if (xDistance < 0 && Math.abs(xDistance) <= 35 && Math.abs(yDistance) <= 35) {
                        setHittableEnemy(enemy);
                        return true;
                    }
                    break;
            }
        }
        instance.hittable = false;
        return false;
    }
    public static Enemy getCurrentTarget(){
        getInstance();
        return instance.currentTarget;
    }
    private static void setHittableEnemy(Enemy enemy) {
        getInstance();
        instance.hittable = true;
        instance.currentTarget = enemy;
        instance.attack.setCurrentTarget(getCurrentTarget());
    }
    public static NPC accessNPC(){
        getInstance();
        return instance.npcs.get(0);
    }
    public static ArrayList<Chest> accessChests(){
        getInstance();
        return instance.chests;
    }

    private Image loadImage(String path) {
        Image image = null;
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                System.err.println("Could not load image at path: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

}