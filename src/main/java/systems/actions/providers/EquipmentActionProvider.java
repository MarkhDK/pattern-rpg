package systems.actions.providers;

import core.GameContext;
import entities.Entity;
import items.Item;
import items.capabilities.Equippable;
import systems.actions.Action;
import systems.actions.EquipAction;
import systems.actions.UnequipAction;

import java.util.ArrayList;
import java.util.List;

public class EquipmentActionProvider implements ActionProvider{
    @Override
    public List<Action> getActions(Entity actor, Item focus, GameContext context) {
        List<Action> actions = new ArrayList<>();

        if (!(focus instanceof Equippable)) {
            return actions;
        }

        boolean equipped = actor.getEquipment().isEquipped(focus);

        if (equipped) {
            actions.add(new UnequipAction("Unequip " + focus.getName(), focus, actor));
        } else {
            actions.add(new EquipAction("Equip " + focus.getName(), focus, actor));
        }

        return actions;
    }
}
