public class Movement {
    private int playerPosX = 0;
    private int playerPosY = 0;

    public Movement(){}


    public void playerMove(char m){

        switch (m){
            case 'w':
                playerPosY += 1;
                break;
            case 's':
                playerPosY -= 1;
                break;
            case 'a':
                playerPosX -= 1;
                break;
            case 'd':
                playerPosX += 1;
                break;
        }
        java.lang.System.out.println("(" + playerPosX + "," + playerPosY + ")");

    }
}