package systems;

import items.capabilities.Equippable;

public class Slot<T extends Equippable> {
    private T item;

    public void equip(T item) {
        this.item = item;
    }

    public void unequip() {
        this.item = null;
    }

    public T getItem() {
        return item;
    }
}
