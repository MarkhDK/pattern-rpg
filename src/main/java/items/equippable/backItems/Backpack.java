package items.equippable.backItems;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.Container;
import items.capabilities.Equippable;
import items.equippable.EquipmentSlotType;
import systems.InventorySystem;
import systems.actions.Action;
import systems.actions.InspectItemAction;
import systems.actions.providers.ActionProvider;

import java.util.ArrayList;
import java.util.List;

public class Backpack extends Item implements Equippable, Container, ActionProvider {
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

    @Override
    public boolean contains(Item item) {
        return inventory.contains(item);
    }

    @Override
    public List<Item> getItems() {
        return inventory.getItems();
    }

    @Override
    public void inspect() {
        GameContext.getInstance().setMode(GameContext.Mode.INSPECT_ITEM);
        System.out.println("Backpack - " + getName());
    }

    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        actions.add(new InspectItemAction("Backpack", this));

        return actions;
    }
}
