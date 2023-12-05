import java.io.Serializable;

/**
 * @author Esteban Rodriguez
 */
public class Player implements Serializable {

    private String playerName;
    private double damageOutput;

    private double health;
    private static Player instance;



    private Player(){}

    public static Player getInstance(){

        if(instance == null){
            instance = new Player();
        }
        return instance;
    }

    public void setPlayerName(String name){

        this.playerName = name;
        this.damageOutput = 1.0;
        this.health = 100.0;
    }

    public double getHealth(){

        return health;

    }

    public double getDamageOutput() {
        return damageOutput;
    }

    public void setDamageOutput(double damageOutput) {
        this.damageOutput = damageOutput;
    }

    public void setHealth(double health){

        this.health = health;

    }

    public void heal(double health){
        this.health += health;
    }

    public void takeDamage(double damage){
        this.health -= damage;
    }
    public String getPlayerName(){
        return this.playerName;
    }



    public static void replacePlayerInstance(Player newInstance){             //Edit by Nohea
        instance = newInstance;
    }



}
