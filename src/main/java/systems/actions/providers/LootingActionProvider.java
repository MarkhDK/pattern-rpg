package systems.actions.providers;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.Container;
import systems.actions.Action;
import systems.actions.TakeItemAction;

import java.util.ArrayList;
import java.util.List;

public class LootingActionProvider implements ActionProvider{
    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        if (context.getMode() != GameContext.Mode.LOOTING) {
            return actions;
        }

        if (!(focus instanceof Container container)) {
            return actions;
        }

        for (Item item : container.getItems()) {
            actions.add(new TakeItemAction("Take " + item.getName(), item, actor));
        }

        return actions;
    }
}
