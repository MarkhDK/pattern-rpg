package systems;

import core.GameContext;
import entities.Entity;
import items.Item;
import systems.actions.Action;
import systems.actions.providers.ActionProvider;

import java.util.ArrayList;
import java.util.List;

public class ActionRegistry {
    List<ActionProvider> providers = new ArrayList<>();

    public void registerProvider(ActionProvider provider) {
        providers.add(provider);
    }

    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        for (ActionProvider provider : providers) {
            actions.addAll(provider.getActions(actor, focus, context));
        }

        if (focus instanceof ActionProvider ap) {
            actions.addAll(ap.getActions(actor, focus, context));
        }

        if (actor instanceof ActionProvider ep) {
            actions.addAll(ep.getActions(actor, focus, context));
        }

        return actions;
    }

    public List<Action> getAvailableActions(Entity actor, Item focus, GameContext context) {
        return providers.stream().flatMap(p -> p.getActions(actor, focus, context).stream()).filter(Action::isAvailable).toList();
    }
}
