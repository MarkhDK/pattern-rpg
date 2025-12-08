package items.consumable.potions;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.Consumable;
import systems.Effect;
import systems.actions.Action;
import systems.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void inspect() {
        GameContext.getInstance().setMode(GameContext.Mode.INSPECT_ITEM);
        System.out.println("Potion - " + getName());
        System.out.println("Effect: " + effect.getType());
        System.out.println("Magnitude: " + effect.getMagnitude());
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();

        if (GameContext.getInstance().getMode() == GameContext.Mode.INSPECT_ITEM) {
            actions.add(new ConsumeAction("Consume " + getName(), this, GameContext.getInstance().getPlayer()));
        }

        return actions;
    }

    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        return List.of();
    }
}
