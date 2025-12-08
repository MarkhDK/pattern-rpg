package systems.actions;

import core.GameContext;

public class FleeAction extends FreeAction{
    public FleeAction(String label) {
        super(label);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void perform() {
        GameContext.getInstance().setMode(GameContext.Mode.MAIN_MENU);
    }
}
