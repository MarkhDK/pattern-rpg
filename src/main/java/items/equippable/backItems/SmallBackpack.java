package items.equippable.backItems;

import items.consumable.potions.Potion;
import systems.Effect;

public class SmallBackpack extends Backpack {
    public SmallBackpack() {
        super("Backpack", 15.0f, 15.0f);
        super.addItem(new Potion("Health Potion", 0.1f, new Effect("heal", 30, 0)));
    }
}
