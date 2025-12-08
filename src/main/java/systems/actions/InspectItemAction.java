package systems.actions;

import items.Item;

public class InspectItemAction extends FreeAction{
    Item item;

    public InspectItemAction(String label, Item item) {
        super(label);
        this.item = item;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void execute() {
        item.inspect();
    }
}
