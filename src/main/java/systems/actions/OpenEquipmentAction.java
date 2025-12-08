package systems.actions;

import core.GameContext;
import systems.EquipmentSystem;

public class OpenEquipmentAction extends FreeAction{
    EquipmentSystem equipment;

    public OpenEquipmentAction(String label, EquipmentSystem equipment) {
        super(label);
        this.equipment = equipment;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public ActionCategory getCategory() {
        return ActionCategory.EQUIPMENT;
    }

    @Override
    public void execute() {
        GameContext.getInstance().setActiveEquipmentSystem(equipment);
        GameContext.getInstance().setMode(GameContext.Mode.EQUIPMENT);
    }
}
