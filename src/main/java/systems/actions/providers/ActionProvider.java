package systems.actions.providers;

import core.GameContext;
import entities.Entity;
import items.Item;
import systems.actions.Action;

import java.util.List;

public interface ActionProvider {
    List<Action> getActions(Entity actor, Item focus, GameContext context);
}
