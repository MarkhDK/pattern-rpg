package entities;

import core.GameContext;
import items.Item;
import items.equippable.EquipmentSlotType;
import items.equippable.weapons.Weapon;

public class Enemy extends Entity{
    int reward;

    public Enemy(String name, int maxHp, int reward, Weapon weapon) {
        super(name, maxHp);
        this.reward = reward;
        this.equipment.equip(weapon);
    }

    @Override
    public void render() {
        System.out.println("-----Enemy-----");
        System.out.println("Health: " + hp);
        System.out.println(attackReport);
    }

    @Override
    public void die() {
        this.equipment.getBackpack().addItem((Item) equipment.getWeapon());
        this.equipment.unequip(EquipmentSlotType.RIGHT_HAND);
        GameContext.getInstance().addCorpse(this);
        notifyObservers();
    }

    @Override
    public void targetDied(Entity target) {

    }

    public int getReward() {
        return reward;
    }
}
