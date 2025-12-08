package systems.actions;

public abstract class FreeAction implements Action {
    protected final String label;

    public FreeAction(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public ActionCategory getCategory() {
        return ActionCategory.GENERAL;
    }
}
