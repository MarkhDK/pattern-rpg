package systems.actions;

import core.GameContext;
import entities.Entity;
import items.Item;

public class TakeItemAction extends TurnAction{
    Item item;
    Entity actor;

    public TakeItemAction(String label, Item item, Entity actor) {
        super(label);
        this.item = item;
        this.actor = actor;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public ActionCategory getCategory() {
        return ActionCategory.INVENTORY;
    }

    @Override
    public void perform() {
        GameContext.getInstance().getActiveContainer().removeItem(item);
        actor.getBackpack().addItem(item);
    }
}
