package entities;

import core.GameContext;
import items.Item;
import items.equippable.backItems.SmallBackpack;
import items.equippable.weapons.RustySword;
import systems.actions.Action;
import systems.actions.OpenContainerAction;
import systems.actions.OpenEquipmentAction;
import systems.actions.providers.ActionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Player extends Entity implements ActionProvider {
    Scanner scanner = new Scanner(System.in);
    int level;
    int xp;
    int xpToLevel;

    public Player() {
        super("Player", 100);
        this.level = 1;
        this.xp = 0;
        setXpToLevel();
        equipItem(new SmallBackpack());
        equipItem(new RustySword());
    }

    private void setXpToLevel() {
        xpToLevel = level * level * 100;
    }

    private void calculateHpTotal() {
        maxHp = 100 * level;
    }

    private void levelUp() {
        level++;
        setXpToLevel();
        calculateHpTotal();
        hp = maxHp;
    }

    @Override
    public void targetDied(Entity target) {
        attackReport = null;
        xp += target.getReward();
        if(xp >= xpToLevel) {
            levelUp();
        }
    }

    @Override
    public void render() {
        System.out.println("-----Player-----");
        System.out.println("Health: " + hp);
        if(attackReport != null) {
            System.out.println(attackReport);
        }
    }

    @Override
    public void die() {
        notifyObservers();
        System.out.println("Player died! Game over...");
        scanner.nextLine();
    }

    @Override
    public int getReward() {
        return 0;
    }

    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        if (getEquipment() != null) {
            actions.add(new OpenEquipmentAction("Open Equipment", getEquipment()));
        }

        if (getBackpack() != null) {
            actions.add(new OpenContainerAction("Open Inventory", getBackpack()));
        }

        return actions;
    }
}
