/**
 * This class is used to handle the different game items
 * @authors Esteban Rodriguez & Alfonso Avila
 */

// Class representing an item that implements the Collectable interface
public class Item implements Collectable {

    // Instance variables
    private String nameItem;
    private String info;
    private int price;

    // Constructor to initialize an Item with a name, information, and price
    public Item(String nameItem, String info, int price) {
        this.nameItem = nameItem;
        this.info = info;
        this.price = price;
    }

//    public Item(Collectable collectable) {
//    }
    // Constructor for creating an Item from another Collectable (not utilized in the provided code)
//    public Item(Collectable collectable) {
//    }

    // Getter method for retrieving the item's name
    public String getnameItem() {
        return nameItem;
    }

    // Getter method for retrieving the item's information
    public String getinfo() {
        return info;
    }

    // Implementation of the use method from the Collectable interface (not utilized in the provided code)
    @Override
    public void use(boolean b, int i) {
        // Implementation details for using the item, but not specified in the code
    }

    // Implementation of the getPrice method from the Collectable interface
    @Override
    public int getPrice() {
        return this.price;
    }
}
