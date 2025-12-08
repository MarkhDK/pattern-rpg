package items.capabilities;

import items.Item;

import java.util.List;

public interface Container {
    void addItem(Item item);
    void removeItem(Item item);
    void displayInventory();
    int count();
    Item getItem(int inventoryInput);
    boolean contains(Item item);
    List<Item> getItems();
}
