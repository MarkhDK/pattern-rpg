package systems.actions;

import core.GameContext;
import items.capabilities.Container;

public class OpenContainerAction extends FreeAction {
    Container container;

    public OpenContainerAction(String label, Container container) {
        super(label);
        this.container = container;
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
        GameContext.getInstance().setActiveContainer(container);
        GameContext.getInstance().setMode(GameContext.Mode.INVENTORY);
    }
}
