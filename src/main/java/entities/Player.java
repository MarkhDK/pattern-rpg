package entities;

import items.equippable.backItems.SmallBackpack;
import items.equippable.weapons.RustySword;

import java.util.Scanner;

public class Player extends Entity {
    Scanner scanner = new Scanner(System.in);
    private static Player instance;
    int level;
    int xp;
    int xpToLevel;

    private Player() {
        super("Player", 100);
        this.level = 1;
        this.xp = 0;
        setXpToLevel();
        equipItem(new SmallBackpack());
        equipItem(new RustySword());
    }

    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
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
        instance = null;
        notifyObservers();
        System.out.println("Player died! Game over...");
        scanner.nextLine();
    }

    @Override
    public int getReward() {
        return 0;
    }
}
