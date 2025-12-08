package systems.actions.providers;

import core.GameContext;
import entities.Entity;
import items.Item;
import systems.actions.Action;
import systems.actions.StartCombatAction;

import java.util.ArrayList;
import java.util.List;

public class GeneralActionProvider implements ActionProvider{
    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        if (context.getMode() != GameContext.Mode.MAIN_MENU) {
            return actions;
        }

        actions.add(new StartCombatAction("Fight goblin", "goblin"));
        actions.add(new StartCombatAction("Fight orc", "orc"));

        return actions;
    }
}
