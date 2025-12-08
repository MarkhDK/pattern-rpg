package systems.actions;

import systems.Ticker;

import java.util.ArrayList;
import java.util.List;

public abstract class TurnAction implements Action {
    protected final String label;
    private final List<Ticker> tickers = new ArrayList<>();

    public TurnAction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        perform();

        for (Ticker observer : tickers) {
            observer.tick();
        }
    }

    public void addObserver(Ticker ticker) {
        tickers.add(ticker);
    }

    @Override
    public String getLabel() {
        return label;
    }

    public abstract void perform();
}
