package items.capabilities;

import items.Item;

public interface Container {
    void addItem(Item item);
    void removeItem(Item item);
    void displayInventory();
    int count();
    Item getItem(int inventoryInput);
}
