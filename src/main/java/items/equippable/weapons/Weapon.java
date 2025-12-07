package items.equippable.weapons;

import items.Item;
import items.capabilities.DamageRoller;
import items.capabilities.Equippable;
import items.equippable.EquipmentSlotType;

import java.util.Random;

public class Weapon extends Item implements Equippable, DamageRoller {
    private int minDmg;
    private int maxDmg;
    private EquipmentSlotType slotType;
    private Random rng = new Random();

    public Weapon(String name, float volume, int minDmg, int maxDmg) {
        super(name, volume);
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.slotType = EquipmentSlotType.RIGHT_HAND;
    }

    @Override
    public EquipmentSlotType getType() {
        return slotType;
    }

    @Override
    public int calculateDamage() {
        return rng.nextInt(minDmg, maxDmg);
    }
}
