package entities;

import items.equippable.weapons.SmallKnife;

public class Goblin extends Enemy{
    public Goblin() {
        super("Goblin", 24, 50, new SmallKnife());
    }
}
