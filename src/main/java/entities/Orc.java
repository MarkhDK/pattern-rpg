package entities;

import items.equippable.weapons.RustySword;

public class Orc extends Enemy{
    public Orc() {
        super("Orc", 55, 110, new RustySword());
    }
}
