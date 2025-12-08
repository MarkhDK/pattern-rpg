package systems.actions.providers;

import systems.actions.Action;

import java.util.List;

public interface ItemActionProvider extends ActionProvider{
    List<Action> getActions();
}
