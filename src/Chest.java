import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * chest class is responsible for giving out items to players
 * @author Esteban Rodriguez
 */
public class Chest extends ChestImages implements Serializable {


    private String name;

    private int chestPosX;
    private int chestPosY;
    private char direction = ' ';

    private Timer animationTimer;
    private boolean isMoving;
    private boolean closedState = true;

    private ArrayList<Collectable> chestInventory;




    public Chest(String name, char direction) {

        this.name = name;
        this.direction = direction;
        chestInventory = new ArrayList<>();
        resetTimer();
        ImageIcon icon;
        switch (this.direction) {
            case 'a':
                icon = this.leftFacingChestClose; // Assuming leftFacingChestClose is the icon for 'a'
                break;
            case 'd':
                icon = this.rightFacingChestClose; // Assuming rightFacingChestClose is the icon for 'd'
                break;
            // Add other cases if needed
            default:
                icon = null;
                break;
        }

        if (icon != null) {
            this.setIcon(icon);
        }
    }
    private void resetTimer(){

        this.animationTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {updateAnimation();
            }
        });
        this.animationTimer.start();
    }

    public void updateAnimation(){
        SwingUtilities.invokeLater(() -> {
            if (closedState == false) {

                this.updateAnimation(this.direction, closedState);
                this.repaint();  // Refresh the display
                this.revalidate();
            } else {
                this.animationTimer.stop();
            }
        });
    }
    public Collectable openChest() {
        if (closedState) {
            if (chestInventory.isEmpty()) {
                java.lang.System.out.println("The chest is empty.");
                return null;
            }

            int index = (int) (Math.random() * chestInventory.size());
            Collectable collectable = chestInventory.get(index);
            java.lang.System.out.println(collectable.getName());
            closedState = !closedState;
            updateAnimation();
            return collectable;

        }
        return null;
    }
    public void addCollectable(Collectable collectable){
        chestInventory.add(collectable);


    }
    public int getChestPosX(){
        return this.chestPosX;

    }
    public int getChestPosY(){
        return this.chestPosY;
    }
    public char getDirection(){
        return this.direction;
    }

    public void setChestPosX(int chestPosX){
        this.chestPosX = chestPosX;
    }
    public void setChestPosY(int chestPosY){
        this.chestPosY = chestPosY;
    }



}