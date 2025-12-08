package systems.actions;

import entities.Entity;
import items.Item;
import items.capabilities.Consumable;

public class ConsumeAction extends TurnAction {
    Consumable item;
    Entity consumer;

    public ConsumeAction(String label, Consumable item, Entity consumer) {
        super(label);
        this.item = item;
        this.consumer = consumer;
    }

    @Override
    public boolean isAvailable() {
        return consumer.getBackpack().contains((Item) item);
    }

    @Override
    public ActionCategory getCategory() {
        return ActionCategory.GENERAL;
    }

    @Override
    public void perform() {
        item.consume(consumer);
    }
}
