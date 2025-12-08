package systems.actions.providers;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.Container;
import items.equippable.EquipmentSlotType;
import systems.actions.Action;
import systems.actions.AttackAction;
import systems.actions.FleeAction;
import systems.actions.OpenContainerAction;

import java.util.ArrayList;
import java.util.List;

public class CombatActionProvider implements ActionProvider{
    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        if (context.getMode() != GameContext.Mode.COMBAT) {
            return actions;
        }

        Entity enemy = context.getEnemy();
        actions.add(new AttackAction("Attack " + enemy.getName(), actor, enemy));
        actions.add(new OpenContainerAction("Open Inventory", (Container) actor.getEquippedItem(EquipmentSlotType.BACK)));
        actions.add(new FleeAction("Flee"));

        return actions;
    }
}
