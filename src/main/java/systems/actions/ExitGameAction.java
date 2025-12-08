package systems.actions;

import core.Game;

public class ExitGameAction extends FreeAction {
    public ExitGameAction() {
        super("Exit Game");
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void perform() {
        Game.running = false;
    }
}
