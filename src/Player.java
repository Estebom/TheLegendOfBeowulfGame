public class Player {

    private String playerName;

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

    }
}
