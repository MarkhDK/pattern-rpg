package systems.actions;

import entities.Entity;

public class AttackAction extends TurnAction {
    private final Entity attacker;
    private final Entity target;

    public AttackAction(String label, Entity attacker, Entity target) {
        super(label);
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public boolean isAvailable() {
        return attacker.isAlive() && target.isAlive();
    }

    @Override
    public ActionCategory getCategory() {
        return ActionCategory.COMBAT;
    }

    @Override
    public void perform() {
        attacker.attack(target);
    }
}
