package systems.actions;

import core.GameContext;

public class StartCombatAction extends FreeAction {
    String enemyType;

    public StartCombatAction(String label, String enemyType) {
        super(label);
        this.enemyType = enemyType;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void execute() {
        GameContext.getInstance().setMode(GameContext.Mode.COMBAT);
        GameContext.getInstance().getCombatSystem().combatLoop(GameContext.getInstance().getEnemyFactory().createEnemy(enemyType));
    }
}
