package systems.actions.providers;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.Consumable;
import systems.actions.Action;
import systems.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

public class ConsumableActionProvider implements ActionProvider{
    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        if (focus instanceof Consumable c) {
            actions.add(new ConsumeAction("Consume " + focus.getName(), c, actor));
        }

        return actions;
    }
}
