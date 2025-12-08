package systems.actions;

import items.Item;
import items.capabilities.Equippable;
import entities.Entity;

public class EquipAction extends FreeAction {
    private final Item item;
    private final Entity entity;

    public EquipAction(String label, Item item, Entity entity) {
        super(label);
        this.item = item;
        this.entity = entity;
    }

    @Override
    public boolean isAvailable() {
        return !entity.getEquipment().isEquipped(item);
    }

    @Override
    public ActionCategory getCategory() {
        return ActionCategory.EQUIPMENT;
    }

    @Override
    public void perform() {
        entity.equipItem((Equippable) item);
    }
}
