package items.equippable.backItems;

import items.Item;
import items.capabilities.Container;
import items.capabilities.Equippable;
import items.equippable.EquipmentSlotType;
import systems.InventorySystem;

public class Backpack extends Item implements Equippable, Container {
    private InventorySystem inventory;
    private EquipmentSlotType slotType;

    public Backpack(String name, float volume, float maxVolume) {
        super(name, volume);
        inventory = new InventorySystem(maxVolume);
        slotType = EquipmentSlotType.BACK;
    }

    @Override
    public EquipmentSlotType getType() {
        return slotType;
    }

    @Override
    public void addItem(Item item) {
        inventory.add(item);
    }

    @Override
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    @Override
    public void displayInventory() {
        inventory.list();
    }

    @Override
    public int count() {
        return inventory.count();
    }

    @Override
    public Item getItem(int index) {
        return inventory.find(index);
    }
}
