package entities;

import items.Item;
import items.capabilities.Container;
import items.capabilities.Equippable;
import items.capabilities.Usable;
import items.equippable.EquipmentSlotType;
import items.equippable.backItems.Backpack;
import systems.EquipmentSystem;
import systems.actions.providers.ActionProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Observer {
    String name;
    int maxHp;
    int hp;
    List<Observer> observers;
    String attackReport;
    EquipmentSystem equipment;

    public Entity(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.observers = new ArrayList<>();
        this.equipment = new EquipmentSystem();
        equipment.equip(new Backpack("Backpack", 15.0f, 15.0f));
    }

    public Entity(String name, int maxHp, EquipmentSystem equipmentSystem) {
        this.name = name;
        this.maxHp = maxHp;
        this.equipment = equipmentSystem;
    }

    public void restoreHealth(int amount) {
        hp += amount;

        if(hp > maxHp) {
            hp = maxHp;
        }
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    public void attack(Entity target) {
        String report = "%s dealt %,d damage to %s";
        int dmg = equipment.getWeapon().calculateDamage();
        attackReport = String.format(report, name, dmg, target.getName());
        target.takeDamage(dmg);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public EquipmentSystem getEquipment() {
        return equipment;
    }

    public Item getEquippedItem(EquipmentSlotType equipmentSlotType) {
        return equipment.get(equipmentSlotType);
    }

    public void useItem(Usable item) {

    }

    public void equipItem(Equippable item) {
        equipment.equip(item);
    }

    public void unequipItem(EquipmentSlotType equipmentSlotType) {
        equipment.unequip(equipmentSlotType);
    }

    public Container getBackpack() {
        return (Container) equipment.get(EquipmentSlotType.BACK);
    }

    public void showInventory() {
        this.getBackpack().displayInventory();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.targetDied(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public abstract void render();
    public abstract void die();
    public abstract int getReward();
}
