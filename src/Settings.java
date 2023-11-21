import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JPanel {
    JButton goBack;

    private MainDisplay mainDisplay;

    public Settings(MainDisplay mainDisplay) {

        this.mainDisplay = mainDisplay;
        this.setPreferredSize(new Dimension(800, 675));
        this.setLayout(new GridBagLayout());


        GridBagConstraints gbcFeatures = new GridBagConstraints();

        goBack = new JButton("Go Back to Main Menu");
        goBack.setPreferredSize(new Dimension(50, 50));
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);


        //name.setPreferredSize(new Dimension(100, 100));
        //name.setVisible(true);
        gbcFeatures.gridx = 0;
        gbcFeatures.gridy = 0;
        gbcFeatures.fill = GridBagConstraints.HORIZONTAL;
        gbcFeatures.weightx = 1.0;
        gbcFeatures.insets = new Insets(20, 20, 20, 20);


        gbcFeatures.gridy++;


        gbcFeatures.gridy++;


        gbcFeatures.gridy++;


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        this.add(goBack);
        this.setVisible(true);
    }

    ActionListener buttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

           if(e.getActionCommand().equals("MainMenu")){


                   mainDisplay.showMainMenu();
           }


        }
    };
}
