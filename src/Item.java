public class Item {
    private Player player;
    private String nameItem;
    private String info;

    public Item(String nameItem, String info) {
        this.nameItem = nameItem;
        this.info = info;
        player = Player.getInstance();
    }

    public String getnameItem() {
        return nameItem;
    }

    public String getinfo() {
        return info;
    }
}
