import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the MainMenu interface for users
 * @author Esteban Rodriguez
 */
public class MainMenu extends JPanel{
    private MainDisplay mainDisplay;

    JButton newGameButton;
    JButton loadGameButton;
    JButton settingButton;
    JButton exitGameButton;

    JPanel buttonLayout;

    JPanel centerPanel;

    private JLayeredPane layeredPane;

    public MainMenu(MainDisplay mainDisplay){

        this.mainDisplay = mainDisplay;


        this.setPreferredSize(new Dimension(1980, 1080));
        this.setLayout(new BorderLayout());
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1980, 1080));
        //Testing background
        JLabel label = new JLabel();
        label.setBackground(Color.red);
        label.setOpaque(true);
        label.setBounds(0, 0, 1980, 1080); // Set the bounds to cover the entire area

        layeredPane.add(label,Integer.valueOf(1));






        newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(600, 150));
        newGameButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size





        loadGameButton = new JButton("Load Game");
        loadGameButton.setPreferredSize(new Dimension(600, 150));
        loadGameButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size



        settingButton = new JButton("Settings");
        settingButton.setPreferredSize(new Dimension(600, 150));
        settingButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size

        exitGameButton = new JButton("Exit Game");
        exitGameButton.setPreferredSize(new Dimension(600, 150));
        exitGameButton.setMaximumSize(new Dimension(600, 150)); // Set maximum size


        newGameButton.setActionCommand("NewGame");
        loadGameButton.setActionCommand("LOAD_GAME");
        settingButton.setActionCommand("SETTINGS");
        exitGameButton.setActionCommand("EXIT");

        newGameButton.addActionListener(e -> mainDisplay.showNewGame());
        loadGameButton.addActionListener(buttonListener);
        settingButton.addActionListener(buttonListener);
        exitGameButton.addActionListener(buttonListener);

        buttonLayout = new JPanel();
        buttonLayout.setLayout(new BoxLayout(buttonLayout, BoxLayout.Y_AXIS));
        buttonLayout.add(Box.createVerticalGlue());
        buttonLayout.setOpaque(false);
        buttonLayout.add(newGameButton);

        //adds spacing between buttons
        buttonLayout.add(Box.createRigidArea(new Dimension(0, 25))); // 10-pixel vertical space
        buttonLayout.add(loadGameButton);
        buttonLayout.add(Box.createRigidArea(new Dimension(0, 25))); // 10-pixel vertical space
        buttonLayout.add(settingButton);
        buttonLayout.add(Box.createRigidArea(new Dimension(0, 25))); // 10-pixel vertical space
        buttonLayout.add(exitGameButton);
        buttonLayout.add(Box.createVerticalGlue());


        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(buttonLayout);

        centerPanel.setBounds(0, 0, 1980, 1080);  // Adjust bounds as needed
        layeredPane.add(centerPanel, Integer.valueOf(2));



        this.add(layeredPane, BorderLayout.CENTER);
        this.setVisible(true);



    }
    public void updateLayout(int width, int height) {
        // Update the layout and size of MainMenu components based on new width and height
        this.setPreferredSize(new Dimension(width, height));


        Component[] components = layeredPane.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                component.setBounds(0, 0, width, height);
            }
        }
        // Calculate new sizes for buttons based on the new resolution
        // For example, you might want the buttons to be a certain percentage of the screen size
        int buttonWidth = width / 3; // Example calculation, adjust as needed
        int buttonHeight = height / 10; // Example calculation, adjust as needed

        // Update button sizes
        Dimension newButtonSize = new Dimension(buttonWidth, buttonHeight);
        newGameButton.setPreferredSize(newButtonSize);
        loadGameButton.setPreferredSize(newButtonSize);
        settingButton.setPreferredSize(newButtonSize);
        exitGameButton.setPreferredSize(newButtonSize);

        // Optional: Update font size based on new resolution
        // ...

        // Update the bounds of the center panel
        centerPanel.setBounds(0, 0, width, height);

        // Revalidate and repaint the panel and its components to apply changes
        this.revalidate();
        this.repaint();
    }
    ActionListener buttonListener  = new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            switch (e.getActionCommand()){
                case "NewGame":
                    java.lang.System.out.println("New Game started");
                    mainDisplay.showNewGame();
                    break;
                case "LOAD_GAME":
                    java.lang.System.out.println("Load Game clicked");

                    break;
                case "SETTINGS":
                    mainDisplay.showSettings();
                    java.lang.System.out.println("Settings opened");

                    break;
                case "EXIT":
                    java.lang.System.exit(1);
                    break;

            }
            MainMenu.this.revalidate();
            MainMenu.this.repaint();

        }
    };


}
