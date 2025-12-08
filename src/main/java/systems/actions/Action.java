package systems.actions;

public interface Action {
    String getLabel();
    ActionCategory getCategory();
    boolean isAvailable();
    void execute();
}
