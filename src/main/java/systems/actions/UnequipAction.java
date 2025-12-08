package systems.actions;

import items.Item;
import entities.Entity;
import items.capabilities.Equippable;

public class UnequipAction extends FreeAction {
    private final Item item;
    private final Entity entity;

    public UnequipAction(String label, Item item, Entity entity) {
        super(label);
        this.item = item;
        this.entity = entity;
    }

    @Override
    public boolean isAvailable() {
        return entity.getEquipment().isEquipped(item);
    }

    @Override
    public ActionCategory getCategory() {
        return ActionCategory.EQUIPMENT;
    }

    @Override
    public void perform() {
        entity.unequipItem(((Equippable) item).getType());
        entity.getBackpack().addItem(item);
    }
}
