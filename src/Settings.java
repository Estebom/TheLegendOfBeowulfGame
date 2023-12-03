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




        goBack = new JButton("Go Back to Main Menu");
        goBack.setPreferredSize(new Dimension(50, 50));
        goBack.setActionCommand("MainMenu");
        goBack.addActionListener(buttonListener);








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
