package items.consumable.potions;

import entities.Entity;
import items.Item;
import items.capabilities.Consumable;
import systems.Effect;

public class Potion extends Item implements Consumable {
    private final Effect effect;

    public Potion(String name, float volume, Effect effect) {
        super(name, volume);
        this.effect = effect;
    }

    @Override
    public void consume(Entity consumer) {
        effect.apply(consumer);
    }
}
