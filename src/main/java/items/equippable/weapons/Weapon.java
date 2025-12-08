package items.equippable.weapons;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.DamageRoller;
import items.capabilities.Equippable;
import items.equippable.EquipmentSlotType;
import systems.actions.Action;
import systems.actions.AttackAction;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void inspect() {
        GameContext.getInstance().setMode(GameContext.Mode.INSPECT_ITEM);
        System.out.println("Weapon - " + getName());
        System.out.println("Damage: " + minDmg + " - " + maxDmg);
        System.out.println("Slot: " + slotType.toString());
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();

        if (GameContext.getInstance().getMode() == GameContext.Mode.COMBAT) {
            actions.add(new AttackAction("Attack", GameContext.getInstance().getPlayer(), GameContext.getInstance().getEnemy()));
        }

        return actions;
    }

    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        return List.of();
    }
}
