package systems;

import items.Item;

import java.util.ArrayList;
import java.util.List;

public class InventorySystem {
    private float capacity;
    private float contents;
    private List<Item> items;

    public InventorySystem(float capacity) {
        this.capacity = capacity;
        this.contents = 0.0f;
        this.items = new ArrayList<>();
    }

    public boolean add(Item item) {
        float itemVolume = item.getVolume();
        if((contents + itemVolume) > capacity) {
            return false;
        }
        items.add(item);
        contents += itemVolume;
        return true;
    }

    public boolean remove(Item item) {
        if(items.contains(item)) {
            float itemVolume = item.getVolume();
            if ((contents - itemVolume) < 0.0f && items.size() == 1) {
                contents = 0.0f;
            }
            items.remove(item);

            return true;
        }
        return false;
    }

    public Item find(int index) {
        if(index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public void list() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
        System.out.println((items.size() + 1) + ". Close inventory");
    }

    public int count() {
        return items.size();
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
