import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * For the creation of a new game state, dispays new game interface
 * @author Esteban Rodriguez
 */
public class NewGame extends JPanel {

    JTextField name;
    JButton easyButton;
    JButton mediumButton;

    JButton hardButton;

    JButton startButton;

    JButton goBack;

    JPanel newGameFeatures;
    JPanel difLayout;

    private Image backgroundImage;

    public NewGame(){




        this.setPreferredSize(new Dimension(1980, 1080));
        this.setLayout(new GridBagLayout());



        ImageIcon backdrop = new ImageIcon("src\\CastleBackDrop.png");
        backgroundImage = backdrop.getImage();




        newGameFeatures = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFeatures = new GridBagConstraints();




        difLayout = new JPanel();
        difLayout.setLayout(new FlowLayout());
        difLayout.setOpaque(false);


        name = new JTextField("Player what is your name?");
        //name.setPreferredSize(new Dimension(100, 100));
        //name.setVisible(true);
        gbcFeatures.gridx = 0;
        gbcFeatures.gridy = 0;
        gbcFeatures.fill = GridBagConstraints.HORIZONTAL;
        gbcFeatures.weightx = 1.0;
        gbcFeatures.insets = new Insets(20, 20, 20, 20);
        name.setPreferredSize(new Dimension(400, 100)); // More typical text field size
        newGameFeatures.add(name, gbcFeatures);




        easyButton = new JButton("Easy");
        easyButton.setPreferredSize(new Dimension(250,100));
        easyButton.setOpaque(true);

        mediumButton = new JButton("Medium");
        mediumButton.setPreferredSize(new Dimension(250,100));
        mediumButton.setOpaque(true);

        hardButton = new JButton("Hard");
        hardButton.setPreferredSize(new Dimension(250,100));



        difLayout.add(easyButton);
        difLayout.add(mediumButton);
        difLayout.add(hardButton);
        difLayout.setOpaque(false);

        gbcFeatures.gridy++;
        newGameFeatures.add(difLayout, gbcFeatures);

        gbcFeatures.gridy++;
        startButton = new JButton("Start Your Adventure!");
        startButton.setPreferredSize(new Dimension(800, 100)); // Adjusted size
        startButton.setActionCommand("START");
        startButton.addActionListener(buttonListener);
        newGameFeatures.add(startButton, gbcFeatures);

        gbcFeatures.gridy++;
        goBack = new JButton("Go Back to Main Menu");
        goBack.setPreferredSize(new Dimension(50,50));
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);
        newGameFeatures.add(goBack, gbcFeatures);
        newGameFeatures.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;




        this.add(newGameFeatures, gbc);
        this.setVisible(true);

    }

    /**
     * creates Player instance
     * @param name string sets the Player instance's name
     */
    public void createPlayer(String name){

        Player.setPlayerName(name);
        java.lang.System.out.println(Player.getPlayerName());
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()){

                case "START":
                    createPlayer(name.getText());
                    MainDisplay.showGamePlay();
                    GamePlay.initializeKeyPad();
                    GamePlay.startGame();

                    break;

                case "MainMenu":
                    name.setText("Player what is your name?");
                    MainDisplay.showMainMenu();
                    break;


            }


        }
    };



}
