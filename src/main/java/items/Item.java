package items;

import systems.actions.Action;
import systems.actions.providers.ItemActionProvider;

import java.util.List;

public abstract class Item implements ItemActionProvider {
    String name;
    float volume;

    public Item(String name, float volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public float getVolume() {
        return volume;
    }

    public abstract void inspect();
    public abstract List<Action> getActions();
}
