package systems;

import items.Item;
import items.capabilities.Container;
import items.capabilities.DamageRoller;
import items.capabilities.Equippable;
import items.equippable.EquipmentSlotType;

import java.util.EnumMap;
import java.util.Map;

public class EquipmentSystem {
    private final Map<EquipmentSlotType, Slot<Equippable>> equipment = new EnumMap<>(EquipmentSlotType.class);

    public EquipmentSystem() {
        // Define Equipment Slots
        equipment.put(EquipmentSlotType.LEFT_HAND, new Slot<>());
        equipment.put(EquipmentSlotType.RIGHT_HAND, new Slot<>());
        equipment.put(EquipmentSlotType.BACK, new Slot<>());
        equipment.put(EquipmentSlotType.HEAD, new Slot<>());
        equipment.put(EquipmentSlotType.CHEST, new Slot<>());
        equipment.put(EquipmentSlotType.LEGS, new Slot<>());
        equipment.put(EquipmentSlotType.FEET, new Slot<>());
        equipment.put(EquipmentSlotType.ARMS, new Slot<>());
        equipment.put(EquipmentSlotType.GLOVES, new Slot<>());
    }

    public boolean isEquipped(Item item) {
        for (Slot<Equippable> slot : equipment.values()) {
            if (slot.getItem() == item) {
                return true;
            }
        }

        return false;
    }

    public Item get(EquipmentSlotType equipmentSlotType) {
        return (Item) equipment.get(equipmentSlotType).getItem();
    }

    public void equip(Equippable item) {
        EquipmentSlotType equipmentSlotType = item.getType();
        equipment.get(equipmentSlotType).equip(item);
    }

    public void render() {
        int i = 1;
        for (Slot<Equippable> slot : equipment.values()) {
            Item item = (Item) slot.getItem();
            System.out.println(i + ". " + item.getName());
            i++;
        }
        System.out.println(i + ". Close");
        System.out.println("--------------------");
    }

    public void unequip(EquipmentSlotType equipmentSlotType) {
        equipment.get(equipmentSlotType).unequip();
    }

    public DamageRoller getWeapon() {
        return (DamageRoller) equipment.get(EquipmentSlotType.RIGHT_HAND).getItem();
    }

    public Container getBackpack() {
        return (Container) equipment.get(EquipmentSlotType.BACK).getItem();
    }
}
