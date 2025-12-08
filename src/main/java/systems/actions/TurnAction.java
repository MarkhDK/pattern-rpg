package systems.actions;

import systems.TurnObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class TurnAction implements Action{
    protected final String label;
    private final List<TurnObserver> observers = new ArrayList<>();

    public TurnAction(String label) {
        this.label = label;
    }

    public void addObserver(TurnObserver observer) {
        observers.add(observer);
    }

    @Override
    public void execute() {
        perform();

        for (TurnObserver observer : observers) {
            observer.tick();
        }
    }

    @Override
    public String getLabel() {
        return label;
    }

    public abstract void perform();
}
