

public class Item implements Collectable {
    private String nameItem;
    private String info;

    private int price;

    public Item(String nameItem, String info, int price) {
        this.nameItem = nameItem;
        this.info = info;
        this.price = price;
    }

    public Item(Collectable collectable) {
    }

    public String getnameItem() {
        return nameItem;
    }

    public String getinfo() {
        return info;
    }


    @Override
    public void use(boolean b, int i) {

    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
