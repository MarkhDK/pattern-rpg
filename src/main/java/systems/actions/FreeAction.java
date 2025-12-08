package systems.actions;

import core.GameContext;
import rendering.Renderer;

public abstract class FreeAction implements Action {
    protected final String label;
    private final Renderer renderer = GameContext.getInstance().getRenderer();

    public FreeAction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        perform();

        renderer.render();
    }

    @Override
    public String getLabel() {
        return label;
    }

    public ActionCategory getCategory() {
        return ActionCategory.GENERAL;
    }

    public abstract void perform();
}
